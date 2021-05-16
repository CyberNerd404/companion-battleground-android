package com.cybernerd.companionBattleground.view.home.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cybernerd.companionBattleground.R
import kotlinx.android.synthetic.main.activity_news.*

class NewsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        val intent = intent.extras

        val imv = intent?.getInt("img")

        if (imv != null) {
            news_iv.setImageResource(imv)
        }


        news_back_iv.setOnClickListener {
            finish()
        }
    }
}