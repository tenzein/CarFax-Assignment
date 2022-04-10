package com.example.gmassignment.carListing.data.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Dealer(
    val address: String,
    val carfaxId: String,
    val city: String,
    val dealerAverageRating: Double,
    val dealerInventoryUrl: String,
    val dealerReviewCount: Int,
    val dealerReviewDate: String,
    val dealerReviewRating: Int,
    val dealerReviewReviewer: String,
    val latitude: String,
    val longitude: String,
    val name: String,
    val onlineOnly: Boolean,
    val phone: String,
    val state: String,
    val zip: String
): Parcelable