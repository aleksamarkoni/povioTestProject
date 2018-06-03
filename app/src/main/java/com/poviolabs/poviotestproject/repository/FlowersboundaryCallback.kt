package com.poviolabs.poviotestproject.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PagedList
import android.util.Log
import com.poviolabs.poviotestproject.AppExecutors
import com.poviolabs.poviotestproject.api.PovioService
import com.poviolabs.poviotestproject.db.FlowersDao
import com.poviolabs.poviotestproject.models.Flower
import com.poviolabs.poviotestproject.models.SearchResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class RepoBoundaryCallback(
        private val appExecutors: AppExecutors,
        private val query: String,
        private val service: PovioService,
        private val flowersDao: FlowersDao
) : PagedList.BoundaryCallback<Flower>() {

    private val _networkErrors = MutableLiveData<String>()

    val networkErrors: LiveData<String>
        get() = _networkErrors

    // keep the last requested page. When the request is successful, increment the page number.
    private var lastRequestedPage = 1

    // avoid triggering multiple requests in the same time
    private var isRequestInProgress = false

    override fun onZeroItemsLoaded() {
        Timber.d("RepoBoundaryCallback onZeroItemsLoaded")
        requestAndSaveData(query)
    }

    override fun onItemAtEndLoaded(itemAtEnd: Flower) {
        Log.d("RepoBoundaryCallback", "onItemAtEndLoaded")
        requestAndSaveData(query)
    }

    private fun requestAndSaveData(query: String) {
        if (isRequestInProgress) return

        isRequestInProgress = true
        searchRepos(service, query, lastRequestedPage, { repos ->
            appExecutors.diskIO().execute {
                Timber.d("Database inserting ${repos.size} repos")
                flowersDao.insert(repos)
                lastRequestedPage++
                isRequestInProgress = false
            }
        }, { error ->
            isRequestInProgress = false
            _networkErrors.postValue(error)
        })
    }

    fun searchRepos(
            service: PovioService,
            query: String,
            page: Int,
            onSuccess: (repos: List<Flower>) -> Unit,
            onError: (error: String) -> Unit) {
        Timber.d("query: $query, page: $page")

        service.searchRepos(query, page).enqueue(
                object : Callback<SearchResult> {
                    override fun onFailure(call: Call<SearchResult>?, t: Throwable) {
                        Timber.d("fail to get data")
                        onError(t.message ?: "unknown error")
                    }

                    override fun onResponse(
                            call: Call<SearchResult>?,
                            response: Response<SearchResult>
                    ) {
                        Timber.d("got a response $response")
                        if (response.isSuccessful) {
                            val repos = response.body()?.items ?: emptyList()
                            onSuccess(repos)
                        } else {
                            onError(response.errorBody()?.string() ?: "Unknown error")
                        }
                    }
                }
        )
    }
}