package com.example.carfaxassignment.carListing.data.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Listings(
    val uuid: Int,
    val advantage: Boolean,
    val backfill: Boolean,
    val badge: String,
    val bedLength: String,
    val bodytype: String,
    val cabType: String,
    val certified: Boolean,
    val currentPrice: Double,
    val dealer: Dealer,
    val dealerType: String,
    val displacement: String,
    val distanceToDealer: Double,
    val drivetype: String,
    val engine: String,
    val exteriorColor: String,
    val firstSeen: String,
    val followCount: Int,
    val following: Boolean,
    val fuel: String,
    val hasViewed: Boolean,
    val id: String,
    val imageCount: Int,
    val images: Images,
    val interiorColor: String,
    val isEnriched: Boolean,
    val listPrice: Double,
    val make: String,
    val mileage: Long,
    val model: String,
    val monthlyPaymentEstimate: MonthlyPaymentEstimate,
    val mpgCity: Int,
    val mpgHighway: Int,
    val noAccidents: Boolean,
    val oneOwner: Boolean,
    val onePrice: Double,
    val onlineOnly: Boolean,
    val personalUse: Boolean,
    val recordType: String,
    val sentLead: Boolean,
    val serviceRecords: Boolean,
    val sortScore: Double,
    val stockNumber: String,
    val subTrim: String,
    val topOptions: List<String>,
    val transmission: String,
    val trim: String,
    val vdpUrl: String,
    val vehicleCondition: String,
    val vin: String,
    val year: Int
): Parcelable