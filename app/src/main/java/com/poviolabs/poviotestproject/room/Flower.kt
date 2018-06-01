package com.poviolabs.poviotestproject.room

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import com.google.gson.annotations.SerializedName

@Entity(
        indices = [
            (Index("id"))],
        primaryKeys = ["id"]
)
data class Flower(
        val id: Int,
        @field:SerializedName("name")
        val name: String,
        @field:SerializedName("latin_name")
        val fullName: String,
        @field:SerializedName("sightings")
        val sightings: Int,
        @field:SerializedName("profile_picture")
        val description: String?,
        @field:SerializedName("favorite")
        val favorite: Boolean
)