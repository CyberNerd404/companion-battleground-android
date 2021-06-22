package com.cybernerd404.bgmiguide.utils

import com.cybernerd404.bgmiguide.model.*

interface NewsListener{
    fun newsListener(homeNewsModel: HomeNewsModel)
}

interface VideoListener{
    fun videoListener(videoMode: Videos)
}

interface SettingListener{
    fun settingsListener(position: Int)
}

interface NotificationListener{
    fun notificationListener(notification: Notification)
}

interface InformationListener{
    fun informationCategoryListener(position: Int)
}

interface WallpaperListener{
    fun wallpaperListener(wallpaper: WallpapersModel, position: Int)
}