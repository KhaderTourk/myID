package com.example.analyticsapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.analytics.FirebaseAnalytics

class DetailsActivity : AppCompatActivity() {

    override fun onResume() {
        super.onResume()
        firstTime = (System.currentTimeMillis())/1000
    }

    private var mFirebaseAnalytics: FirebaseAnalytics? = null
    var db : SQL ?= null
    var firstTime:Long ?= null

    lateinit var img :ImageView
    lateinit var tv_name :TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        db = SQL(this)

        img = findViewById(R.id.iv_product_image)
        tv_name = findViewById(R.id.tv_name)

        when(MainActivity.product){
            "Cake" -> {
                img.setImageResource(R.drawable.cake)
                tv_name.text = "Cake"
            }
            "Cheese" -> {
                img.setImageResource(R.drawable.cheese)
                tv_name.text = "Cheese"
            }
            "Meat" -> {
                img.setImageResource(R.drawable.meat)
                tv_name.text = "Meat"
            }
            "Salad" -> {
                img.setImageResource(R.drawable.salad)
                tv_name.text = "Salad"
            }
            "Shirt" -> {
                img.setImageResource(R.drawable.shirt)
                tv_name.text = "Shirt"
            }
            "Jeans" -> {
                img.setImageResource(R.drawable.jeans)
                tv_name.text = "Jeans"
            }
            "Jacket" -> {
                img.setImageResource(R.drawable.jacket)
                tv_name.text = "Jacket"
            }
            "Shoe" -> {
                img.setImageResource(R.drawable.shoe)
                tv_name.text = "Shoe"
            }
            "TV" -> {
                img.setImageResource(R.drawable.tv)
                tv_name.text = "TV"
            }
            "IPad" -> {
                img.setImageResource(R.drawable.ipad)
                tv_name.text = "IPad"
            }
            "Phone" -> {
                img.setImageResource(R.drawable.phone)
                tv_name.text = "Phone"
            }
            "Charge" -> {
                img.setImageResource(R.drawable.charge)
                tv_name.text = "Charge"
            }

        }

    }
    private fun screenTrack():String{
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, "details_screen")
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "DetailsActivity")
        mFirebaseAnalytics!!.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)
        return "details_screen"
    }
    private fun addToDB(pageName : String , time :String){
        if (db!!.insertPerson(pageName , time)) {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onPause() {
        super.onPause()
        val time = ((System.currentTimeMillis())/1000) - firstTime!!
        addToDB(screenTrack() , time.toString())
    }

}