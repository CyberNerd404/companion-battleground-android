package com.cybernerd404.bgmiguide.network

import android.content.Context
import android.net.ConnectivityManager
import com.cybernerd404.bgmiguide.utils.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(context: Context): Interceptor {

    val applicationContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isInternetAvailable()){
            throw NoInternetException("Make sure you have active Internet Connection")
        }

        return chain.proceed(chain.request())
    }

    fun isInternetAvailable(): Boolean{
        val connectivityManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        connectivityManager.activeNetworkInfo.also {
            return it != null && it.isConnected
        }
    }
}