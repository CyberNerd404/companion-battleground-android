package com.cybernerd404.bgmiguide.repository

import androidx.lifecycle.MutableLiveData
import com.cybernerd404.bgmiguide.model.NotificationModel
import com.cybernerd404.bgmiguide.network.CompanionApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotificationRepository {
    val notificationLiveData = MutableLiveData<NotificationModel>()
    val showProgress = MutableLiveData<Boolean>()

    fun getNotifications() {
        showProgress.value = true
        CompanionApi().getNotifications().enqueue(object : Callback<NotificationModel> {
            override fun onResponse(
                call: Call<NotificationModel>,
                response: Response<NotificationModel>,
            ) {
                if (response.isSuccessful) {
                    notificationLiveData.value = response.body()
                    showProgress.value = false
                }

            }

            override fun onFailure(call: Call<NotificationModel>, t: Throwable) {
                try {
                    showProgress.value = false
                } catch (e: Exception) {
                    showProgress.value = false
                }
            }

        })
    }
}