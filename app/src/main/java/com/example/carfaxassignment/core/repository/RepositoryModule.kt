package com.example.carfaxassignment.core.repository

import com.example.carfaxassignment.carListing.data.respository.CarListRepositoryImpl
import com.example.carfaxassignment.carListing.domain.repository.CarListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by TENZING SHERPA on 4/10/22.
 * Email: tenzingherpaaa@gmail.com
 */
@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {
    /**
     * provider for building the repository for PhotosRepository
     * @param repo PhotosRepositoryImpl
     * */
    @Provides
    fun provideCarListRepository(repo: CarListRepositoryImpl): CarListRepository = repo

}