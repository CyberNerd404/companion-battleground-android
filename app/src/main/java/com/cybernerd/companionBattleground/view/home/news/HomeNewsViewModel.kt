package com.cybernerd.companionBattleground.view.home.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cybernerd.companionBattleground.model.HomeNewsListModel
import com.cybernerd.companionBattleground.repository.HomeNewsRepository

class HomeNewsViewModel: ViewModel() {

    val newsRepository = HomeNewsRepository()
    var newsLiveData = MutableLiveData<HomeNewsListModel>()
    val showprogress : LiveData<Boolean>

    init {
        this.showprogress = newsRepository.showProgress
        this.newsLiveData = newsRepository.newsLiveData
    }

    fun getNews(){
        newsRepository.getNews()
    }

}