package com.cybernerd.bgmiguide.repository

import androidx.lifecycle.MutableLiveData
import com.cybernerd.bgmiguide.model.WallpapersModel
import com.cybernerd.bgmiguide.network.CompanionApi
import com.cybernerd.bgmiguide.utils.debug
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeWallpaperRepository(val token: String) {
    val wallpaperLiveData = MutableLiveData<WallpapersModel>()
    val showProgress = MutableLiveData<Boolean>()

    fun getWallpaper() {
        showProgress.value = true
        CompanionApi().getWallpapers(token).enqueue(object : Callback<WallpapersModel> {
            override fun onResponse(
                call: Call<WallpapersModel>,
                response: Response<WallpapersModel>,
            ) {
                wallpaperLiveData.value = response.body()
                showProgress.value = false

            }

            override fun onFailure(call: Call<WallpapersModel>, t: Throwable) {
                try {
                    showProgress.value = false
                } catch (e: Exception) {
                    showProgress.value = false
                }
            }

        })
    }
}