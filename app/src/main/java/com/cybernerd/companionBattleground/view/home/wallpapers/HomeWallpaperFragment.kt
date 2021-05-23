package com.cybernerd.companionBattleground.view.home.wallpapers

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.cybernerd.companionBattleground.R
import com.cybernerd.companionBattleground.adapter.ThemeAdapter
import com.cybernerd.companionBattleground.model.HomeNewsModel
import com.cybernerd.companionBattleground.model.Notification
import com.cybernerd.companionBattleground.model.Videos
import com.cybernerd.companionBattleground.model.WallpaperModel
import com.cybernerd.companionBattleground.utils.ClickListener
import com.cybernerd.companionBattleground.view.BaseFragment
import kotlinx.android.synthetic.main.fragment_home_wallpaper.*
import kotlinx.android.synthetic.main.fragment_setting.*

class HomeWallpaperFragment : BaseFragment(),ClickListener {


    lateinit var themeAdapter: ThemeAdapter
    var wallpaperList = arrayListOf<WallpaperModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_wallpaper, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        themeAdapter = ThemeAdapter(requireContext(), this)
        wallpaper_rv.adapter = themeAdapter


        wallpaper_rv.layoutManager = GridLayoutManager(requireContext(), 2)
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

    }

    override fun notificationClickListener(notification: Notification) {
        TODO("Not yet implemented")
    }

    override fun wallpaperClickListener(wallpaperModel: WallpaperModel) {
        Intent(requireContext(), WallpaperActivity::class.java).apply {
            putExtra("imageUrl", wallpaperModel.imageLink)
            putExtra("imageTitle", wallpaperModel.title)
            startActivity(this)
        }
    }
}