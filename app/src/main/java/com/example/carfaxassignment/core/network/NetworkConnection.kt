package com.example.carfaxassignment.core.network

import java.io.IOException


/**
 * Created by TENZING SHERPA on 4/2/22.
 * Email: tenzingherpaaa@gmail.com
 */
/**
 * object with network check function
 * return boolean whether network is available or not
 * */
object NetworkConnection {
    fun checkNetwork(): Boolean {
        val runtime = Runtime.getRuntime()
        try {
            val ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8")
            val exitValue = ipProcess.waitFor()
            return exitValue == 0
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        return false
    }
}