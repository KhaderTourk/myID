package com.example.caching.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.caching.MainActivity
import com.example.caching.R
import com.example.caching.databinding.FragmentHistoryBinding
import com.example.caching.features.Parts.PartAdapter
import com.example.caching.features.Parts.PartViewModel
import com.example.caching.util.Resourse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HistoryFragment : Fragment() {

    private val viewModel: PartViewModel by viewModels()

    private var _binding : FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)

        lifecycleScope.launch { startAnimation() }
        setAdapter()
        backButtonHandle()

        return binding.root
    }
    private fun bottomNavigate(){
        binding.include.btnHome.setOnClickListener {
            lifecycleScope.launch {
                applyAnimation()
                findNavController().navigate(R.id.action_historyFragment_to_homeFragment)
            }

        }
        binding.include.btnInformation.setOnClickListener {
            lifecycleScope.launch {
                applyAnimation()
                findNavController().navigate(R.id.action_historyFragment_to_informationFragment)
            }

        }
    }

    private fun setAdapter(){
        val partAdapter = PartAdapter()
        bottomNavigate()
        binding.apply {
            recyclerView2.apply {
                adapter = partAdapter
                layoutManager = LinearLayoutManager(context)
            }
            viewModel.parts.observe(viewLifecycleOwner) { result ->
                partAdapter.submitList(result.data)
                progressBar2.isVisible = result is Resourse.Loading && result.data.isNullOrEmpty()
                textViewError2.isVisible = result is Resourse.Error && result.data.isNullOrEmpty()
                textViewError2.text = result.error?.localizedMessage
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
        binding.include.btnHistoryTv.setTextColor(resources.getColor(R.color.prime,null))
        DrawableCompat.setTint(
            DrawableCompat.wrap(binding.include.btnHistoryIc.drawable),
            ContextCompat.getColor(requireContext(), R.color.prime)
        )
        binding.appBsrInclude.tvDate.text = MainActivity.date
        binding.include.lineHistory.setBackgroundResource(R.color.light)
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
                    findNavController().navigate(R.id.action_historyFragment_to_homeFragment)
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callBack)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}