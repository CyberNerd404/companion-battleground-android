package com.cybernerd404.bgmiguide.network

import com.cybernerd404.bgmiguide.model.*
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

const val BASE_URL = "https://bgmiguide.azurewebsites.net/"

interface CompanionApi {


    companion object {
        operator fun invoke(): CompanionApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CompanionApi::class.java)
        }
    }

    @GET("/api/v1/videos")
    fun getHomeVideos(
        /*@Header("Authorization") token: String,*/
    ): Call<HomeVideosModel>


    @GET("/api/v1/news")
    fun getHomeNews(
        /*@Header("Authorization") token: String,*/
    ): Call<HomeNewsListModel>

    @GET("/api/v1/notifications")
    fun getNotifications(
        /*@Header("Authorization") token: String,*/
    ): Call<NotificationModel>

    @GET("/api/v1/wallpapers")
    fun getWallpapers(
        /*@Header("Authorization") token: String,*/
    ): Call<WallpapersModel>


    @Headers("Content-Type: application/json")
    @POST("api/v1/device")
    fun sendToken(
        @Body jsonObject: JsonObject): Call<Token>

}