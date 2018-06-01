package com.poviolabs.poviotestproject.room

import com.google.gson.annotations.SerializedName

data class SearchResult(
        @field:SerializedName("flowers")
        val name: List<Flower>,
        @field:SerializedName("meta")
        val meta: Meta
)