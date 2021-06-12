package com.cybernerd.bgmiguide.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network

class NetworkManager(context: Context) : ConnectivityManager.NetworkCallback() {

    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    var isNetworkAvailable = false
    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        isNetworkAvailable = true
    }

    override fun onUnavailable() {
        super.onUnavailable()
        isNetworkAvailable = false
    }
}