package com.example.carfaxassignment.carListing.data.respository

import com.example.carfaxassignment.carListing.data.api.CarListingApiImpl
import com.example.carfaxassignment.carListing.data.database.CarListingDao
import com.example.carfaxassignment.carListing.data.response.ResponseCarListing
import com.example.carfaxassignment.carListing.domain.model.CarData
import com.example.carfaxassignment.carListing.domain.repository.CarListRepository
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by TENZING SHERPA on 4/10/22.
 * Email: tenzingherpaaa@gmail.com
 */
class CarListRepositoryImpl @Inject constructor(
    private val remoteSource: CarListingApiImpl,
    private val localSource: CarListingDao
) : CarListRepository {
    override fun getCarListFromDb(): Maybe<List<CarData>?> {
       return  localSource.getAllCarList()
    }

    override fun getCarList(): Single<ResponseCarListing> {
        return remoteSource.getCarList()
    }

    override fun insertAllCarList(data: List<CarData>): Maybe<List<Long>> {
       return  localSource.insertCarList(data)
    }

    override fun deleteAllCarList(): Single<Int> {
       return localSource.deleteAllCars()
    }
}