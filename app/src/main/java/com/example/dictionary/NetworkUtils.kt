package com.example.dictionary

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkInfo
import android.net.NetworkRequest
import androidx.core.content.getSystemService

//fun isOnline(context: Context): Boolean {
//    var isOnline = true
//    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//    val request = NetworkRequest.Builder().build()
//
//    connectivityManager.registerNetworkCallback(request, object : ConnectivityManager.NetworkCallback() {
//        override fun onAvailable(network: Network) {
//            isOnline = true
//        }
//
//        override fun onLost(network: Network) {
//            isOnline = false
//        }
//
//        override fun onUnavailable() {
//            isOnline = false
//        }
//    })
//    return isOnline
//}
fun isOnline(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val netInfo: NetworkInfo?
    netInfo = connectivityManager.activeNetworkInfo
    return netInfo != null && netInfo.isConnected
}