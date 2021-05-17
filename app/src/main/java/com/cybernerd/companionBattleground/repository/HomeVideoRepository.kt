package com.cybernerd.companionBattleground.repository

import androidx.lifecycle.MutableLiveData
import com.cybernerd.companionBattleground.model.HomeVideosModel
import com.cybernerd.companionBattleground.network.CompanionApi
import com.cybernerd.companionBattleground.utils.debug
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeVideoRepository {
    val videoLiveData = MutableLiveData<HomeVideosModel>()

    fun getVideo(){
            CompanionApi().getHomeVideos().enqueue(object : Callback<HomeVideosModel>{
                override fun onResponse(
                    call: Call<HomeVideosModel>,
                    response: Response<HomeVideosModel>
                ) {
//                videoLiveData.value = response.body()
                    debug("RepositorySuccess : call = ${response.body()}")

                }

                override fun onFailure(call: Call<HomeVideosModel>, t: Throwable) {
                    try {
                        error("RepositoryFailure : error = ${t.message}")
                    }catch (e:Exception){}

                }

            })

    }
}