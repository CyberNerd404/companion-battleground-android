package com.cybernerd.companionBattleground.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cybernerd.companionBattleground.R
import com.cybernerd.companionBattleground.adapter.ViewPagerAdapter
import com.cybernerd.companionBattleground.model.HomeNewsModel
import com.cybernerd.companionBattleground.model.Notification
import com.cybernerd.companionBattleground.model.Videos
import com.cybernerd.companionBattleground.utils.ClickListener
import com.cybernerd.companionBattleground.view.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : BaseFragment(), ClickListener {
    /*
        lateinit var viewPager: ViewPager2
        lateinit var tabLayout: TabLayout*/
    lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPagerAdapter = ViewPagerAdapter(this)

        viewPager.adapter = viewPagerAdapter

        viewPager.isUserInputEnabled = false

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->

            tab.text = when (position) {
                0 -> {
                    "News"
                }
                1 -> {
                    "Videos"
                }
                2 -> {
                    "Rumors"
                }
                3 -> {
                    "Tips"
                }
                else -> "News"
            }

        }.attach()


    }

    override fun homeNewsClickListener(homeNewsModel: HomeNewsModel) {

    }

    override fun homeVideoClickListener(videos: Videos) {
        TODO("Not yet implemented")
    }

    override fun notificationClickListener(notification: Notification) {
        TODO("Not yet implemented")
    }

    override fun settingsClickListener(position: Int) {
        TODO("Not yet implemented")
    }


}