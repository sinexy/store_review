import 'package:flutter_test/flutter_test.dart';
import 'package:store_review/store_review.dart';
import 'package:store_review/store_review_platform_interface.dart';
import 'package:store_review/store_review_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockStoreReviewPlatform
    with MockPlatformInterfaceMixin
    implements StoreReviewPlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');

  @override
  Future<void> openStoreReview() {
    // TODO: implement openStoreReview
    throw UnimplementedError();
  }
}

void main() {
  final StoreReviewPlatform initialPlatform = StoreReviewPlatform.instance;

  test('$MethodChannelStoreReview is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelStoreReview>());
  });

  test('getPlatformVersion', () async {
    StoreReview storeReviewPlugin = StoreReview();
    MockStoreReviewPlatform fakePlatform = MockStoreReviewPlatform();
    StoreReviewPlatform.instance = fakePlatform;

    expect(await storeReviewPlugin.getPlatformVersion(), '42');
  });
}
