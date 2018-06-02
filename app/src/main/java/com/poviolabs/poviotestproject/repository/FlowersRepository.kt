package com.poviolabs.poviotestproject.repository

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList
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

    fun search(query: String): LiveData<Resource<PagedList<Flower>>> {
        return AbsentLiveData.create()
    }

}
