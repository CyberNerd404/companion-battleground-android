package com.cybernerd.bgmiguide.view.home.topvideos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cybernerd.bgmiguide.model.HomeVideosModel
import com.cybernerd.bgmiguide.repository.HomeVideoRepository

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