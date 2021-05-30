package com.cybernerd.companionBattleground

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cybernerd.companionBattleground.model.HomeNewsModel
import kotlinx.android.synthetic.main.activity_coming_soon.*
import kotlinx.android.synthetic.main.item_information_layout.view.*

class ComingSoonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coming_soon)

        val selectionValue = intent.getIntExtra("position",0)

        when (selectionValue) {

            0 -> {
                placeholder_screen.setImageResource(R.drawable.bg1)
                screenTitle.text = "Map"
            }
            1 -> {
                placeholder_screen.setImageResource(R.drawable.bg2)
                screenTitle.text = "Guns"
            }
            2 -> {
                placeholder_screen.setImageResource(R.drawable.bg3)
                screenTitle.text = "Tips & Tricks"
            }
            3 -> {
                placeholder_screen.setImageResource(R.drawable.bg4)
                screenTitle.text = "Top Ranking"
            }
            4 -> {
                placeholder_screen.setImageResource(R.drawable.bg5)
                screenTitle.text = "Damage Calculator"
            }
            5 -> {
                placeholder_screen.setImageResource(R.drawable.bg1)
                screenTitle.text = "Sound"
            }
            else -> {
                placeholder_screen.setImageResource(R.drawable.bg2)
                screenTitle.text = "Map"
            }
        }
    }
}