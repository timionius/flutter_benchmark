package io.timon.flutter_benchmark

import io.flutter.app.FlutterApplication
import io.timon.android.pixelsampler.PixelSampler

class Application  : FlutterApplication() {
    companion object {
        init {
            PixelSampler.init()
        }
    }
}