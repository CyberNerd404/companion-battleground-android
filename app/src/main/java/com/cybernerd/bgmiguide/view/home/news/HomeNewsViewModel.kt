package com.cybernerd.bgmiguide.view.home.news

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cybernerd.bgmiguide.model.HomeNewsListModel
import com.cybernerd.bgmiguide.repository.HomeNewsRepository
import com.cybernerd.bgmiguide.utils.SessionManager
import com.cybernerd.bgmiguide.utils.debug

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