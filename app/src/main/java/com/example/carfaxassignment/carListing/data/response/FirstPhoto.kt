package com.example.carfaxassignment.carListing.data.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FirstPhoto(
    val large: String,
    val medium: String,
    val small: String
): Parcelable