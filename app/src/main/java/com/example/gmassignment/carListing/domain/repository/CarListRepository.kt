package com.example.gmassignment.carListing.domain.repository

import com.example.gmassignment.carListing.data.response.ResponseCarListing
import com.example.gmassignment.carListing.domain.model.CarData
import io.reactivex.Maybe
import io.reactivex.Single

/**
 * Created by TENZING SHERPA on 4/10/22.
 * Email: tenzingherpaaa@gmail.com
 */
interface CarListRepository {
 fun getCarListFromDb(): Maybe<List<CarData>?> // get list of cars from local database
 fun getCarList(): Single<ResponseCarListing> // get list of cars from remote server
 fun insertAllCarList(data: List<CarData>): Maybe<List<Long>> // insert all cars in local database
 fun deleteAllCarList(): Single<Int> // delete all cars from local database

}