package com.cybernerd404.bgmiguide.repository

import androidx.lifecycle.MutableLiveData
import com.cybernerd404.bgmiguide.model.HomeNewsListModel
import com.cybernerd404.bgmiguide.network.CompanionApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeNewsRepository {
    val newsLiveData = MutableLiveData<HomeNewsListModel>()
    val showProgress = MutableLiveData<Boolean>()

    fun getNews() {
        showProgress.value = true
        CompanionApi().getHomeNews().enqueue(object : Callback<HomeNewsListModel> {
            override fun onResponse(
                call: Call<HomeNewsListModel>,
                response: Response<HomeNewsListModel>,
            ) {
                if (response.isSuccessful) {
                    newsLiveData.value = response.body()
                    showProgress.value = false
                }


            }

            override fun onFailure(call: Call<HomeNewsListModel>, t: Throwable) {
                try {
                    showProgress.value = false
                } catch (e: Exception) {
                    showProgress.value = false
                }
            }

        })
    }
}