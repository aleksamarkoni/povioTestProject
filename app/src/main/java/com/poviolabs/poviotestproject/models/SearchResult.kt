package com.poviolabs.poviotestproject.models

import com.google.gson.annotations.SerializedName

data class SearchResult(
        @field:SerializedName("flowers")
        val items: List<Flower>,
        @field:SerializedName("meta")
        val meta: Meta
)