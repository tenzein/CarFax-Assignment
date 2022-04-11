package com.example.carfaxassignment.carListing.data.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MonthlyPaymentEstimate(
    val downPaymentAmount: Double,
    val downPaymentPercent: Double,
    val interestRate: Double,
    val loanAmount: Double,
    val monthlyPayment: Double,
    val price: Double,
    val termInMonths: Int
): Parcelable