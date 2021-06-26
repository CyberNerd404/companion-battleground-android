package com.cybernerd404.bgmiguide.repository

import androidx.lifecycle.MutableLiveData
import com.cybernerd404.bgmiguide.model.GunsModelX
import com.cybernerd404.bgmiguide.network.junkApi
import com.cybernerd404.bgmiguide.utils.debug
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GunsRepository {
    val gunsLiveData = MutableLiveData<GunsModelX>()
    val showProgress = MutableLiveData<Boolean>()

    fun getGuns() {
        showProgress.value = true
        junkApi().getGuns().enqueue(object : Callback<GunsModelX> {
            override fun onResponse(
                call: Call<GunsModelX>,
                response: Response<GunsModelX>,
            ) {
                if (response.isSuccessful) {
                    gunsLiveData.value = response.body()
                    debug("guns : ${response.body()}")
                    showProgress.value = false
                }


            }

            override fun onFailure(call: Call<GunsModelX>, t: Throwable) {
                try {
                    showProgress.value = false
                } catch (e: Exception) {
                    showProgress.value = false
                }
            }

        })
    }
}