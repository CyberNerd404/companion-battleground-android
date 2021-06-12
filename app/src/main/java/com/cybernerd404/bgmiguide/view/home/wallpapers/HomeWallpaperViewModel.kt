package com.cybernerd404.bgmiguide.view.home.wallpapers

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cybernerd404.bgmiguide.model.WallpapersModel
import com.cybernerd404.bgmiguide.repository.HomeWallpaperRepository

class HomeWallpaperViewModel(context: Context) : ViewModel() {

    var wallpaperRepository = HomeWallpaperRepository()
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