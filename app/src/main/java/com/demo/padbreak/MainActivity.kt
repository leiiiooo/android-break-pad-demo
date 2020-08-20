package com.demo.padbreak

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.leiiiooo.breakpad.BreakPadHelper
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {
    private lateinit var externalReportPath: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                WRITE_EXTERNAL_STORAGE_REQUEST_CODE
            )
        }

        crash.setOnClickListener {
            initExternalReportPath()
            initBreakPad()
            crash()
        }
    }

    private fun initExternalReportPath() {
        externalReportPath = File(getExternalFilesDir(null), "crashDump")
        if (!externalReportPath.exists()) {
            externalReportPath.mkdirs()
        }
    }

    private fun initBreakPad() {
        BreakPadHelper.initBreakPad(externalReportPath.absolutePath)
    }

    private external fun crash()

    companion object {
        private const val WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 100

        init {
            System.loadLibrary("crash-lib")
        }
    }
}