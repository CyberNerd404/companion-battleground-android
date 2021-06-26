package com.cybernerd404.bgmiguide.view.information.guns

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cybernerd404.bgmiguide.model.GunsModelX
import com.cybernerd404.bgmiguide.repository.GunsRepository
import com.google.gson.JsonObject

class GunsViewModel(): ViewModel() {

    var gunsRepository = GunsRepository()
    var gunsLiveData = MutableLiveData<GunsModelX>()
    val showprogress : LiveData<Boolean>

    init {
        this.showprogress = gunsRepository.showProgress
        this.gunsLiveData = gunsRepository.gunsLiveData
    }

    fun getGuns(){
        gunsRepository.getGuns()
    }

}