package com.cybernerd404.bgmiguide.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cybernerd404.bgmiguide.R
import com.cybernerd404.bgmiguide.adapter.ViewPagerAdapter
import com.cybernerd404.bgmiguide.view.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : BaseFragment() {
    /*
        lateinit var viewPager: ViewPager2
        lateinit var tabLayout: TabLayout*/
    lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPagerAdapter = ViewPagerAdapter(this)

        viewPager.adapter = viewPagerAdapter

        viewPager.isUserInputEnabled = true

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->

            tab.text = when (position) {
                0 -> {
                    "News"
                }
                1 -> {
                    "Videos"
                }
                2 -> {
                    "Wallpapaers"
                }
                3 -> {
                    "Rumors"
                }
                4 -> {
                    "Tips"
                }
                else -> "News"
            }

        }.attach()

    }


}