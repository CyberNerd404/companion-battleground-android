package com.cybernerd.companionBattleground.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.cybernerd.companionBattleground.view.home.news.HomeNewsFragment
import com.cybernerd.companionBattleground.view.home.rumors.HomeRumorsFragment
import com.cybernerd.companionBattleground.view.home.tipsandtricks.HomeTipsTrickFragment
import com.cybernerd.companionBattleground.view.home.topvideos.HomeTopVideosFragment


class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {

        when(position) {
            0 -> {
                return HomeNewsFragment()
            }
            1 -> {
                return HomeTopVideosFragment()
            }
            2 -> {
                return HomeRumorsFragment()
            }
            3 -> {
                return HomeTipsTrickFragment()
            }

            else -> return HomeTopVideosFragment()
        }

    }
}