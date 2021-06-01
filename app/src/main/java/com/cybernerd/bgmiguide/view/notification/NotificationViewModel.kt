package com.cybernerd.bgmiguide.view.notification

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cybernerd.bgmiguide.model.NotificationModel
import com.cybernerd.bgmiguide.repository.HomeWallpaperRepository
import com.cybernerd.bgmiguide.repository.NotificationRepository
import com.cybernerd.bgmiguide.utils.SessionManager

class NotificationViewModel(context: Context): ViewModel() {

    lateinit var repository: NotificationRepository
    var liveData = MutableLiveData<NotificationModel>()
    val showprogress : LiveData<Boolean>

    init {
        repository = SessionManager(context).fetchAuthToken()?.let {
            NotificationRepository(it)
        }!!
        this.showprogress = repository.showProgress
        this.liveData = repository.notificationLiveData
    }

    fun getNotifications(){
        repository.getNotifications()
    }

}