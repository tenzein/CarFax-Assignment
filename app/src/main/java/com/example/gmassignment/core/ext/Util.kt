package com.example.gmassignment.core.ext

import kotlin.math.ln
import kotlin.math.pow

/**
 * Created by TENZING SHERPA on 4/10/22.
 * Email: tenzingherpaaa@gmail.com
 */
object Util {

    /**
     * function to convert mileage to k mi
     * */
    fun getFormattedNumber(count: Long): String {
        if (count < 1000) return "" + count
        val exp = (ln(count.toDouble()) / ln(1000.0)).toInt()
        return String.format("%.1f %c", count / 1000.0.pow(exp.toDouble()), "kMGTPE"[exp - 1])
    }
}