package com.cybernerd.companionBattleground.repository

import androidx.lifecycle.MutableLiveData
import com.cybernerd.companionBattleground.model.HomeNewsListModel
import com.cybernerd.companionBattleground.network.CompanionApi
import com.cybernerd.companionBattleground.utils.debug
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeNewsRepository {
    val newsLiveData = MutableLiveData<HomeNewsListModel>()
    val showProgress = MutableLiveData<Boolean>()

    fun getNews(){
        showProgress.value = true
        CompanionApi().getHomeNews().enqueue(object : Callback<HomeNewsListModel>{
            override fun onResponse(
                call: Call<HomeNewsListModel>,
                response: Response<HomeNewsListModel>
            ) {
                newsLiveData.value = response.body()
                debug("NewsRepositorySuccess : call = ${response.body()}")
                showProgress.value = false

            }

            override fun onFailure(call: Call<HomeNewsListModel>, t: Throwable) {
                try {
                    showProgress.value = false
                    error("NewsRepositoryFailure : error = ${t.message}")
                }catch (e:Exception){
                    showProgress.value = false
                }
            }

        })
    }
}