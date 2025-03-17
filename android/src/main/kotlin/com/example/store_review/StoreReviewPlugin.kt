package com.example.store_review

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.TextUtils
import androidx.annotation.NonNull
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result




/** StoreReviewPlugin */
class StoreReviewPlugin: FlutterPlugin, ActivityAware, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private lateinit var channel: MethodChannel
  private lateinit var activity: Activity

  override fun onAttachedToActivity(binding: ActivityPluginBinding) {
    activity = binding.activity
  }

  override fun onDetachedFromActivityForConfigChanges() {
//    try {
//      channel.setMethodCallHandler(null)
//    }catch (Exception e){
//
//    }
  }
  override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
//    TODO("Not yet implemented")
  }

  override fun onDetachedFromActivity() {
//    try {
//      channel.setMethodCallHandler(null)
//    }catch (Exception e){
//
//    }
  }


  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "store_review")
    channel.setMethodCallHandler(this)
  }


  override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
    if (call.method == "getPlatformVersion") {
      result.success("Android ${android.os.Build.VERSION.RELEASE}")
    } else if(call.method == "openStoreReview"){
      var appPackageName: String? = call.argument("appPackageName")

      if(appPackageName == null) {
        result.error("UNAVAILABLE", "appPackageName 不能为空", null)
        return
      }
      try {
        val uri = Uri.parse("market://details?id=$appPackageName")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        activity.startActivity(intent)
      }catch (e: Exception){
        val msg = e.message
        result.error("UNAVAILABLE", "$msg", null)
      }

    }else {
      result.notImplemented()
    }
  }
  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }
}
