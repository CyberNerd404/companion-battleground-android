package com.cybernerd.companionBattleground.view.home.wallpapers

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.cybernerd.companionBattleground.R
import com.cybernerd.companionBattleground.adapter.ThemeAdapter
import com.cybernerd.companionBattleground.model.HomeNewsModel
import com.cybernerd.companionBattleground.model.Notification
import com.cybernerd.companionBattleground.model.Videos
import com.cybernerd.companionBattleground.model.WallpaperModel
import com.cybernerd.companionBattleground.utils.ClickListener
import com.cybernerd.companionBattleground.utils.debug
import com.cybernerd.companionBattleground.view.BaseFragment
import com.cybernerd.companionBattleground.view.home.topvideos.HomeVideoViewModel
import kotlinx.android.synthetic.main.fragment_home_top_videos.*
import kotlinx.android.synthetic.main.fragment_home_wallpaper.*

class HomeWallpaperFragment : BaseFragment(),ClickListener {


    lateinit var themeAdapter: ThemeAdapter
    var wallpaperList = arrayListOf<WallpaperModel>()

    private val viewModel: HomeWallpaperViewModel by lazy {
        HomeWallpaperViewModel()
    }

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

        viewModel.getWallpapers()
        viewModel.wallpaperLiveData.observe(viewLifecycleOwner, Observer {
            debug("HomeFragment : Wallpaper data = ${it.wallpapers}")
            themeAdapter.setWallpaperGrid(it.wallpapers)
        })

        /*viewModel.showprogress.observe(viewLifecycleOwner, Observer {
            if (it) {
                video_progressbar.visibility = View.VISIBLE
            } else {
                video_progressbar.visibility = View.GONE
            }
        })*/



        wallpaper_rv.layoutManager = GridLayoutManager(requireContext(), 2)
        wallpaperList.add(WallpaperModel("https://raw.githubusercontent.com/Ashutoshwahane/junk-data/main/resource1.jpeg","1"))
        wallpaperList.add(WallpaperModel("https://raw.githubusercontent.com/Ashutoshwahane/junk-data/main/resource2.jpeg","2"))
        wallpaperList.add(WallpaperModel("https://raw.githubusercontent.com/Ashutoshwahane/junk-data/main/resource3.jpeg","3"))
        wallpaperList.add(WallpaperModel("https://raw.githubusercontent.com/Ashutoshwahane/junk-data/main/resource4.jpeg","4"))
        wallpaperList.add(WallpaperModel("https://raw.githubusercontent.com/Ashutoshwahane/junk-data/main/resource5.jpeg","5"))
        wallpaperList.add(WallpaperModel("https://raw.githubusercontent.com/Ashutoshwahane/junk-data/main/resource2.jpeg","6"))
        wallpaperList.add(WallpaperModel("https://raw.githubusercontent.com/Ashutoshwahane/junk-data/main/resource4.jpeg","7"))
        wallpaperList.add(WallpaperModel("https://raw.githubusercontent.com/Ashutoshwahane/junk-data/main/resource1.jpeg","8"))
        wallpaperList.add(WallpaperModel("https://raw.githubusercontent.com/Ashutoshwahane/junk-data/main/resource5.jpeg","9"))
        wallpaperList.add(WallpaperModel("https://raw.githubusercontent.com/Ashutoshwahane/junk-data/main/resource3.jpeg","10"))





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

    override fun wallpaperClickListener(wallpaperModel: WallpaperModel, position: Int) {
        Intent(requireContext(), WallpaperActivity::class.java).apply {
            putExtra("imageUrl", wallpaperModel.imageLink)
            putExtra("imageTitle", wallpaperModel.title)
            putExtra("positionWallpaper", position)
            startActivity(this)
        }
    }

    override fun informationCategoryClickListener(position: Int) {
        TODO("Not yet implemented")
    }
}