package com.example.carfaxassignment.carListing.data.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Images(
    val baseUrl: String,
    val firstPhoto: FirstPhoto,
    val large: List<String>,
    val medium: List<String>,
    val small: List<String>
): Parcelable