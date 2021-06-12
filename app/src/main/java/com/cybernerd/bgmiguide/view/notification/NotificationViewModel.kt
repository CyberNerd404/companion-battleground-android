package com.cybernerd.bgmiguide.view.notification

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cybernerd.bgmiguide.model.NotificationModel
import com.cybernerd.bgmiguide.repository.NotificationRepository

class NotificationViewModel(context: Context): ViewModel() {

    var repository =  NotificationRepository()
    var liveData = MutableLiveData<NotificationModel>()
    val showprogress : LiveData<Boolean>

    init {
        this.showprogress = repository.showProgress
        this.liveData = repository.notificationLiveData
    }

    fun getNotifications(){
        repository.getNotifications()
    }

}