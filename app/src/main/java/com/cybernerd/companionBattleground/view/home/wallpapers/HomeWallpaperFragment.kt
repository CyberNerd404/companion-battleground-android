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
import com.cybernerd.companionBattleground.model.Wallpaper
import com.cybernerd.companionBattleground.model.WallpaperModel
import com.cybernerd.companionBattleground.utils.WallpaperListener
import com.cybernerd.companionBattleground.utils.debug
import com.cybernerd.companionBattleground.view.BaseFragment
import kotlinx.android.synthetic.main.fragment_home_wallpaper.*

class HomeWallpaperFragment : BaseFragment(), WallpaperListener {


    lateinit var themeAdapter: ThemeAdapter
    var wallpaperList = arrayListOf<WallpaperModel>()

    private val viewModel: HomeWallpaperViewModel by lazy {
        HomeWallpaperViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_wallpaper, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        themeAdapter = ThemeAdapter(requireContext(), this)
        wallpaper_rv.adapter = themeAdapter
        wallpaper_rv.layoutManager = GridLayoutManager(requireContext(), 2)

        viewModel.getWallpapers()
        viewModel.wallpaperLiveData.observe(viewLifecycleOwner, Observer {
            debug("HomeFragment : Wallpaper data = ${it.wallpapers}")
            themeAdapter.setWallpaperGrid(it.wallpapers)
        })

        viewModel.showprogress.observe(viewLifecycleOwner, Observer {
            if (it) {
                wallpaper_progressbar.visibility = View.VISIBLE
            } else {
                wallpaper_progressbar.visibility = View.GONE
            }
        })


    }


    override fun wallpaperListener(wallpaper: Wallpaper, position: Int) {
        Intent(requireContext(), WallpaperActivity::class.java).apply {
            putExtra("imageUrl", wallpaper.image)
            putExtra("imageTitle", wallpaper.credit)
            putExtra("positionWallpaper", position)

        }
    }
}