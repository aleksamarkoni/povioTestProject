package com.poviolabs.poviotestproject.repository

import android.arch.paging.LivePagedListBuilder
import com.poviolabs.poviotestproject.AppExecutors
import com.poviolabs.poviotestproject.api.PovioService
import com.poviolabs.poviotestproject.db.FlowersDao
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository that handles Flower instances.
 */
@Singleton
class FlowersRepository @Inject constructor(
        private val appExecutors: AppExecutors,
        private val povioService: PovioService,
        private val flowersDao: FlowersDao
) {

    fun search(query: String): FlowerSearchResult {
        val dataSourceFactory = flowersDao.flowersByName()

        // every new query creates a new BoundaryCallback
        // The BoundaryCallback will observe when the user reaches to the edges of
        // the list and update the database with extra data
        val boundaryCallback = RepoBoundaryCallback(appExecutors, query, povioService, flowersDao)
        val networkErrors = boundaryCallback.networkErrors

        val data = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
                .setBoundaryCallback(boundaryCallback)
                .build()

        return FlowerSearchResult(data, networkErrors)
    }

    companion object {
        private const val DATABASE_PAGE_SIZE = 20
    }
}
