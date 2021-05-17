package com.cybernerd.companionBattleground.network

import com.cybernerd.companionBattleground.model.HomeNewsListModel
import com.cybernerd.companionBattleground.model.HomeVideosModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://companion-battleground.herokuapp.com/"
interface CompanionApi {


    companion object{
        operator fun invoke(): CompanionApi{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CompanionApi::class.java)
        }
    }

    @GET("/api/v1/videos")
    fun getHomeVideos(): Call<HomeVideosModel>

    @GET("/api/v1/news")
    fun getHomeNews(): Call<HomeNewsListModel>


}