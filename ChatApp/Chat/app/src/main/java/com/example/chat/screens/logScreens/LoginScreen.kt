package com.example.splashandviewbager.screens.logScreens

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText

import android.widget.TextView
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.example.splashandviewbager.Units.Companion
import com.example.splashandviewbager.R
import com.example.splashandviewbager.screens.HomeScreens.UserScreen

class LoginScreen : Fragment() {



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment


        val view = inflater.inflate(R.layout.fragment_login_screen, container, false)
        val home : TextView = view.findViewById(R.id.home)

        val viewPager =  activity?.findViewById<ViewPager2>(R.id.signingPager)

        val signup :TextView = view.findViewById(R.id.btn_go_to_signup)
        val phone :EditText = view.findViewById(R.id.et_name)

        signup.setOnClickListener {
            viewPager?.currentItem = 1
        }

        home.setOnClickListener {
          //  onBoardingFinished()
            LogInAccount(phone.text.toString())
        }

    return view
    }

    private fun onLogout(){
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }

private fun LogInAccount(phone: String) {

    for (i in Companion.usersArray){
        if (i.phone == phone){
            Companion.myId = i.id
            Companion.myName = i.name
            Companion.myPhone = i.phone
            val intent = Intent(context , UserScreen::class.java)
            startActivity(intent)

        }
    }
        Toast.makeText(context, "User undefine .",Toast.LENGTH_SHORT).show()

}

}

