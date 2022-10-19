package com.example.waseef.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.waseef.R
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@SuppressLint("CustomSplashScreen")
class CSplashActivity : AppCompatActivity() {

    private lateinit var videoView: VideoView
    private lateinit var whView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_csplash)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        videoView = findViewById(R.id.logo_exo)
        whView = findViewById(R.id.wh_view)

        lifecycleScope.launch {
            delay(600)
            whView.visibility = View.GONE
        }

        videoView.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.logo_motion))
        videoView.requestFocus()
        videoView.start()
        videoView.setOnCompletionListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

override fun onPause() {
    super.onPause()
    window.addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN)
}
}
