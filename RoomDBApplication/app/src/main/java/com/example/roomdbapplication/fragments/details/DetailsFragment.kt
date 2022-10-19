package com.example.roomdbapplication.fragments.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.roomdbapplication.R
import com.example.roomdbapplication.fragments.update.UpdateFragmentArgs
import com.example.roomdbapplication.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_details.view.*


class DetailsFragment : Fragment() {

    private val args by navArgs<DetailsFragmentArgs>()
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_details, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.detailsTextView.setText("Name : ${args.userDetails.name} \n age : ${args.userDetails.age}")

        return view
    }

}