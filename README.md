# Flutter Performance Benchmark

A cross-platform performance benchmarking tool for Flutter applications. Measures app startup time and UI stability using platform-native SDKs.

## 🎯 Purpose

This project benchmarks Flutter app performance by measuring:
- **UI stability time** (when animations become stable)
- **App launch to scene stability duration**

Results are provided by platform-specific SDKs:
- **iOS**: Uses [PixelSamplerSDK](https://github.com/timionius/ios-frameworks-benchmark)
- **Android**: Uses [PixelSamplerSDK](https://github.com/timionius/android-frameworks-benchmark)

## ⚠️ Disclaimer
This SDK has been tested on the following devices only:

| Platform | Device/OS |
|----------|-----------|
| **Android** | Redmi Note 8 Pro (Android 10/Q) |
| **iOS** | iPhone 15 (iOS 17+) |

**Important Notes:**
- Results may vary across different devices, Android/iOS versions, and system configurations
- The benchmark numbers provided are for reference only and not guaranteed to be reproducible on all devices
- Build and test scripts assume a Unix-like environment (macOS/Linux)

## 🚀 Quick Start

### Prerequisites

#### For iOS Development
- macOS with Xcode 14.0+
- iOS 16.0+ device or simulator

#### For Android Development
- macOS / Linux 
- Android Studio or command line tools
- Android SDK (API 29+)
- NDK 28.2.13676358
- Java 17

### Clone the Repository

```bash
git clone https://github.com/timionius/flutter_benchmark.git
cd flutter_benchmark
```

## 📱 Android Benchmarking

### Build and Run

```bash
# Get Flutter dependencies
flutter pub get

# Build Android APK
flutter build apk --release

# Install with permissions (automatically grants screen capture permission)
adb install -r -g build/app/outputs/flutter-apk/app-release.apk

# Grant MediaProjection permission (bypasses dialog)
adb shell cmd appops set io.timon.flutter_benchmark PROJECT_MEDIA allow

# Launch the app
adb shell am start -n io.timon.flutter_benchmark/.MainActivity
```

### View Results
Monitor Logcat for benchmark output:
```bash
adb logcat | grep -E "BENCHMARK"
```
Monitor Logcat for SDK pipeline output:
```bash
adb logcat -s "PixelSampler:*" 
```

Expected output:
```text
... PixelSampler: ✅ [BENCHMARK] APP_START: 0,061ms
... PixelSampler: ✅ [BENCHMARK] FRAMEWORK_ENTRY: 75,332ms
... PixelSampler: ✅ [BENCHMARK] FRAMEWORK_ENTRY: 1482,931ms
... PixelSampler: ✅ [BENCHMARK] Total time: 2122,134ms
```

## 📱 iOS Benchmarking

### Run in Xcode
Open Xcode workspace.

Then:

1. Select your target device or simulator
2. Press Cmd + R to run
3. View benchmark results in Xcode console

### View Results
iOS benchmark results appear in Xcode console:

```text
🚀 [BENCHMARK] APP_START: 0.000ms
✅ [BENCHMARK] Render complete
✅ [BENCHMARK] Total time: 1276.8ms
```

## 📊 Understanding Results
| Metric | Description                                                 | Target                        |
|--------|-------------------------------------------------------------|-------------------------------|
| **APP_START** | Time from app launch to SDK initialization (base)                | 0                             |
| **FRAMEWORK_ENTRY** | Time whenever framework entrypoint is accessable (optional) | <200ms                        |  
| **RENDER_COMPLETE** | Time to UI stability (animation complete) | Depends on operation duration |

## 📄 License
```text
MIT License

Copyright (c) 2026 Dmitrii Nikishov

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```