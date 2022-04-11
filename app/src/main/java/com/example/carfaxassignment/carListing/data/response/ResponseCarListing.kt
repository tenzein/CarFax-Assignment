package com.example.carfaxassignment.carListing.data.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseCarListing(
    val backfillCount: Int,
    val dealerNewCount: Int,
    val dealerUsedCount: Int,
    val enhancedCount: Int,
    val listings: List<Listings>,
    val page: Int,
    val pageSize: Int,
    val searchArea: SearchArea,
    val totalListingCount: Int,
    val totalPageCount: Int
): Parcelable