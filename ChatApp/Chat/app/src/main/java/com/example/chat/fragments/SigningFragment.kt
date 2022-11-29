package com.example.splashandviewbager.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.splashandviewbager.R
import com.example.splashandviewbager.adapters.SigningPagerAdapter
import com.example.splashandviewbager.screens.logScreens.LoginScreen
import com.example.splashandviewbager.screens.logScreens.SignupScreen


class SigningFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_signing, container, false)

        val fragmentList = arrayListOf<Fragment>(
            LoginScreen(),
            SignupScreen()
        )

        val adapter =  SigningPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        val viewpager = view.findViewById<ViewPager2>(R.id.signingPager)
        viewpager.adapter = adapter

        return view
    }
}