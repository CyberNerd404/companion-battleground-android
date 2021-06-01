package com.cybernerd.bgmiguide.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.cybernerd.bgmiguide.view.home.news.HomeNewsFragment
import com.cybernerd.bgmiguide.view.home.rumors.HomeRumorsFragment
import com.cybernerd.bgmiguide.view.home.tipsandtricks.HomeTipsTrickFragment
import com.cybernerd.bgmiguide.view.home.topvideos.HomeTopVideosFragment
import com.cybernerd.bgmiguide.view.home.wallpapers.HomeWallpaperFragment


class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {

        when(position) {
            0 -> {
                return HomeNewsFragment()
            }
            1 -> {
                return HomeTopVideosFragment()
            }
            2 -> {
                return HomeWallpaperFragment()
            }
            3 -> {
                return HomeRumorsFragment()
            }
            4 -> {
                return HomeTipsTrickFragment()
            }

            else -> return HomeTopVideosFragment()
        }

    }
}