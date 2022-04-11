package com.example.carfaxassignment.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.carfaxassignment.carListing.data.database.CarListingDao
import com.example.carfaxassignment.carListing.domain.model.CarData

/**
 * Created by TENZING SHERPA on 4/10/22.
 * Email: tenzingherpaaa@gmail.com
 */
@Database(entities = [CarData::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun carListingDao(): CarListingDao
}