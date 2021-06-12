package com.cybernerd.bgmiguide.repository

import androidx.lifecycle.MutableLiveData
import com.cybernerd.bgmiguide.model.HomeNewsListModel
import com.cybernerd.bgmiguide.network.CompanionApi
import com.cybernerd.bgmiguide.utils.debug
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