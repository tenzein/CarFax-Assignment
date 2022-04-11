package com.example.carfaxassignment.carListing.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/**
 * Created by TENZING SHERPA on 4/10/22.
 * Email: tenzingherpaaa@gmail.com
 */

@Parcelize
@Entity(tableName = "CarData")
data class CarData(
    @PrimaryKey
    val id: String = "",
    var dealerPhone:String? = null,
    var vin:String? = null,
    var mileage: Long? = null,
    var currentPrice: Double? = null,
    var exteriorColor: String? = null,
    var interiorColor: String? = null,
    var engine: String? = null,
    var driveType: String? = null,
    var transmission: String? = null,
    var bodyType: String? = null,
    var imageUrl: String? = null,
    var year:Int ? = null,
    var make: String? = null,
    var model: String? = null,
    var address: String? = null,
    var trim: String? = null,
    var fuel: String? = null


    ) : Parcelable
