package com.to0lfor.matches

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pt.tornelas.segmentedprogressbar.SegmentedProgressBar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spb = findViewById<SegmentedProgressBar>(R.id.spb)
        spb.start()
    }
}
