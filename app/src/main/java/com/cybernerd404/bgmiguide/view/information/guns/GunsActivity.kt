package com.cybernerd404.bgmiguide.view.information.guns

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cybernerd404.bgmiguide.R
import com.cybernerd404.bgmiguide.model.GunsModelX

class GunsActivity : AppCompatActivity() {

    lateinit var list: MutableList<GunsModelX>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guns)

        supportFragmentManager.beginTransaction().replace(R.id.fame_layout, GunsFragment()).commit()

    }

}