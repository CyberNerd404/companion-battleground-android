package com.cybernerd.companionBattleground.network

import com.cybernerd.companionBattleground.model.*
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

const val BASE_URL = "https://bgmiguide.azurewebsites.net/"
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

    @GET("/api/v1/notifications")
    fun getNotifications(): Call<NotificationModel>

    @GET("/api/v1/wallpapers")
    fun getWallpapers(): Call<WallpapesModel>


    @Headers("Content-Type: application/json")
    @POST("api/v1/device")
    fun sendToken(@Body jsonObject: JsonObject): Call<Token>

}