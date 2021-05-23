package com.cybernerd.companionBattleground.repository

import androidx.lifecycle.MutableLiveData
import com.cybernerd.companionBattleground.model.HomeNewsListModel
import com.cybernerd.companionBattleground.model.NotificationModel
import com.cybernerd.companionBattleground.network.CompanionApi
import com.cybernerd.companionBattleground.utils.debug
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
                debug("NewsRepositorySuccess : call = ${response.body()}")
                showProgress.value = false

            }

            override fun onFailure(call: Call<NotificationModel>, t: Throwable) {
                try {
                    showProgress.value = false
                    error("NewsRepositoryFailure : error = ${t.message}")
                }catch (e:Exception){
                    showProgress.value = false
                }
            }

        })
    }
}