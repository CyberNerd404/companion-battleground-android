package com.cybernerd404.bgmiguide.utils

import android.app.*
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.cybernerd404.bgmiguide.R
import com.cybernerd404.bgmiguide.view.MainActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    val CHANNEL_ID = "ForegroundServiceChannel"

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val firebaseToken = task.result

            // Log and toast

        })
    }

    override fun onMessageReceived(response: RemoteMessage) {
        super.onMessageReceived(response)
        val dataType = response.data.get("type")
        val titleStr = response.data.get("title")
        val desc = response.data.get("description")
        if (titleStr != null && desc != null) {
            showNotification(0, titleStr, desc)
        }

    }

    private fun showNotification(id: Int, title: String, desc: String) {
        createNotificationChannel()
        val notificationIntent = Intent(this, MainActivity::class.java)
        notificationIntent.putExtra("notification", "true")
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, id,notificationIntent, 0)
        val notification: Notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(desc)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setOngoing(true)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()

        val manager: NotificationManager = getSystemService(NotificationManager::class.java)

        notification.flags = Notification.FLAG_AUTO_CANCEL
        manager.notify(1,notification)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Foreground Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager: NotificationManager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        }
    }


}