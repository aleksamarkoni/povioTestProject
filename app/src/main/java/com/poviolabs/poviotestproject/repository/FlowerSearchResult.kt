package com.poviolabs.poviotestproject.repository

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList
import com.poviolabs.poviotestproject.models.Flower

data class FlowerSearchResult(
        val data: LiveData<PagedList<Flower>>,
        val networkErrors: LiveData<String>
)