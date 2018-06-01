package com.poviolabs.poviotestproject.room

import com.google.gson.annotations.SerializedName

data class Meta(
        @field:SerializedName("pagination")
        val currentPage: Pagination
) {
    data class Pagination(
            @field:SerializedName("current_page")
            val currentPage: Int,
            @field:SerializedName("prev_page")
            val prevPage: String?,
            @field:SerializedName("next_page")
            val nextPage: String?,
            @field:SerializedName("total_pages")
            val totalPages: Int
    )
}