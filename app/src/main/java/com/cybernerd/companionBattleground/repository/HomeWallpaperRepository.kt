package com.cybernerd.companionBattleground.repository

import androidx.lifecycle.MutableLiveData
import com.cybernerd.companionBattleground.model.HomeNewsListModel
import com.cybernerd.companionBattleground.model.WallpapesModel
import com.cybernerd.companionBattleground.network.CompanionApi
import com.cybernerd.companionBattleground.utils.debug
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeWallpaperRepository {
    val wallpaperLiveData = MutableLiveData<WallpapesModel>()
    val showProgress = MutableLiveData<Boolean>()

    fun getWallpaper() {
        showProgress.value = true
        CompanionApi().getWallpapers().enqueue(object : Callback<WallpapesModel> {
            override fun onResponse(
                call: Call<WallpapesModel>,
                response: Response<WallpapesModel>,
            ) {
                wallpaperLiveData.value = response.body()
                debug("NewsRepositorySuccess : call = ${response.body()}")
                showProgress.value = false

            }

            override fun onFailure(call: Call<WallpapesModel>, t: Throwable) {
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