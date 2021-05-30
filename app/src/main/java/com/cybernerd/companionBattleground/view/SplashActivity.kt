package com.cybernerd.companionBattleground.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.cybernerd.companionBattleground.R
import com.cybernerd.companionBattleground.model.Token
import com.cybernerd.companionBattleground.network.CompanionApi
import com.cybernerd.companionBattleground.utils.debug
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response

class SplashActivity : AppCompatActivity() {

    var tokenKey = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)



        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                return@OnCompleteListener
            }

            // Get new FCM registration token
            tokenKey = task.result.toString()
            debug("send Token : $tokenKey")
        })

        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return

        val key = resources.getString(R.string.saved_key)
        val keyVal = sharedPref.getString(key, "null")
        debug("key : $keyVal")
        if (keyVal.equals("null", true)) {
            with(sharedPref.edit()) {
                putString(getString(R.string.saved_key), "cybernerd")
                debug("key added")
                apply()
            }
            Handler().postDelayed({
                sendDeviceToken(tokenKey)
            }, 2000)

        } else {
            debug("key already exist")

        }

        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            this@SplashActivity.finish()
        }, 3000)
    }


    private fun sendDeviceToken(key: String) {

        debug("request key $key")
        val jsonObject = JsonObject()
        jsonObject.addProperty("token", key)
        CompanionApi().sendToken(jsonObject).enqueue(object : retrofit2.Callback<Token> {
            override fun onResponse(call: Call<Token>, response: Response<Token>) {
                debug("request : ${call.request()}")
                if (response.isSuccessful) {
                    debug("Token send successfully : ${response.body()}")
                }
            }

            override fun onFailure(call: Call<Token>, t: Throwable) {
                t.printStackTrace()
            }

        })


    }

}