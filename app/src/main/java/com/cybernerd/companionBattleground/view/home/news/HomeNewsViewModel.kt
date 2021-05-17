package com.cybernerd.companionBattleground.view.home.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cybernerd.companionBattleground.model.HomeNewsListModel
import com.cybernerd.companionBattleground.repository.HomeNewsRepository

class HomeNewsViewModel: ViewModel() {

    val newsRepository = HomeNewsRepository()
    var newsLiveData = MutableLiveData<HomeNewsListModel>()

    init {
        this.newsLiveData = newsRepository.newsLiveData
    }

    fun getNews(){
        newsRepository.getNews()
    }

}