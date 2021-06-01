package com.cybernerd.bgmiguide.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
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
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : BaseActivity() {

    private lateinit var navController: NavController
    var tokenKey = ""
    lateinit var sessionManager: SessionManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sessionManager = SessionManager(this)
        navController = findNavController(R.id.fragmentContainer)

        val intent = intent.extras
        val userExist = intent?.getString("userExist")

        when (userExist) {
            "exist" -> {
                initBottomNavigation()
            }
            "success" -> {
                initBottomNavigation()
            }
            "failed" -> {
                sendToken()
            }
        }


    }

    private fun sendToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                return@OnCompleteListener
            }
            // Get new FCM registration token
            tokenKey = task.result.toString()
        })
        val jsonObject = JsonObject()
        jsonObject.addProperty(resources.getString(R.string.token), tokenKey)
        CompanionApi().sendToken(jsonObject).enqueue(object : retrofit2.Callback<Token> {
            override fun onResponse(call: Call<Token>, response: Response<Token>) {
                if (response.isSuccessful) {
                    sessionManager.saveAuthToken(tokenKey)
                    initBottomNavigation()
                }
            }

            override fun onFailure(call: Call<Token>, t: Throwable) {
                t.printStackTrace()
                showToast(this@MainActivity,
                    "Please Contact Us, If you are not able to get any data")
                loadFragment(SettingFragment(), R.id.setting_menu)
            }

        })

    }


    private fun initBottomNavigation() {
        val extras = intent.extras
        when (extras?.getString("toOpen")) {
            "Home" -> {
                loadFragment(HomeFragment(), R.id.home_menu)
            }
            "Information" -> {
                loadFragment(InformationFragment(), R.id.information_menu)
            }
            "Notification" -> {
                loadFragment(NotificationFragment(), R.id.notification_menu)
            }
            "Setting" -> {
                loadFragment(SettingFragment(), R.id.setting_menu)
            }
        }
        bottom_navigation.setupWithNavController(navController)
    }

    private fun loadFragment(fragment: Fragment, fragmentId: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host_fragment_container, fragment)
        transaction.detach(fragment)
        transaction.attach(fragment)
        transaction.addToBackStack(null)
        transaction.commitAllowingStateLoss()
        supportFragmentManager.executePendingTransactions()

    }


}