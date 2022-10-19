package com.example.caching.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.caching.MainActivity
import com.example.caching.R
import com.example.caching.databinding.FragmentInformationBinding
import com.example.caching.placies.PlaceAdapter
import com.example.caching.placies.Place
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class InformationFragment : Fragment() {

    private var _binding : FragmentInformationBinding? = null
    private val binding get() = _binding!!

    private lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInformationBinding.inflate(inflater, container, false)

        lifecycleScope.launch {
            getAllUsers()
            startAnimation()
        }

        bottomNavigate()
        backButtonHandle()

        return binding.root
    }
    private fun bottomNavigate(){
        binding.include.btnHome.setOnClickListener {
            lifecycleScope.launch {
                applyAnimation()
                findNavController().navigate(R.id.action_informationFragment_to_homeFragment)
            }
        }
        binding.include.btnHistory.setOnClickListener {
            lifecycleScope.launch {
                applyAnimation()
                findNavController().navigate(R.id.action_informationFragment_to_historyFragment)
            }
        }
    }

    private suspend fun applyAnimation() {
        binding.include.btnHome.isClickable = false
        binding.include.btnInformation.isClickable = false
        binding.include.btnHistory.isClickable = false

        binding.appBsrInclude.appBarMain.animate().alpha(0f).translationYBy(-200f).duration = 700L
        binding.include.btnHome.animate().alpha(0f).translationYBy(200f).duration = 400L
        binding.include.btnInformation.animate().alpha(0f).translationYBy(200f).duration = 600L
        binding.include.btnHistory.animate().alpha(0f).translationYBy(200f).duration = 800L

        delay(1)

    }

    private suspend fun startAnimation() {
        binding.include.btnInformationTv.setTextColor(resources.getColor(R.color.prime,null))
        DrawableCompat.setTint(
            DrawableCompat.wrap(binding.include.btnInformationIc.drawable),
            ContextCompat.getColor(requireContext(), R.color.prime)
        )
        binding.appBsrInclude.tvDate.text = MainActivity.date
        binding.include.lineInformation.setBackgroundResource(R.color.light)
        binding.appBsrInclude.appBarMain.animate().alpha(0f).translationY(-200f).duration = 10L
        binding.include.btnHome.animate().alpha(0f).translationY(200f).duration = 10L
        binding.include.btnInformation.animate().alpha(0f).translationY(200f).duration = 10L
        binding.include.btnHistory.animate().alpha(0f).translationYBy(200f).duration = 10L
        delay(200)
        binding.appBsrInclude.appBarMain.animate().alpha(1f).translationY(0f).duration = 700L
        binding.include.btnHome.animate().alpha(1f).translationY(0f).duration = 400L
        binding.include.btnInformation.animate().alpha(1f).translationY(0f).duration = 600L
        binding.include.btnHistory.animate().alpha(1f).translationY(0f).duration = 800L
    }

    private fun backButtonHandle(){
        val callBack = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                lifecycleScope.launch {
                    applyAnimation()
                    findNavController().navigate(R.id.action_informationFragment_to_homeFragment)
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callBack)
    }

    private fun getAllUsers(){
        db = FirebaseFirestore.getInstance()
        val placeAdapter = PlaceAdapter()
        val places = ArrayList<Place>()
       binding.progressBar.isVisible = true

        db.collection("JerusalemLocations")
            .get()
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot) {
                    val title = document.getString("title").toString()
                    val details = document.getString("details").toString()
                    val image = document.getString("image").toString()
                    places.add(
                        Place(


                            title,
                            details,
                            image
                        )
                    )
                }

                placeAdapter.setData(places)
                binding.progressBar.isVisible = false
                placeAdapter.notifyDataSetChanged()
                binding.placeRecyclerView.adapter = placeAdapter
                binding.placeRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            }
            .addOnFailureListener { exception ->
                Log.e("TAG", exception.message.toString())
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}