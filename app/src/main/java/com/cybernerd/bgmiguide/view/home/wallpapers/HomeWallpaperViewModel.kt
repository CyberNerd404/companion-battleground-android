package com.cybernerd.bgmiguide.view.home.wallpapers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cybernerd.bgmiguide.model.WallpapersModel
import com.cybernerd.bgmiguide.repository.HomeWallpaperRepository

class HomeWallpaperViewModel : ViewModel() {

    val wallpaperRepository = HomeWallpaperRepository()
    var wallpaperLiveData = MutableLiveData<WallpapersModel>()
    val showprogress: LiveData<Boolean>


    init {
        this.showprogress = wallpaperRepository.showProgress
        this.wallpaperLiveData = wallpaperRepository.wallpaperLiveData
    }

    fun getWallpapers() {
        wallpaperRepository.getWallpaper()
    }

}