package com.cybernerd.bgmiguide.view.home.wallpapers

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cybernerd.bgmiguide.model.WallpapersModel
import com.cybernerd.bgmiguide.repository.HomeNewsRepository
import com.cybernerd.bgmiguide.repository.HomeVideoRepository
import com.cybernerd.bgmiguide.repository.HomeWallpaperRepository
import com.cybernerd.bgmiguide.utils.SessionManager

class HomeWallpaperViewModel(context: Context) : ViewModel() {

    lateinit var wallpaperRepository: HomeWallpaperRepository
    var wallpaperLiveData = MutableLiveData<WallpapersModel>()
    val showprogress: LiveData<Boolean>


    init {
        wallpaperRepository = SessionManager(context).fetchAuthToken()?.let {
            HomeWallpaperRepository(it)
        }!!
        this.showprogress = wallpaperRepository.showProgress
        this.wallpaperLiveData = wallpaperRepository.wallpaperLiveData
    }

    fun getWallpapers() {
        wallpaperRepository.getWallpaper()
    }

}