package com.example.todo_for_blockchain

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.example.todo_for_blockchain.adapter.MyPagerAdapter
import com.example.todo_for_blockchain.util.CCcompanion
import com.example.todo_for_blockchain.util.CCcompanion.Companion.ADDRESS
import com.example.todo_for_blockchain.util.CCcompanion.Companion.PRIVATE_KEY
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private lateinit var tvCoins: TextView
    private lateinit var tvLogout: TextView
    private lateinit var ivVisibility: ImageView
    private lateinit var fab: FloatingActionButton
    private var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null
    var isVis = true
    private lateinit var shared: SharedPreferences
    private lateinit var shareEditor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        shared = this.getSharedPreferences("wallet", Context.MODE_PRIVATE)
        shareEditor = shared.edit()
        fab = findViewById(R.id.fab_add)
        tvCoins = findViewById(R.id.tv_coins)
        tvLogout = findViewById(R.id.tv_logout)
        ivVisibility = findViewById(R.id.iv_visibility)

        getJSONObject(ADDRESS, PRIVATE_KEY)
        ivVisibility.setOnClickListener {
            if (isVis){
                ivVisibility.setImageResource(R.drawable.ic_visibility)
                tvCoins.text = CCcompanion.coins
                isVis = false
            }else{
                ivVisibility.setImageResource(R.drawable.ic_visibility_off)
                tvCoins.text = "****"
                isVis = true
            }
        }
        tvLogout.setOnClickListener {
            val intent = Intent(this, LogActivity::class.java)
            ADDRESS = ""
            PRIVATE_KEY = ""
            shareEditor.clear()
            shareEditor.apply()
            startActivity(intent)
        }


        fab.setOnClickListener {
            val intent = Intent(this, AddTaskActivity::class.java)
            startActivity(intent)
        }

        pagerInit()
        tvCoins.text = CCcompanion.coins
    }
    private fun getJSONObject(address: String, key: String) {
        val uriGetMyActions = "${CCcompanion.MAIN_PATH}/getUser?purse=${address}&privateKey=${key}"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, uriGetMyActions, null,
            { response ->
                val message = response.getString("message")
                if (message == "successfully"){
                    val jArray = response.getJSONArray("status")
                    val jObject = jArray.getJSONObject(2)
                    val coinInHex = jObject.getString("hex").substring(2)
                    val coin = coinInHex.toLong(radix = 16).toString()
                    CCcompanion.coins = coin
                }else{
                    CCcompanion.coins = "1000"
                }
            },
            { error ->
                Log.e("hzm Error", error.toString())
            })

        MySingleton.getInstance()!!.addRequestQueue(jsonObjectRequest)

    }

    private fun pagerInit() {
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)

        tabLayout!!.addTab(tabLayout!!.newTab().setText("All Tasks"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Need Action"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("My Actions"))
        tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = MyPagerAdapter(this, supportFragmentManager, tabLayout!!.tabCount)
        viewPager!!.adapter = adapter

        viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager!!.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }


}
