package com.cybernerd.companionBattleground.repository

import androidx.lifecycle.MutableLiveData
import com.cybernerd.companionBattleground.model.HomeNews1Model
import com.cybernerd.companionBattleground.model.HomeVideosModel
import com.cybernerd.companionBattleground.network.CompanionApi
import com.cybernerd.companionBattleground.utils.debug
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeNewsRepository {
    val newsLiveData = MutableLiveData<HomeNews1Model>()

    fun getNews(){
        CompanionApi().getHomeNews().enqueue(object : Callback<HomeNews1Model>{
            override fun onResponse(
                call: Call<HomeNews1Model>,
                response: Response<HomeNews1Model>
            ) {
                newsLiveData.value = response.body()
                debug("NewsRepositorySuccess : call = ${response.body()}")

            }

            override fun onFailure(call: Call<HomeNews1Model>, t: Throwable) {
                error("NewsRepositoryFailure : error = ${t.message}")
            }

        })
    }
}