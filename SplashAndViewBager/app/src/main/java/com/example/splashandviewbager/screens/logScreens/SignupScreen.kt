package com.example.splashandviewbager.screens.logScreens

import android.os.Bundle
import android.util.Log
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
import com.example.splashandviewbager.modles.User


class SignupScreen : Fragment() {

    var name:String? = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_signup_screen, container, false)

        val viewPager =  activity?.findViewById<ViewPager2>(R.id.signingPager)
        val submit : TextView = view.findViewById(R.id.btn_submit)
        val signin : TextView = view.findViewById(R.id.btn_go_to_signin)
        val et_name : EditText = view.findViewById(R.id.et_add_email)
        val phone : EditText = view.findViewById(R.id.et_add_password)

        signin.setOnClickListener {
            viewPager?.currentItem = 0
        }

        submit.setOnClickListener {
            //  onBoardingFinished()
            name = et_name.text.toString()
            if (phone.text.toString() != "" && name != "" ) {
                createNewAccount( phone.text.toString() , name.toString())
                viewPager?.currentItem = 0
            }else{

                Toast.makeText(context,"Please fill the filds !", Toast.LENGTH_SHORT).show()
            }
        }
        return view
    }

    private fun createNewAccount(phone: String, name: String) {
        try {
            Log.e("signup", Companion.usersArray.toString())
            Log.e("signup", Companion.usersArray.size.toString())
            var isTrue = false
            for (i in  Companion.usersArray) {
                Log.e("signup","2")
                if (phone == i.phone){
                    isTrue = true
                    Toast.makeText(context,"Phone is used", Toast.LENGTH_SHORT).show()
                    break
                }
                Log.e("signup","5")}
            if (!isTrue){
                    Companion.isNotExist = true
                    Companion.myName = name
                    Companion.myPhone = phone
                    Companion.myId = phone
                    Companion.usersArray.add(User(phone, name, phone))
                    Log.e("signup","4")
            }

        }catch (e : Exception){
            Log.e("signup",e.message.toString())
        }


    }


}