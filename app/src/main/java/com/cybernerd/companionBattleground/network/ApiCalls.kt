package com.cybernerd.companionBattleground.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://github.com/Ashutoshwahane/"
interface ApiCalls {
    companion object{
        operator fun invoke(): ApiCalls{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiCalls::class.java)
        }
    }

    @GET
    fun getDemoData(): Any
}