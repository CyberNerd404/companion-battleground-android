package com.cybernerd404.bgmiguide.network

import com.cybernerd404.bgmiguide.model.GunsModelX
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val base_url = "https://raw.githubusercontent.com/"

interface junkApi {


    companion object {
        operator fun invoke(): junkApi {
            return Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(junkApi::class.java)
        }
    }

    @GET("/Ashutoshwahane/junk-data/main/guns.json")
    fun getGuns(

    ): Call<GunsModelX>

}

