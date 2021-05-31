package com.cybernerd.companionBattleground.view.home.wallpapers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cybernerd.companionBattleground.model.WallpapersModel
import com.cybernerd.companionBattleground.repository.HomeWallpaperRepository

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