package com.cybernerd.bgmiguide.view.home.topvideos

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cybernerd.bgmiguide.model.HomeVideosModel
import com.cybernerd.bgmiguide.repository.HomeNewsRepository
import com.cybernerd.bgmiguide.repository.HomeVideoRepository
import com.cybernerd.bgmiguide.utils.SessionManager

class HomeVideoViewModel(context: Context): ViewModel() {

    lateinit var homeVideoRepository: HomeVideoRepository
    var videoLiveData = MutableLiveData<HomeVideosModel>()
    val showprogress : LiveData<Boolean>


    init {
        homeVideoRepository = SessionManager(context).fetchAuthToken()?.let {
            HomeVideoRepository(it)
        }!!
        this.showprogress = homeVideoRepository.showProgress
        this.videoLiveData = homeVideoRepository.videoLiveData
    }

    fun getVideo(){
        homeVideoRepository.getVideo()
    }

}