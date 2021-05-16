package com.cybernerd.companionBattleground.utils

import com.cybernerd.companionBattleground.model.HomeNewsModel
import com.cybernerd.companionBattleground.model.HomeVideoModel

interface ClickListener {


    // click listener for news recyclerview
    fun homeNewsClickListener(homeNewsModel: HomeNewsModel)

    // click listener for youtude recyclerview
    fun homeVideoClickListener(homeVideoModel: HomeVideoModel)
}