package com.example.gmassignment.carListing.data.api

import com.example.gmassignment.carListing.data.response.ResponseCarListing
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by TENZING SHERPA on 4/10/22.
 * Email: tenzingherpaaa@gmail.com
 */
class CarListingApiImpl @Inject constructor(
    private val carListingApi: CarListingApi
) {
    fun getCarList(): Single<ResponseCarListing> {
        return carListingApi.getCarListing()
    }
}