package com.cybernerd404.bgmiguide.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.cybernerd404.bgmiguide.view.information.guns.category.*


class GunsViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 7

    override fun createFragment(position: Int): Fragment {

        when(position) {
            0 -> {
                return AssaultFragment()
            }
            1 -> {
                return DMRFragment()
            }
            2 -> {
                return LMGFragment()
            }
            3 -> {
                return PistolFragment()
            }
            4 -> {
                return ShotgunFragment()
            }

            5 -> {
                return SMGFragment()
            }
            6 -> {
                return SniperFragment()
            }

            else -> return AssaultFragment()
        }

    }
}