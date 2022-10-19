package com.example.roomdbapplication.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdbapplication.R
import com.example.roomdbapplication.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*


class ListFragment : Fragment() {

    private val adapter by lazy { ListAdapter() }
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        //Recycle
        val recycle = view.usersRecycle
        recycle.adapter = adapter
        recycle.layoutManager = LinearLayoutManager(requireContext())
        //User Model

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer {user ->
            adapter.setData(user)
        })


        //Delete Menu
        setHasOptionsMenu(true)

        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu , menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete){
            deleteAllUsers()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllUsers() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setIcon(R.drawable.ic_delete)
            .setTitle("Delete All data !!")
            .setMessage("Are you sure delete everything ?")
            .setNegativeButton("No"){_,_ ->}
            .setPositiveButton("Yes"){_,_ ->
                mUserViewModel.deleteAllUsers()
                Toast.makeText(requireContext(),"Successfully deleted", Toast.LENGTH_LONG).show()
            }.create().show()
    }

}