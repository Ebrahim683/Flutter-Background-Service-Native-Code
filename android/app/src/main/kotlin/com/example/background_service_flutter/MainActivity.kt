package com.example.background_service_flutter

import android.content.Intent
import android.os.Build
import android.os.Bundle
import com.example.background_service_flutter.service.MyService
import io.flutter.embedding.android.FlutterActivity
import io.flutter.plugin.common.MethodChannel

class MainActivity : FlutterActivity() {
	
	private val METHOD_CHANNEL = "background_service_flutter"
	private val START_SERVICE = "start_serviceAndroid"
	private val STOP_SERVICE = "stop_serviceAndroid"
	private lateinit var serviceIntent: Intent
	
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		serviceIntent = Intent(this, MyService::class.java)
		val methodChannel =
			MethodChannel(flutterEngine!!.dartExecutor.binaryMessenger, METHOD_CHANNEL)
		methodChannel.setMethodCallHandler { methodCall, result ->
			if (methodCall.method.equals(START_SERVICE)) {
				startService()
				result.success("Service started")
			} else if (methodCall.method.equals(STOP_SERVICE)) {
				stopService()
				result.success("Service stopped")
			}
		}
	}
	
	private fun startService() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			startForegroundService(serviceIntent)
		} else {
			startService(serviceIntent)
		}
	}
	
	private fun stopService() {
		stopService(serviceIntent)
	}
	
}
