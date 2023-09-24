import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:store_review/store_review_method_channel.dart';

void main() {
  MethodChannelStoreReview platform = MethodChannelStoreReview();
  const MethodChannel channel = MethodChannel('store_review');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await platform.getPlatformVersion(), '42');
  });
}
