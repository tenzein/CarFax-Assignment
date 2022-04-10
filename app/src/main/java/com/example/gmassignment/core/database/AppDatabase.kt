package com.example.gmassignment.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gmassignment.carListing.data.database.CarListingDao
import com.example.gmassignment.carListing.domain.model.CarData

/**
 * Created by TENZING SHERPA on 4/10/22.
 * Email: tenzingherpaaa@gmail.com
 */
@Database(entities = [CarData::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun carListingDao(): CarListingDao
}