package com.cybernerd.companionBattleground.view.setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.cybernerd.companionBattleground.R
import com.cybernerd.companionBattleground.adapter.ThemeAdapter
import com.cybernerd.companionBattleground.model.HomeNewsModel
import com.cybernerd.companionBattleground.model.Notification
import com.cybernerd.companionBattleground.model.Videos
import com.cybernerd.companionBattleground.model.WallpaperModel
import com.cybernerd.companionBattleground.utils.ClickListener
import kotlinx.android.synthetic.main.activity_themes.*
import kotlinx.android.synthetic.main.fragment_home_news.*

class WallpaperActivity : AppCompatActivity() , ClickListener {
    lateinit var themeAdapter: ThemeAdapter
    var wallpaperList = arrayListOf<WallpaperModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallpaper)

        themeAdapter = ThemeAdapter(this, this)
        wallpaper_rv.adapter = themeAdapter


        wallpaper_rv.layoutManager = GridLayoutManager(this, 2)
        wallpaperList.add(WallpaperModel("http://dreamicus.com/data/yellow/yellow-08.jpg","Hi"))
        wallpaperList.add(WallpaperModel("https://hddesktopwallpapers.in/wp-content/uploads/2015/09/green-macro-flowers.jpg","Hi"))
        wallpaperList.add(WallpaperModel("https://www.drodd.com/images16/green-color6.jpg","Hi"))
        wallpaperList.add(WallpaperModel("https://wonderfulengineering.com/wp-content/uploads/2016/02/mobile-wallpaper-3.jpg","Hi"))
        wallpaperList.add(WallpaperModel("https://wonderfulengineering.com/wp-content/uploads/2016/02/mobile-wallpaper-3.jpg","Hi"))
        themeAdapter.setWallpaperGrid(wallpaperList)
    }


    override fun homeNewsClickListener(homeNewsModel: HomeNewsModel) {
        TODO("Not yet implemented")
    }

    override fun homeVideoClickListener(videoMode: Videos) {
        TODO("Not yet implemented")
    }

    override fun settingsClickListener(position: Int) {
        TODO("Not yet implemented")
    }

    override fun notificationClickListener(notification: Notification) {
        TODO("Not yet implemented")
    }
}