package com.cybernerd404.bgmiguide.view.information.guns

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cybernerd404.bgmiguide.R
import com.cybernerd404.bgmiguide.adapter.GunsViewPagerAdapter
import com.cybernerd404.bgmiguide.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_guns.*
import kotlinx.android.synthetic.main.fragment_home.*

class GunsFragment : Fragment() {

    lateinit var viewPagerAdapter: GunsViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_guns, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPagerAdapter = GunsViewPagerAdapter(this)

        gunsViewPager.adapter = viewPagerAdapter

        gunsViewPager.isUserInputEnabled = true

        gunsTabLayout.isInlineLabel = true

        TabLayoutMediator(gunsTabLayout, gunsViewPager) { tab, position ->

            tab.text = when (position) {
                0 -> {
                    "Assualt"
                }
                1 -> {
                    "DMR"
                }
                2 -> {
                    "LMG"
                }
                3 -> {
                    "Pistol"
                }
                4 -> {
                    "Shotgun"
                }
                5 -> {
                    "SMG"
                }
                6 -> {
                    "Snipper"
                }
                else -> "Assualt"
            }

        }.attach()
    }

}