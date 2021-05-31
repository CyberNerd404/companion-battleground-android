package com.cybernerd.bgmiguide.repository

import androidx.lifecycle.MutableLiveData
import com.cybernerd.bgmiguide.model.HomeVideosModel
import com.cybernerd.bgmiguide.network.CompanionApi
import com.cybernerd.bgmiguide.utils.debug
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeVideoRepository {
    val videoLiveData = MutableLiveData<HomeVideosModel>()
    val showProgress = MutableLiveData<Boolean>()

    fun getVideo() {
        showProgress.value = true
        CompanionApi().getHomeVideos().enqueue(object : Callback<HomeVideosModel> {
            override fun onResponse(
                call: Call<HomeVideosModel>,
                response: Response<HomeVideosModel>
            ) {
                videoLiveData.value = response.body()
                debug("RepositorySuccess : call = ${response.body()}")
                showProgress.value = false


            }

            override fun onFailure(call: Call<HomeVideosModel>, t: Throwable) {
                try {
                    showProgress.value = false
                    error("RepositoryFailure : error = ${t.message}")
                } catch (e: Exception) {
                    showProgress.value = false
                }

            }

        })

    }
}