import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'store_review_platform_interface.dart';

/// An implementation of [StoreReviewPlatform] that uses method channels.
class MethodChannelStoreReview extends StoreReviewPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('store_review');

  @override
  Future<String?> getPlatformVersion() async {
    final version =
        await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }

  @override
  Future<void> openStoreReview(
      {String? appPackageName, String? storePackageName}) async {
    // TODO: implement openStoreReview
    await methodChannel.invokeMethod<String>('openStoreReview', {
      'appPackageName': appPackageName,
      'storePackageName': storePackageName
    });
  }
}
