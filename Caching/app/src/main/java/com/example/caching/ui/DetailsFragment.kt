package com.example.caching.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.caching.MainActivity
import com.example.caching.R
import com.example.caching.databinding.FragmentDetailsBinding
import kotlinx.coroutines.launch


class DetailsFragment : Fragment() {

    private var _binding : FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        lifecycleScope.launch { displayData() }
       binding.backFromDetails.setOnClickListener { handleBack() }
        backButtonHandle()

        return binding.root
    }

    private fun displayData(){
        Glide.with(this@DetailsFragment)
            .load(MainActivity.image)
            .into(binding.ivDetails)
        binding.tvDetailsTitle.text = MainActivity.title
        binding.tvDetailsDiscription.text = MainActivity.detail
    }

    private fun handleBack(){
        when (MainActivity.from) {
            "history" -> findNavController().navigate(R.id.action_detailsFragment_to_historyFragment)
            "information" -> findNavController().navigate(R.id.action_detailsFragment_to_informationFragment)
            else -> findNavController().navigate(R.id.action_detailsFragment_to_homeFragment)
        }
    }

    private fun backButtonHandle(){
        val callBack = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                handleBack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callBack)
    }
}