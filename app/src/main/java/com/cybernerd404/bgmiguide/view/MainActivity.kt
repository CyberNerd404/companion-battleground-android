package com.cybernerd404.bgmiguide.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.cybernerd404.bgmiguide.R
import com.cybernerd404.bgmiguide.model.Token
import com.cybernerd404.bgmiguide.network.CompanionApi
import com.cybernerd404.bgmiguide.utils.SessionManager
import com.cybernerd404.bgmiguide.utils.debug
import com.cybernerd404.bgmiguide.view.home.HomeFragment
import com.cybernerd404.bgmiguide.view.information.InformationFragment
import com.cybernerd404.bgmiguide.view.quiz.QuizFragment
import com.cybernerd404.bgmiguide.view.setting.SettingFragment
import com.facebook.ads.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response


class MainActivity : BaseActivity() {
    private var interstitialAd: InterstitialAd? = null
    private val TAG: String = MainActivity::class.java.getSimpleName()

    var tokenKey = ""
    lateinit var sessionManager: SessionManager
    lateinit var activeFragment: Fragment
    private val homeFragment = HomeFragment()
    private val notificationFragment = QuizFragment()
    private val settingFragment = SettingFragment()
    private val informationFragment = InformationFragment()

    lateinit var adView: AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        AudienceNetworkAds.initialize(this)

        sessionManager = SessionManager(this)

        adView = AdView(this, "874198839852511_874199979852397", AdSize.BANNER_HEIGHT_50)
        banner_container.addView(adView)
        adView.loadAd()

        activeFragment = homeFragment

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

        // Instantiate an InterstitialAd object.
        // NOTE: the placement ID will eventually identify this as your App, you can ignore it for
        // now, while you are testing and replace it later when you have signed up.
        // While you are using this temporary code you will only get test ads and if you release
        // your code like this to the Google Play your users will not receive ads (you will get a no fill error).

        interstitialAd = InterstitialAd(this, "874198839852511_874207956518266")

        val  adListener = object : InterstitialAdListener{
            override fun onError(p0: Ad?, p1: AdError?) {
                debug("error :${p0.toString()} ${p1?.errorMessage}")
            }

            override fun onAdLoaded(p0: Ad?) {
                debug("onAdLoaded :${p0} ")
                interstitialAd!!.show()

            }

            override fun onAdClicked(p0: Ad?) {
                debug("onAdClicked :${p0} ")
            }

            override fun onLoggingImpression(p0: Ad?) {
                debug("onLoggingImpression :${p0} ")
            }

            override fun onInterstitialDisplayed(p0: Ad?) {
                debug("onInterstitialDisplayed :${p0} ")
            }

            override fun onInterstitialDismissed(p0: Ad?) {
                debug("onInterstitialDisplayed :${p0} ")
            }

        }

        val loadAdConfig = interstitialAd!!.buildLoadAdConfig()
            .withAdListener(adListener)
            .build()

        interstitialAd!!.loadAd(loadAdConfig)

        //and when you want to show ad
        if (interstitialAd!!.isAdLoaded){
            interstitialAd!!.show()
        }


    }


    override fun onDestroy() {
        super.onDestroy()
        adView.destroy()
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

        interstitialAd!!.loadAd()

    }

}