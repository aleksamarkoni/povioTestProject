package com.poviolabs.poviotestproject.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
        tableName = "flowers"
)
data class Flower(
        @PrimaryKey @field:SerializedName("id") val id: Int,
        @field:SerializedName("name")
        val name: String,
        @field:SerializedName("latin_name")
        val latinName: String,
        @field:SerializedName("sightings")
        val sightings: Int,
        @field:SerializedName("profile_picture")
        val flowerPicture: String?,
        @field:SerializedName("favorite")
        val favorite: Boolean
)