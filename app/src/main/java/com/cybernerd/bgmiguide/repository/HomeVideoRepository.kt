package com.cybernerd.bgmiguide.repository

import androidx.lifecycle.MutableLiveData
import com.cybernerd.bgmiguide.model.HomeVideosModel
import com.cybernerd.bgmiguide.model.Token
import com.cybernerd.bgmiguide.network.CompanionApi
import com.cybernerd.bgmiguide.utils.debug
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeVideoRepository() {
    val videoLiveData = MutableLiveData<HomeVideosModel>()
    val showProgress = MutableLiveData<Boolean>()

    fun getVideo() {
        showProgress.value = true
        CompanionApi().getHomeVideos().enqueue(object : Callback<HomeVideosModel> {
            override fun onResponse(
                call: Call<HomeVideosModel>,
                response: Response<HomeVideosModel>,
            ) {
                if (response.isSuccessful) {
                    videoLiveData.value = response.body()
                    showProgress.value = false
                }


            }

            override fun onFailure(call: Call<HomeVideosModel>, t: Throwable) {
                try {
                    showProgress.value = false
                } catch (e: Exception) {
                    showProgress.value = false
                }

            }

        })

    }
}