package com.cybernerd404.bgmiguide.view.information.guns

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.cybernerd404.bgmiguide.ComingSoonActivity
import com.cybernerd404.bgmiguide.R
import com.cybernerd404.bgmiguide.adapter.GunsAdapter
import com.cybernerd404.bgmiguide.model.GunsModelX
import com.cybernerd404.bgmiguide.utils.debug
import com.cybernerd404.bgmiguide.utils.showToast
import com.facebook.ads.*
import kotlinx.android.synthetic.main.activity_guns.*

class GunsActivity : AppCompatActivity() {
    private var interstitialAd: InterstitialAd? = null
    lateinit var list: MutableList<GunsModelX>
    lateinit var gunsAdapter: GunsAdapter
    val viewModel: GunsViewModel by lazy {
        GunsViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guns)

        gunsAdapter = GunsAdapter(this)
        AudienceNetworkAds.initialize(this)

        guns_rv.adapter = gunsAdapter
        guns_rv.layoutManager = LinearLayoutManager(this)

        viewModel.getGuns()
        viewModel.gunsLiveData.observe(this, Observer {
            if (it != null)
                gunsAdapter.setGuns(it)
        })

        viewModel.showprogress.observe(this, Observer {
            if (it) {
                guns_progress.visibility = View.VISIBLE
            } else {
                guns_progress.visibility = View.GONE
            }
        })

        filterFloatingButton.setOnClickListener{
                /*val intent = Intent(this, FilterGunActivity::class.java)
                    startActivity(intent)*/
            showToast(this, "Filter feature coming soon")
        }

        interstitialAd = InterstitialAd(this, "874198839852511_874207956518266")

        val  adListener = object : InterstitialAdListener {
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

}