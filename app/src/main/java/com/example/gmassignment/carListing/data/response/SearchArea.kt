package com.example.gmassignment.carListing.data.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchArea(
    val city: String,
    val dynamicRadii: List<Double>,
    val dynamicRadius: Boolean,
    val latitude: Double,
    val longitude: Double,
    val radius: Double,
    val state: String,
    val zip: String
): Parcelable