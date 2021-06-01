package com.cybernerd.bgmiguide.repository

import androidx.lifecycle.MutableLiveData
import com.cybernerd.bgmiguide.model.NotificationModel
import com.cybernerd.bgmiguide.network.CompanionApi
import com.cybernerd.bgmiguide.utils.debug
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotificationRepository {
    val notificationLiveData = MutableLiveData<NotificationModel>()
    val showProgress = MutableLiveData<Boolean>()

    fun getNotifications(){
        showProgress.value = true
        CompanionApi().getNotifications().enqueue(object : Callback<NotificationModel>{
            override fun onResponse(
                call: Call<NotificationModel>,
                response: Response<NotificationModel>
            ) {
                notificationLiveData.value = response.body()
                showProgress.value = false

            }

            override fun onFailure(call: Call<NotificationModel>, t: Throwable) {
                try {
                    showProgress.value = false
                }catch (e:Exception){
                    showProgress.value = false
                }
            }

        })
    }
}