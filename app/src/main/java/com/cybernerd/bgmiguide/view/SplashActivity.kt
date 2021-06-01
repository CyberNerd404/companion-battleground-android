package com.cybernerd.bgmiguide.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.cybernerd.bgmiguide.R
import com.cybernerd.bgmiguide.model.Token
import com.cybernerd.bgmiguide.network.CompanionApi
import com.cybernerd.bgmiguide.utils.debug
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
        })

        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return

        val key = resources.getString(R.string.saved_key)
        val keyVal = sharedPref.getString(key, "null")
        if (keyVal.equals("null", true)) {
            with(sharedPref.edit()) {
                putString(getString(R.string.saved_key), "cybernerd")
                apply()
            }
            Handler().postDelayed({
                sendDeviceToken(tokenKey)
            }, 2000)

        } else {

        }

        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            this@SplashActivity.finish()
        }, 3000)
    }


    private fun sendDeviceToken(key: String) {

        val jsonObject = JsonObject()
        jsonObject.addProperty("token", key)
        CompanionApi().sendToken(jsonObject).enqueue(object : retrofit2.Callback<Token> {
            override fun onResponse(call: Call<Token>, response: Response<Token>) {
                if (response.isSuccessful) {
                }
            }

            override fun onFailure(call: Call<Token>, t: Throwable) {
                t.printStackTrace()
            }

        })


    }

}