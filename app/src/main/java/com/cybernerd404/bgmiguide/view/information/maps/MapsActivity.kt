package com.cybernerd404.bgmiguide.view.information.maps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.widget.ImageView
import com.cybernerd404.bgmiguide.R
import kotlinx.android.synthetic.main.activity_maps.*
import java.lang.Float.max
import java.lang.Float.min

class MapsActivity : AppCompatActivity() {
    private lateinit var scaleGestureDetector: ScaleGestureDetector
    private var scaleFactor = 1.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        scaleGestureDetector = ScaleGestureDetector(this, ScaleListener())

    }

    override fun onTouchEvent(motionEvent: MotionEvent): Boolean {
        scaleGestureDetector.onTouchEvent(motionEvent)
        return true
    }
    private inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(scaleGestureDetector: ScaleGestureDetector): Boolean {
            scaleFactor *= scaleGestureDetector.scaleFactor
            scaleFactor = max(0.1f, min(scaleFactor, 10.0f))
            map_iv.scaleX = scaleFactor
            map_iv.scaleY = scaleFactor
            return true
        }
    }
}