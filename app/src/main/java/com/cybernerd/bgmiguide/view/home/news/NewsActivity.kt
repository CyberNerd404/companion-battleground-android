package com.cybernerd.bgmiguide.view.home.news


import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.cybernerd.bgmiguide.R
import com.cybernerd.bgmiguide.model.HomeNewsModel
import com.cybernerd.bgmiguide.utils.debug
import kotlinx.android.synthetic.main.activity_news.*


class NewsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        val homeNewsModel = intent.extras?.getParcelable<HomeNewsModel>("homeNewsModel")
        toolbar.setNavigationIcon(R.drawable.ic_back_icon)
        toolbar.setNavigationOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                finish()
            }
        })

        Glide.with(this)
            .load(homeNewsModel?.media)
            .placeholder(R.drawable.battleground_logo)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(news_image)
        collapsing_toolbar.setExpandedTitleColor(Color.WHITE);

        text_rights.text = homeNewsModel?.rights ?:""
        news_title.text = homeNewsModel?.title ?: ""
        published_date.text = homeNewsModel?.publishedDate ?: ""
        news_description.text = homeNewsModel?.description ?: ""



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