package com.example.store_review

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.TextUtils
import androidx.annotation.NonNull
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result


/** StoreReviewPlugin */
class StoreReviewPlugin: FlutterPlugin, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private lateinit var channel : MethodChannel

  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "store_review")
    channel.setMethodCallHandler(this)
  }

  override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
    if (call.method == "getPlatformVersion") {
      result.success("Android ${android.os.Build.VERSION.RELEASE}")
    } else if(call.method == "openStoreReview"){
      val storePackageName : String? = call.argument("storePackageName")
      val appPackageName : String? = call.argument("appPackageName")
      if(appPackageName == null) {
        println("appPackageName不能为空")
        return
      }
      launchAppDetail(appPackageName,storePackageName)

    }else {
      result.notImplemented()
    }
  }


  fun launchAppDetail(appPkg: String, marketPkg: String?) {
    try {
      if (TextUtils.isEmpty(appPkg)) return
      val uri = Uri.parse("market://details?id=$appPkg")

      val intent = Intent(Intent.ACTION_VIEW, uri)
      val context = getApplicationContext()
      if (!TextUtils.isEmpty(marketPkg)) {
        intent.setPackage(marketPkg)
      }
      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
      context.startActivity(intent)
    } catch (e: Exception) {
      e.printStackTrace()
    }
  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }
}
