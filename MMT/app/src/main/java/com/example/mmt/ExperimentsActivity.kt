package com.example.mmt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.mmt.labs.Lab2Activity
import com.example.mmt.labs.Lab3Activity
import com.example.mmt.labs.Lab4Activity
import com.example.mmt.labs.MainActivity

class ExperimentsActivity : AppCompatActivity() {

    private lateinit var lab1 : ConstraintLayout
    private lateinit var lab2 : ConstraintLayout
    private lateinit var lab3 : ConstraintLayout
    private lateinit var lab4 : ConstraintLayout

    private lateinit var title1 : TextView
    private lateinit var title2 : TextView
    private lateinit var title3 : TextView
    private lateinit var title4 : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_experiments)
        lab1 = findViewById(R.id.include_lab1)
        lab2 = findViewById(R.id.include_lab2)
        lab3 = findViewById(R.id.include_lab3)
        lab4 = findViewById(R.id.include_lab4)

        title1 = findViewById(R.id.tv_lab_title)
        title2 = findViewById(R.id.tv_lab_title2)
        title3 = findViewById(R.id.tv_lab_title3)
        title4 = findViewById(R.id.tv_lab_title4)

        lab1.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        lab2.setOnClickListener {
            val intent = Intent(this, Lab2Activity::class.java)
            startActivity(intent)
            finish()
        }

        lab3.setOnClickListener {
            val intent = Intent(this, Lab3Activity::class.java)
            startActivity(intent)
            finish()
        }

        lab4.setOnClickListener {
            val intent = Intent(this, Lab4Activity::class.java)
            startActivity(intent)
            finish()
        }
    }
}