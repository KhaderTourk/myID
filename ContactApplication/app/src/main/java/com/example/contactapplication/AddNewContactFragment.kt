package com.example.contactapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.contactapplication.databinding.FragmentAddNewContactBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AddNewContactFragment : Fragment() {

    private var _binding : FragmentAddNewContactBinding? = null
    private val binding get() = _binding!!
    lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddNewContactBinding.inflate(inflater, container, false)
        db = FirebaseFirestore.getInstance()

        setHasOptionsMenu(true)

        binding.generateButton.setOnClickListener {
            onGenerateClick()
        }

        return binding.root
    }

    private fun onGenerateClick(){
        val name = binding.plainTextName.text.toString()
        val number = binding.plainTextNumber.text.toString()
        val address = binding.plainText.text.toString()

        if(address.isEmpty() || number.isEmpty() ||  name.isEmpty()){
            showSnackBar("Some Field Empty.")
        }else{
            lifecycleScope.launch {
                addPersonToDB(name,number,address)
                applyAnimation()

                navigateToSuccess("vbbbb")
            }
        }
    }

    private fun addPersonToDB(name: String, number: String, address: String) {

        val newPerson = hashMapOf("name" to name, "number" to number, "address" to address)
        lifecycleScope.launch {
            db.collection("contacts")
                .add(newPerson)
                .addOnSuccessListener { documentReference ->
                    Log.e("TAG", "User added Successfully with user id ${documentReference.id}")
                }
                .addOnFailureListener { exception ->
                    Log.e("TAG", exception.message.toString())
                }
        }
    }

    private suspend fun applyAnimation(){
        binding.generateButton.isClickable = false
        binding.titleTv.animate().alpha(0f).duration = 400L
        binding.generateButton.animate().alpha(0f).duration = 400L
        binding.plainTextName.animate().alpha(0f).translationXBy(1200f).duration = 400L
        binding.plainTextNumber.animate().alpha(0f).translationXBy(1200f).duration = 400L
        binding.plainText.animate().alpha(0f).translationXBy(-1200f).duration = 400L

        delay(300)

        binding.successBackground.animate().alpha(1f).duration = 600L
        binding.successBackground.animate().rotationBy(720f).duration = 600L
        binding.successBackground.animate().scaleXBy(900f).duration = 800L
        binding.successBackground.animate().scaleYBy(900f).duration = 800L

        delay(500)

        binding.successImageView.animate().alpha(1f).duration = 1000L

        delay(1500)
    }

    private fun navigateToSuccess(hash:String){
        findNavController().navigate(R.id.action_addNewContactFragment_to_allContactsFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun showSnackBar(message:String){
        val snackBar = Snackbar.make(binding.rootLayout,message, Snackbar.LENGTH_SHORT)
        snackBar.setAction("Okay"){}
        snackBar.setActionTextColor(ContextCompat.getColor(requireContext(), R.color.blue))
        snackBar.show()
    }


}