package com.cybernerd404.bgmiguide.view.home.topvideos

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cybernerd404.bgmiguide.model.HomeVideosModel
import com.cybernerd404.bgmiguide.repository.HomeVideoRepository

class HomeVideoViewModel(context: Context) : ViewModel() {

    var homeVideoRepository = HomeVideoRepository()
    var videoLiveData = MutableLiveData<HomeVideosModel>()
    val showprogress: LiveData<Boolean>


    init {
        this.showprogress = homeVideoRepository.showProgress
        this.videoLiveData = homeVideoRepository.videoLiveData
    }

    fun getVideo() {
        homeVideoRepository.getVideo()
    }

}