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
import kotlinx.android.synthetic.main.activity_guns.*

class GunsActivity : AppCompatActivity() {

    lateinit var list: MutableList<GunsModelX>
    lateinit var gunsAdapter: GunsAdapter
    val viewModel: GunsViewModel by lazy {
        GunsViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guns)

        gunsAdapter = GunsAdapter(this)

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
                val intent = Intent(this, FilterGunActivity::class.java)
                    startActivity(intent)
        }

    }

}