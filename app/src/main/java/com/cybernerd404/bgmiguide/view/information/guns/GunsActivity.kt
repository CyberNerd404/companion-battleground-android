package com.cybernerd404.bgmiguide.view.information.guns

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.cybernerd404.bgmiguide.R
import com.cybernerd404.bgmiguide.adapter.GunsAdapter
import com.cybernerd404.bgmiguide.model.GunsModel
import kotlinx.android.synthetic.main.activity_guns.*

class GunsActivity : AppCompatActivity() {

    lateinit var gunsAdapter: GunsAdapter
    lateinit var list: MutableList<GunsModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guns)

        list = arrayListOf()
        gunsAdapter = GunsAdapter(this)

        guns_rv.adapter = gunsAdapter
        guns_rv.layoutManager = LinearLayoutManager(this)

        var gunsModel = GunsModel()
        gunsModel.name = "Kar98"
        list.add(gunsModel)

        gunsModel = GunsModel()
        gunsModel.name = "M416"
        list.add(gunsModel)

        gunsModel = GunsModel()
        gunsModel.name = "Mini 14"
        list.add(gunsModel)

        gunsAdapter.setGuns(list)

    }
}