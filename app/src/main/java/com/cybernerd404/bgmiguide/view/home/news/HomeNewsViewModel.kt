package com.cybernerd404.bgmiguide.view.home.news

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cybernerd404.bgmiguide.model.HomeNewsListModel
import com.cybernerd404.bgmiguide.repository.HomeNewsRepository

class HomeNewsViewModel(context: Context): ViewModel() {

    var newsRepository = HomeNewsRepository()
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