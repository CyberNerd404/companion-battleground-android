package com.cybernerd.bgmiguide.view.home.wallpapers

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.cybernerd.bgmiguide.R
import com.cybernerd.bgmiguide.adapter.ThemeAdapter
import com.cybernerd.bgmiguide.model.Wallpaper
import com.cybernerd.bgmiguide.model.WallpaperModel
import com.cybernerd.bgmiguide.model.WallpapersModel
import com.cybernerd.bgmiguide.utils.WallpaperListener
import com.cybernerd.bgmiguide.utils.debug
import com.cybernerd.bgmiguide.view.BaseFragment
import kotlinx.android.synthetic.main.fragment_home_wallpaper.*
import java.io.Serializable

class HomeWallpaperFragment : BaseFragment(), WallpaperListener {


    lateinit var themeAdapter: ThemeAdapter
    var wallpaperList = arrayListOf<WallpaperModel>()

    private val viewModel: HomeWallpaperViewModel by lazy {
        HomeWallpaperViewModel(requireContext())
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


    override fun wallpaperListener(wallpaper: WallpapersModel, position: Int) {
        Intent(requireContext(), WallpaperActivity::class.java).apply {
            putExtra("wallpaperObj", wallpaper as Serializable)
            putExtra("positionWallpaper", position)
            startActivity(this)
        }
    }
}