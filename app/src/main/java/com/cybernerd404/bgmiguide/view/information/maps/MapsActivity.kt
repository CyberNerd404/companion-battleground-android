package com.cybernerd404.bgmiguide.view.information.maps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.widget.ImageView
import com.cybernerd404.bgmiguide.R
import kotlinx.android.synthetic.main.activity_maps.*
import kotlinx.android.synthetic.main.activity_web_view.*
import java.lang.Float.max
import java.lang.Float.min

class MapsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        map_webView.settings.javaScriptEnabled = true

        map_webView.loadUrl("https://pubgmap.io/erangel.html?/v2/30/4ftla8/BLeG/W4v70n4")

    }


}