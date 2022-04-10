package com.example.gmassignment.carListing.data.api

import com.example.gmassignment.carListing.data.response.ResponseCarListing
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Created by TENZING SHERPA on 4/10/22.
 * Email: tenzingherpaaa@gmail.com
 */
interface CarListingApi {

 @GET("assignment.json")
 fun getCarListing(): Single<ResponseCarListing>

}