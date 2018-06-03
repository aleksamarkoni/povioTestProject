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
    fun insert(flowers: List<Flower>)

    // Do a similar query as the search API:
    // Look for flowers that contain the query string in the name or in the latinName
    // and order those results descending, by the number of sightings and then by name
    @Query("select * from flowers where (name like :queryString) or (latinName like :queryString) order by sightings desc, name asc")
    fun flowersByName(queryString: String): DataSource.Factory<Int, Flower>
    //@Query("select * from flowers")
    //fun flowersByName(): DataSource.Factory<Int, Flower>
}
