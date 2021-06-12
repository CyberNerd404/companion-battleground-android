package com.cybernerd.bgmiguide.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.cybernerd.bgmiguide.R
import com.cybernerd.bgmiguide.model.Token
import com.cybernerd.bgmiguide.network.CompanionApi
import com.cybernerd.bgmiguide.utils.SessionManager
import com.cybernerd.bgmiguide.utils.showToast
import com.cybernerd.bgmiguide.view.home.HomeFragment
import com.cybernerd.bgmiguide.view.information.InformationFragment
import com.cybernerd.bgmiguide.view.notification.NotificationFragment
import com.cybernerd.bgmiguide.view.setting.SettingFragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : BaseActivity() {

    var tokenKey = ""
    lateinit var sessionManager: SessionManager
    lateinit var activeFragment: Fragment
    private val homeFragment = HomeFragment()
    private val notificationFragment = NotificationFragment()
    private val settingFragment = SettingFragment()
    private val informationFragment = InformationFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sessionManager = SessionManager(this)

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                return@OnCompleteListener
            }
            // Get new FCM registration token
            tokenKey = task.result.toString()
        })

        sessionManager.fetchAuthToken()
        val keyVal = sessionManager.fetchAuthToken()
        if (keyVal == null) {
            Handler(Looper.myLooper()!!).postDelayed(Runnable {
                sendDeviceToken(tokenKey)
            }, 2000)
        }

        bottom_navigation.setOnNavigationItemSelectedListener(object :
            BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.home_menu -> {
                        loadFragment(homeFragment)
                        return true
                    }
                    R.id.information_menu -> {
                        loadFragment(informationFragment)
                        return true
                    }
                    R.id.notification_menu -> {
                        loadFragment(notificationFragment)
                        return true
                    }
                    R.id.setting_menu -> {
                        loadFragment(settingFragment)
                        return true
                    }

                    else -> {
                        loadFragment(homeFragment)
                        return false
                    }
                }
            }

        })
        activeFragment = homeFragment


    }

    private fun sendDeviceToken(key: String) {
        val jsonObject = JsonObject()
        jsonObject.addProperty("token", key)
        CompanionApi().sendToken(jsonObject).enqueue(object : retrofit2.Callback<Token> {
            override fun onResponse(call: Call<Token>, response: Response<Token>) {
                if (response.isSuccessful) {
                    sessionManager.saveAuthToken("BGMIGuide $key")
                }
            }

            override fun onFailure(call: Call<Token>, t: Throwable) {
                t.printStackTrace()
            }

        })


    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        supportFragmentManager.executePendingTransactions()
        if (!fragment.isAdded) {
            transaction.hide(activeFragment)
            transaction.add(R.id.fragmentContainer, fragment)
            transaction.show(fragment)
            activeFragment = fragment
        } else {
            transaction.hide(activeFragment)
            transaction.show(fragment)
            activeFragment = fragment
        }
        transaction.commitAllowingStateLoss()
        supportFragmentManager.executePendingTransactions()

    }


}