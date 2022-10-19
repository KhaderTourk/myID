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
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.example.caching.MainActivity
import com.example.caching.R
import com.example.caching.databinding.FragmentHomeBinding
import com.example.caching.news.News
import com.example.caching.news.NewsAdapter
import com.example.caching.news.VolleySingleton
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val url = "https://script.google.com/macros/s/AKfycbzJJRZB2M9qhdNpZGddG6mMfmMAfr6JKnL-WNr06TH-T_aHhOq-xdUbjds6lrMi-HMpZw/exec"

    private lateinit var newsAdapter: NewsAdapter
    private lateinit var newsList: ArrayList<News>

    override fun onCreateView(inflater: LayoutInflater
                              , container: ViewGroup?
                              , savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        lifecycleScope.launch {
            getDataFromApi()
            startAnimation()
        }
        bottomNavigate()

        return binding.root
    }

    private fun bottomNavigate() {
        binding.include.btnInformation.setOnClickListener {
            lifecycleScope.launch {
                applyAnimation()
                findNavController().navigate(R.id.action_homeFragment_to_informationFragment)
            }

        }
        binding.include.btnHistory.setOnClickListener {
            lifecycleScope.launch {
                applyAnimation()
                findNavController().navigate(R.id.action_homeFragment_to_historyFragment)
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
        binding.include.btnHomeTv.setTextColor(resources.getColor(R.color.prime,null))
        DrawableCompat.setTint(
            DrawableCompat.wrap(binding.include.btnHomeIc.drawable),
            ContextCompat.getColor(requireContext(), R.color.prime)
        )
        binding.include.lineHome.setBackgroundResource(R.color.light)
        binding.appBsrInclude.tvDate.text = MainActivity.date
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

    private fun getDataFromApi(){

            newsList = ArrayList()
            binding.progressBar.isVisible = true
        val imageList = ArrayList<SlideModel>() // Create image list
        val videoList = ArrayList<String>() // Create image list

            val jsonArrayRequest = JsonArrayRequest(
                Request.Method.GET, url, null,
                { response ->
                    Log.e("kh", response.toString())
                    binding.progressBar.isVisible = false

                        for (i in 0 until response.length()) {
                            newsList.add(
                                News(
                                    response.getJSONObject(i).getString("info_id"),
                                    response.getJSONObject(i).getString("info_title"),
                                    response.getJSONObject(i).getString("info_details"),
                                    response.getJSONObject(i).getString("info_author"),
                                    response.getJSONObject(i).getString("info_date")
                                )
                            )
                        }
                    for (i in 0 until response.length()-1) {
                        imageList.add(SlideModel(response.getJSONObject(i).getString("news_image"), response.getJSONObject(i).getString("news_title")))
                        videoList.add(response.getJSONObject(i).getString("news_video"))
                    }
                    binding.imageSlider.setImageList(imageList, ScaleTypes.FIT)
                    binding.imageSlider.setItemClickListener(object : ItemClickListener {
                        override fun onItemSelected(position: Int) {
                            lifecycleScope.launch {
                                applyAnimation()
                                MainActivity.video = videoList[position]
                                findNavController().navigate(R.id.action_homeFragment_to_videoOffloadingFragment)
                            }
                        }
                    })
                    newsAdapter = NewsAdapter(requireContext(), newsList)
                    binding.newsRecycle.adapter = newsAdapter
                    binding.newsRecycle.layoutManager = LinearLayoutManager(requireContext())
                },
                { error ->
                    Log.e("kh", error.message.toString())
                })
            VolleySingleton.getInstance(requireContext()).addToRequestQueue(jsonArrayRequest)
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

