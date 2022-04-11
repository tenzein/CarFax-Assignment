package com.example.carfaxassignment.core.database

import android.content.Context
import androidx.room.Room
import com.example.carfaxassignment.carListing.data.database.CarListingDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by TENZING SHERPA on 4/10/22.
 * Email: tenzingherpaaa@gmail.com
 */
@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    /**
     * provider for building the Room Database
     * extends AppDatabase
     * allows multiple queries
     * follow fallback destruction migration (auto migration preferred)
     * @param context applicationContext
     * */
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "RR-DATA.db"
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    /**
     * provides interface functions to get all local background operations (insert, delete, update)
     * @param database Room Database instance
     * */
    @Provides
    fun providePhotosDao(database: AppDatabase): CarListingDao {
        return database.carListingDao()
    }
}