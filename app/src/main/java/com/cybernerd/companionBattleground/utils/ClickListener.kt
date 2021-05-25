package com.cybernerd.companionBattleground.utils

import com.cybernerd.companionBattleground.model.HomeNewsModel
import com.cybernerd.companionBattleground.model.Notification
import com.cybernerd.companionBattleground.model.Videos
import com.cybernerd.companionBattleground.model.WallpaperModel

interface ClickListener {


    // click listener for news recyclerview
    fun homeNewsClickListener(homeNewsModel: HomeNewsModel)

    // click listener for youtude recyclerview
    fun homeVideoClickListener(videoMode: Videos)

    // click listener for settings recyclerview
    fun settingsClickListener(position: Int)

    // click listener for notification
    fun notificationClickListener(notification: Notification)

    // click listener for wallpaper
    fun wallpaperClickListener(wallpaperModel: WallpaperModel, position: Int)

}