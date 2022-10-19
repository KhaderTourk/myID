package com.example.cloudcomputingapplication.list

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cloudcomputingapplication.MainActivity
import com.example.cloudcomputingapplication.R
import com.example.cloudcomputingapplication.model.Person
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class ListFragment : Fragment() {

    private val adapter by lazy { ListAdapter() }
    lateinit var db: FirebaseFirestore
    lateinit var progressDialog: ProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

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
            findNavController().navigate(R.id.action_listFragment_to_homeFragment)
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

