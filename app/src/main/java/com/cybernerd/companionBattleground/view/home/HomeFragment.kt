package com.cybernerd.companionBattleground.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.cybernerd.companionBattleground.R
import com.cybernerd.companionBattleground.adapter.HomeCardViewAdapter
import com.cybernerd.companionBattleground.adapter.ViewPagerAdapter
import com.cybernerd.companionBattleground.model.HomeCardViewModel
import com.cybernerd.companionBattleground.utils.ClickListener
import com.cybernerd.companionBattleground.view.BaseFragment
import com.google.android.material.tabs.TabLayout
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

        viewPager.isUserInputEnabled = true

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->

            tab.text = when (position){
                0 -> {
                    "Videos"
                }
                1 -> {
                    "News"
                }
                2 -> {
                    "Rumors"
                }
                3 -> {
                    "Tips"
                }
                else -> "Top Videos"
            }

        }.attach()


    }


}