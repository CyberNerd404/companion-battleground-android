package com.cybernerd.companionBattleground.view.home.topvideos

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cybernerd.companionBattleground.model.HomeVideosModel
import com.cybernerd.companionBattleground.repository.HomeVideoRepository

class HomeVideoViewModel: ViewModel() {

    val homeVideoRepository = HomeVideoRepository()
    var videoLiveData = MutableLiveData<HomeVideosModel>()
    val showprogress : LiveData<Boolean>


    init {
        this.showprogress = homeVideoRepository.showProgress
        this.videoLiveData = homeVideoRepository.videoLiveData
    }

    fun getVideo(){
        homeVideoRepository.getVideo()
    }

}