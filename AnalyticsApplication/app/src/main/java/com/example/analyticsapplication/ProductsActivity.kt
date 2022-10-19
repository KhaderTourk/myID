package com.example.analyticsapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.analytics.FirebaseAnalytics

class ProductsActivity : AppCompatActivity() {

    private var mFirebaseAnalytics: FirebaseAnalytics? = null
    var db : SQL ?= null
    var firstTime:Long ?= null
    var products  = ArrayList<String>()

    lateinit var p1: Button
    lateinit var p2: Button
    lateinit var p3: Button
    lateinit var p4: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        when(MainActivity.category){
            1 -> {
                products.add("Cake")
                products.add("Cheese")
                products.add("Meat")
                products.add("Salad")
            }
            2 -> {
                products.add("Shirt")
                products.add("Jeans")
                products.add("Jacket")
                products.add("Shoe")
            }
            3 -> {
                products.add("TV")
                products.add("IPad")
                products.add("Phone")
                products.add("Charge")
            }
        }

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        db = SQL(this)

        p1 = findViewById(R.id.p1)
        p1.text = products[0]
        p1.setOnClickListener {
            MainActivity.product = products[0]
            allWork("id_${products[0]}_item" , "${products[0]}_button" )
        }
        p2 = findViewById(R.id.p2)
        p2.text = products[1]
        p2.setOnClickListener {
            MainActivity.product = products[1]
            allWork( "id_${products[1]}_item" , "${products[1]}_button")
        }
        p3 = findViewById(R.id.p3)
        p3.text = products[2]
        p3.setOnClickListener {
            MainActivity.product = products[2]
            allWork( "id_${products[2]}_item" , "${products[2]}_button" )
        }
        p4 = findViewById(R.id.p4)
        p4.text = products[3]
        p4.setOnClickListener {
            MainActivity.product = products[3]
            allWork( "id_${products[3]}_item" , "${products[3]}_button" )
        }

    }
    private fun allWork( itemId : String , itemName : String ){
        val time = ((System.currentTimeMillis())/1000) - firstTime!!
        addToDB(screenTrack(),time.toString())
        clickEvent(itemId,itemName)
        Toast.makeText(this, "Success $time ", Toast.LENGTH_LONG).show()
        val intent = Intent(this , DetailsActivity::class.java)
        startActivity(intent)
    }
    private fun clickEvent(id: String, name: String){
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, id)
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name)
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "Button")
        mFirebaseAnalytics!!.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)
    }
    private fun screenTrack():String{
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, "products_screen")
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "ProductsActivity")
        mFirebaseAnalytics!!.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)
        return "products_screen"
    }
    private fun addToDB(pageName : String , time :String){
        if (db!!.insertPerson(pageName , time)) {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        firstTime = (System.currentTimeMillis())/1000
    }
}