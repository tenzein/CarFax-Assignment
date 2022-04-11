package com.example.carfaxassignment.carListing.data.api

import com.example.carfaxassignment.carListing.data.response.ResponseCarListing
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