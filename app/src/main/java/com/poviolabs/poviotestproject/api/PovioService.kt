package com.poviolabs.poviotestproject.api

import com.poviolabs.poviotestproject.models.SearchResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PovioService {

    @GET("flowers")
    fun searchRepos(@Query("query") query: String, @Query("page") page: Int): Call<SearchResult>
}