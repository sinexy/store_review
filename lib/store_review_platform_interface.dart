import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'store_review_method_channel.dart';

abstract class StoreReviewPlatform extends PlatformInterface {
  /// Constructs a StoreReviewPlatform.
  StoreReviewPlatform() : super(token: _token);

  static final Object _token = Object();

  static StoreReviewPlatform _instance = MethodChannelStoreReview();

  /// The default instance of [StoreReviewPlatform] to use.
  ///
  /// Defaults to [MethodChannelStoreReview].
  static StoreReviewPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [StoreReviewPlatform] when
  /// they register themselves.
  static set instance(StoreReviewPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<void> openStoreReview({String? appPackageName,String? storePackageName}) async {
    throw UnimplementedError('openStoreReview() has not been implemented.');
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }
}
