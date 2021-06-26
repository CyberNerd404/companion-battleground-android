package com.cybernerd404.bgmiguide.view.quiz

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cybernerd404.bgmiguide.model.NotificationModel
import com.cybernerd404.bgmiguide.repository.NotificationRepository

class QuizViewModel(context: Context): ViewModel() {

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