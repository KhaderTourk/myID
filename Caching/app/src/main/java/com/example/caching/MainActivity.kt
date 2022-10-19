package com.example.caching

import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import dagger.hilt.android.AndroidEntryPoint
import java.text.DateFormat

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private  lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Caching)
        setContentView(R.layout.activity_main)
        supportActionBar!!.hide()

        navController = findNavController(R.id.fragment)
        setupActionBarWithNavController(navController)
    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    companion object{
        var from = ""
        var title = ""
        var image = ""
        var detail = ""
        var video = ""
        var date = DateFormat.getInstance().format(Calendar.getInstance().time).split(" ")[0].trim()
    }

}

