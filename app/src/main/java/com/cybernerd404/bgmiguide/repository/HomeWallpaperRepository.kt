package com.cybernerd404.bgmiguide.repository

import androidx.lifecycle.MutableLiveData
import com.cybernerd404.bgmiguide.model.WallpapersModel
import com.cybernerd404.bgmiguide.network.CompanionApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeWallpaperRepository {
    val wallpaperLiveData = MutableLiveData<WallpapersModel>()
    val showProgress = MutableLiveData<Boolean>()

    fun getWallpaper() {
        showProgress.value = true
        CompanionApi().getWallpapers().enqueue(object : Callback<WallpapersModel> {
            override fun onResponse(
                call: Call<WallpapersModel>,
                response: Response<WallpapersModel>,
            ) {
                if (response.isSuccessful) {
                    wallpaperLiveData.value = response.body()
                    showProgress.value = false
                }

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