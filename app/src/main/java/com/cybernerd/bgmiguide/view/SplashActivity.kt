package com.cybernerd.bgmiguide.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.cybernerd.bgmiguide.R
import com.cybernerd.bgmiguide.model.Token
import com.cybernerd.bgmiguide.network.CompanionApi
import com.cybernerd.bgmiguide.utils.SessionManager
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response

class SplashActivity : AppCompatActivity() {

    var tokenKey = ""
    lateinit var sessionManager: SessionManager

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

        /* If User Exist -> exist
       *  If New User Added -> success
       *  If User token failed -> failed
       *  for every api request we need to send the device token and because of that we should check whether the token is exist in our
       * sharedPreferences or not, if not we have to send the token to our server
       *  */

        sessionManager = SessionManager(this)
        sessionManager.fetchAuthToken()
        val keyVal = sessionManager.fetchAuthToken()
        if (keyVal != null) {
            navigateToHomeScreen("exist")
        } else {
            Handler().postDelayed({
                sendDeviceToken(tokenKey)
            }, 2000)
        }
    }


    private fun sendDeviceToken(key: String) {
        val jsonObject = JsonObject()
        jsonObject.addProperty("token", key)
        CompanionApi().sendToken(jsonObject).enqueue(object : retrofit2.Callback<Token> {
            override fun onResponse(call: Call<Token>, response: Response<Token>) {
                if (response.isSuccessful) {
                    sessionManager.saveAuthToken("BGMIGuide $key")
                    navigateToHomeScreen("success")
                }
            }

            override fun onFailure(call: Call<Token>, t: Throwable) {
                t.printStackTrace()
                navigateToHomeScreen("failed")
            }

        })


    }

    private fun navigateToHomeScreen(newUser: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.putExtra("userExist", newUser)
        startActivity(intent)
        this@SplashActivity.finish()
    }

}