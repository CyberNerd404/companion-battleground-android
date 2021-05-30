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
                coming_soon_text.text = "Map are currently unavailable, We'll update it once Battleground Mobile India is released"
            }
            1 -> {
                coming_soon_text.text = "Guns are currently unavailable, We'll update it once Battleground Mobile India is released"
            }
            2 -> {
                coming_soon_text.text = "Tips & Tricks are currently unavailable, We'll update it once Battleground Mobile India is released"
            }
            3 -> {
                coming_soon_text.text = "Top Ranking are currently unavailable, We'll update it once Battleground Mobile India is released"
            }
            4 -> {
                coming_soon_text.text = "Damage Calculator are currently unavailable, We'll update it once Battleground Mobile India is released"
            }
            5 -> {
                coming_soon_text.text = "Sound are currently unavailable, We'll update it once Battleground Mobile India is released"
            }
            else -> {
                coming_soon_text.text = "Map are currently unavailable, We'll update it once Battleground Mobile India is released"
            }
        }
    }
}