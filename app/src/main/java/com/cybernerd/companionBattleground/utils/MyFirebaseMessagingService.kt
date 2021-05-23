package com.cybernerd.companionBattleground.utils

import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Intent
import com.cybernerd.companionBattleground.R
import com.cybernerd.companionBattleground.view.MainActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                debug("Fetching FCM registration token failed ${task.exception}")
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val firebaseToken = task.result

            // Log and toast
            debug("token : $firebaseToken")

        })
    }

    override fun onMessageReceived(response: RemoteMessage)         {
        super.onMessageReceived(response)

        debug("show Response : ${response.data}")

        val resultIntent = Intent(this, MainActivity::class.java)
        // Create the TaskStackBuilder
        val resultPendingIntent: PendingIntent? = TaskStackBuilder.create(this).run {
            // Add the intent, which inflates the back stack
            addNextIntentWithParentStack(resultIntent)
            // Get the PendingIntent containing the entire back stack
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }


    }


}