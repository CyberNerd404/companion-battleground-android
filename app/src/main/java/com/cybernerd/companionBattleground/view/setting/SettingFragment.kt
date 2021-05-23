package com.cybernerd.companionBattleground.view.setting

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.cybernerd.companionBattleground.R
import com.cybernerd.companionBattleground.adapter.SettingsAdapter
import com.cybernerd.companionBattleground.model.HomeNewsModel
import com.cybernerd.companionBattleground.model.Notification
import com.cybernerd.companionBattleground.model.Videos
import com.cybernerd.companionBattleground.model.WallpaperModel
import com.cybernerd.companionBattleground.utils.ClickListener
import com.cybernerd.companionBattleground.view.BaseFragment
import com.cybernerd.companionBattleground.view.home.news.NewsActivity
import kotlinx.android.synthetic.main.fragment_setting.*


class SettingFragment : BaseFragment(), ClickListener {


    lateinit var homeAdapter: SettingsAdapter
    var settingStringList = arrayListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeAdapter = SettingsAdapter(requireContext(), this)
        list_rv.adapter = homeAdapter


        list_rv.layoutManager = GridLayoutManager(requireContext(), 1)
        settingStringList.add(0,"Terms and Condition")
        settingStringList.add(1,"Contact Us")
        settingStringList.add(2,"Update App")
        homeAdapter.setSettingsText(settingStringList)



    }

    override fun homeNewsClickListener(homeNewsModel: HomeNewsModel) {
        TODO("Not yet implemented")
    }

    override fun homeVideoClickListener(videoMode: Videos) {
        TODO("Not yet implemented")
    }

    override fun settingsClickListener(position: Int) {
        when(position) {
            0 -> {
                activity.let {
                    Intent(it, NewsActivity::class.java).apply {
                        startActivity(this)
                    }
                }
            }
            1 -> {
                activity.let {
                    Intent(it, NewsActivity::class.java).apply {
                        startActivity(this)
                    }
                }
            }


            else -> activity.let {
                Intent(it, NewsActivity::class.java).apply {
                    startActivity(this)
                }
            }
        }
    }

    override fun notificationClickListener(notification: Notification) {
        TODO("Not yet implemented")
    }

    override fun wallpaperClickListener(wallpaperModel: WallpaperModel) {
        TODO("Not yet implemented")
    }

}