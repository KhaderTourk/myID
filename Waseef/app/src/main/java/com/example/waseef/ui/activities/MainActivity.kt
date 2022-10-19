package com.example.waseef.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.waseef.R
import com.example.waseef.adapter.TabsAdapter
import com.example.waseef.util.Constants.USER_ID
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {
    private lateinit var tabLayoutInvoice: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var addInvoiceFAB: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewsInit()
        addInvoiceFAB.setOnClickListener {
            val intent = Intent(this, AddNewInvoiceActivity::class.java)
            startActivity(intent)
            finish()
        }

    }


    private fun viewsInit(){
        addInvoiceFAB = findViewById(R.id.fab_add_invoice)
        tabLayoutInvoice = findViewById(R.id.tab_invoice)
        viewPager = findViewById(R.id.main_view_pager)
        tabLayoutInvoice.setupWithViewPager(viewPager)
        val menuAdapter = TabsAdapter(supportFragmentManager)
        viewPager.adapter = menuAdapter
        viewPager.offscreenPageLimit = 3
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.logout -> {
                val intent = Intent(this, LoginActivity::class.java)
                USER_ID = 0
                startActivity(intent)
                finish()
                true
            }
            else ->{
                return super.onOptionsItemSelected(item)
            }
        }

    }
}