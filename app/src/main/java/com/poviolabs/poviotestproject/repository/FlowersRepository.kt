package com.poviolabs.poviotestproject.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import com.poviolabs.poviotestproject.AppExecutors
import com.poviolabs.poviotestproject.api.ApiResponse
import com.poviolabs.poviotestproject.api.ApiSuccessResponse
import com.poviolabs.poviotestproject.api.PovioService
import com.poviolabs.poviotestproject.models.Flower
import com.poviolabs.poviotestproject.models.Resource
import com.poviolabs.poviotestproject.models.SearchResult
import com.poviolabs.poviotestproject.util.AbsentLiveData
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository that handles Flower instances.
 */
@Singleton
class FlowersRepository @Inject constructor(
        private val appExecutors: AppExecutors,
        private val povioService: PovioService
) {

    fun search(query: String): LiveData<Resource<List<Flower>>> {
        return object : NetworkBoundResource<List<Flower>, SearchResult>(appExecutors) {
            override fun createCall(): LiveData<ApiResponse<SearchResult>> {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun saveCallResult(item: SearchResult) {
//                val repoIds = item.items.map { it.id }
//                val repoSearchResult = RepoSearchResult(
//                        query = query,
//                        repoIds = repoIds,
//                        totalCount = item.total,
//                        next = item.nextPage
//                )
//                db.beginTransaction()
//                try {
//                    repoDao.insertRepos(item.items)
//                    repoDao.insert(repoSearchResult)
//                    db.setTransactionSuccessful()
//                } finally {
//                    db.endTransaction()
//                }
            }

            override fun shouldFetch(data: List<Flower>?) = data == null

            override fun loadFromDb(): LiveData<List<Flower>> {
//                return Transformations.switchMap(repoDao.search(query)) { searchData ->
//                    if (searchData == null) {
//                        AbsentLiveData.create()
//                    } else {
//                        repoDao.loadOrdered(searchData.repoIds)
//                    }
//                }
                return AbsentLiveData.create<List<Flower>>()
            }

            override fun processResponse(response: ApiSuccessResponse<SearchResult>)
                    : SearchResult {
                val body = response.body
                //body.nextPage = response.nextPage
                return body
            }
        }.asLiveData()
    }

}
