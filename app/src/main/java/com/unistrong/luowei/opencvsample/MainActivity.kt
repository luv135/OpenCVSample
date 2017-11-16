package com.unistrong.luowei.opencvsample

import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.opencv.android.Utils
import org.opencv.core.Mat
import org.opencv.imgproc.Imgproc
import org.opencv.osgi.OpenCVNativeLoader
import android.graphics.Bitmap



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        OpenCVNativeLoader().init()
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.maxresdefault)
//        imageView.setImageBitmap(bitmap)
        val source = Mat()
        val dest = Mat()
        Utils.bitmapToMat(bitmap,source)
        Imgproc.cvtColor(source, dest, Imgproc.COLOR_RGB2GRAY);
        val btmp = Bitmap.createBitmap(dest.width(), dest.height(), Bitmap.Config.ARGB_8888)
        Utils.matToBitmap(dest, btmp)
        imageView.setImageBitmap(btmp)
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}
