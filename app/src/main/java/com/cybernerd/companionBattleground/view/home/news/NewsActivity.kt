package com.cybernerd.companionBattleground.view.home.news


import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cybernerd.companionBattleground.R
import com.google.android.material.appbar.CollapsingToolbarLayout
import kotlinx.android.synthetic.main.activity_news.*


class NewsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        val intent = intent.extras

        val imv = intent?.getInt("img")
//        val toolbar_layout = findViewById<CollapsingToolbarLayout>(R.id.collapsing_toolbar)
        collapsing_toolbar.setExpandedTitleColor(Color.WHITE);

//        if (imv != null) {
//            news_iv.setImageResource(imv)
//        }
//
//
//        news_back_iv.setOnClickListener {
//            finish()
//        }
    }
}