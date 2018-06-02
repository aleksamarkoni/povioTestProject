package com.poviolabs.poviotestproject.db

import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.poviolabs.poviotestproject.models.Flower

@Dao
interface FlowersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(posts: List<Flower>)

    // Do a similar query as the search API:
    // Look for flowers that contain the query string in the name or in the latinName
    // and order those results descending, by the number of sightings and then by name
    @Query("SELECT * FROM flowers WHERE (name LIKE :queryString) OR (latinName LIKE " +
            ":queryString) ORDER BY sightings DESC, name ASC")
    fun flowersByName(queryString: String): DataSource.Factory<Int, Flower>
}