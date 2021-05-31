package com.cybernerd.bgmiguide.repository

import androidx.lifecycle.MutableLiveData
import com.cybernerd.bgmiguide.model.WallpapersModel
import com.cybernerd.bgmiguide.network.CompanionApi
import com.cybernerd.bgmiguide.utils.debug
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
                wallpaperLiveData.value = response.body()
                debug("NewsRepositorySuccess : call = ${response.body()}")
                showProgress.value = false

            }

            override fun onFailure(call: Call<WallpapersModel>, t: Throwable) {
                try {
                    showProgress.value = false
                    error("NewsRepositoryFailure : error = ${t.message}")
                } catch (e: Exception) {
                    showProgress.value = false
                }
            }

        })
    }
}