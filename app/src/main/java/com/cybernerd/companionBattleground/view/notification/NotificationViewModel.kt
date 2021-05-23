package com.cybernerd.companionBattleground.view.notification

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cybernerd.companionBattleground.model.HomeNewsListModel
import com.cybernerd.companionBattleground.model.NotificationModel
import com.cybernerd.companionBattleground.repository.HomeNewsRepository
import com.cybernerd.companionBattleground.repository.NotificationRepository

class NotificationViewModel: ViewModel() {

    val repository = NotificationRepository()
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