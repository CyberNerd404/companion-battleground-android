package com.cybernerd404.bgmiguide.view.setting

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.cybernerd404.bgmiguide.BuildConfig
import com.cybernerd404.bgmiguide.R
import com.cybernerd404.bgmiguide.adapter.SettingsAdapter
import com.cybernerd404.bgmiguide.utils.SettingListener
import com.cybernerd404.bgmiguide.view.BaseFragment
import kotlinx.android.synthetic.main.fragment_setting.*


class SettingFragment : BaseFragment(), SettingListener {

    val versionName = BuildConfig.VERSION_NAME
    val versionCode = BuildConfig.VERSION_CODE
    lateinit var homeAdapter: SettingsAdapter
    var settingStringList = arrayListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeAdapter = SettingsAdapter(requireContext(), this)
        list_rv.adapter = homeAdapter


        list_rv.layoutManager = GridLayoutManager(requireContext(), 1)
        settingStringList.add(0, "Privacy Policy")
        settingStringList.add(1, "Contact Us")
        settingStringList.add(2, "Update App")
        settingStringList.add(3, "Version: $versionName")
        homeAdapter.setSettingsText(settingStringList)


    }


    override fun settingsListener(position: Int) {
        when (position) {
            0 -> {
                activity.let {
                    Intent(it, WebViewActivity::class.java).apply {
                        putExtra("url", "https://bgmiguide.azurewebsites.net/privacy-policy.html")
                        startActivity(this)
                    }
                }
            }
            1 -> {
                activity.let {
                    Intent(it, WebViewActivity::class.java).apply {
                        putExtra("url", "https://bgmiguide.azurewebsites.net/")
                        startActivity(this)
                    }
                }
            }
            else -> activity.let {
            }
        }
    }
}
