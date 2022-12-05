package com.tridiv.tridivroymisltest.domain

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build

open class CommonUseCase {
    fun isNetworkAvailable(context: Context): Boolean {
        try {
            val connectivityManager: ConnectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                val capabilities =
                    connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork) as NetworkCapabilities
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            } else {
                try {
                    val activeNetworkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
                    if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                        return true
                    }
                } catch (exception: Exception) {
                }
            }
            return false
        } catch (e: Exception) {
            return false
        }
    }
}