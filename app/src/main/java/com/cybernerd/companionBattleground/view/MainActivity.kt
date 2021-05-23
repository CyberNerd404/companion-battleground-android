package com.cybernerd.companionBattleground.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.cybernerd.companionBattleground.R
import com.cybernerd.companionBattleground.view.home.HomeFragment
import com.cybernerd.companionBattleground.view.information.InformationFragment
import com.cybernerd.companionBattleground.view.notification.NotificationFragment
import com.cybernerd.companionBattleground.view.setting.SettingFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.fragmentContainer)
        initBottomNavigation()
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