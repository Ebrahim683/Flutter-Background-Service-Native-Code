package com.example.background_service_flutter.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.background_service_flutter.R

class MyService : Service() {
	override fun onBind(intent: Intent?): IBinder? {
		return null
	}
	
	@RequiresApi(Build.VERSION_CODES.M)
	override fun onCreate() {
		super.onCreate()
		
		val notificationManager = getSystemService(NotificationManager::class.java)
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			val notification = NotificationCompat.Builder(
				this,
				CHANNEL_ID
			).apply {
				setContentText("running in background")
				setContentTitle("Flutter service")
				setSmallIcon(R.drawable.ic_android_black_24dp)
			}
			val notificationChannel = NotificationChannel(
				CHANNEL_ID,
				CHANNEL_NAME,
				NotificationManager.IMPORTANCE_DEFAULT
			)
			notificationManager.createNotificationChannel(notificationChannel)
			startForeground(SERVICE_ID, notification.build())
		}
	}
	
	override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
		return super.onStartCommand(intent, flags, startId)
	}
	
	companion object {
		private const val CHANNEL_ID = "channel_id"
		private const val CHANNEL_NAME = "background_service"
		private const val SERVICE_ID = 101
	}
	
}
