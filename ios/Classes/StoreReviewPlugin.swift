import Flutter
import UIKit
import StoreKit

public class StoreReviewPlugin: NSObject, FlutterPlugin {
  public static func register(with registrar: FlutterPluginRegistrar) {
    let channel = FlutterMethodChannel(name: "store_review", binaryMessenger: registrar.messenger())
    let instance = StoreReviewPlugin()
    registrar.addMethodCallDelegate(instance, channel: channel)
  }

  public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
    switch call.method{
            case "openStoreReview":
            if #available(iOS 13.0, *) {
                if let scene = UIApplication.shared.connectedScenes.first(where: { $0.activationState == .foregroundActive }) as? UIWindowScene {
                    DispatchQueue.main.async {
                        if #available(iOS 14.0, *) {
                            SKStoreReviewController.requestReview(in: scene)
                        } else {
                            // Fallback on earlier versions
                        }
                        result("success")
                    }
                }
            } else {
                // Fallback on earlier versions
            }
             case "getPlatformVersion":
                result("iOS " + UIDevice.current.systemVersion)
                default:
                            result(FlutterMethodNotImplemented)

        }
      }
    }