package io.timon.flutter_benchmark

import android.content.Intent
import android.os.Bundle
import io.flutter.embedding.android.FlutterActivity
import io.timon.android.pixelsampler.PixelSampler

class MainActivity : FlutterActivity() {

    override fun onResume() {
        super.onResume()
        PixelSampler.start(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        PixelSampler.onActivityResult(requestCode, resultCode, data)
    }
}
