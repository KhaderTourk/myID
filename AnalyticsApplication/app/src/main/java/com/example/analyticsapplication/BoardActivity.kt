package com.example.analyticsapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class BoardActivity : AppCompatActivity() {

    lateinit var textView:TextView
    var db : SQL ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board)

        db = SQL(this)
        textView = findViewById(R.id.textView)

        val list = db!!.getAllPerson()

        var myText = ""
        for(i in list){
            myText += "\n ${i.id}  : ${i.pageName}  : ${i.time}seconds"
        }
        textView.text = myText

    }
}