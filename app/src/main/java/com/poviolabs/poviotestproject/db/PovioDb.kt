package com.poviolabs.poviotestproject.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.poviolabs.poviotestproject.models.Flower

@Database(
        entities = [
            Flower::class],
        version = 4,
        exportSchema = false
)
abstract class PovioDb : RoomDatabase() {

    abstract fun flowerDao(): FlowersDao
}