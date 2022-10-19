package com.example.contactapplication

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore

class AllContactsFragment : Fragment() {

    private val adapter by lazy { ListAdapter() }
    lateinit var db: FirebaseFirestore
    lateinit var progressDialog: ProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_all_contacts, container, false)
        db = FirebaseFirestore.getInstance()
        progressDialog = ProgressDialog(requireContext())
        progressDialog.setMessage("loading....")
        progressDialog.setCancelable(false)
        progressDialog.show()

        MainActivity.person.clear()
        if( MainActivity.person.isEmpty()) {
            getAllUsers()

        }
        adapter.setData(MainActivity.person)

        //Recycle
        val recycle = view.findViewById<RecyclerView>(R.id.usersRecycle)

        //Delete Menu
        setHasOptionsMenu(true)

        view.findViewById<FloatingActionButton>(R.id.floatingActionButton).setOnClickListener {
            findNavController().navigate(R.id.action_allContactsFragment_to_addNewContactFragment)
        }
        recycle?.adapter = adapter
        recycle?.layoutManager = LinearLayoutManager(requireContext())

        return view
    }
    private fun getAllUsers(){

        db.collection("contacts")
            .get()
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot) {
                    val name = document.getString("name").toString()
                    val number = document.getString("number").toString()
                    val address = document.getString("address").toString()
                    MainActivity.person.add(
                        Person(
                            name,
                            number,
                            address
                        )
                    )
                    Log.e("TAG", "${document.getString("name").toString()} ")
                    Log.e("TAG", "${document.id} => ${document.getString("name")}")
                }
                progressDialog.dismiss()
                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                Log.e("TAG", exception.message.toString())
            }
    }

}