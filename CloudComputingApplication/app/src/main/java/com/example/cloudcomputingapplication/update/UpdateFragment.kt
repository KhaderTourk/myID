package com.example.cloudcomputingapplication.update

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import com.example.cloudcomputingapplication.R


class UpdateFragment : Fragment() {

  // private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

//        view.findViewById<EditText>(R.id.updateName_et).setText(args.currentUser.name)
//        view.findViewById<EditText>(R.id.updateAge_et).setText(args.currentUser.age.toString())
//        view.findViewById<EditText>(R.id.update_btn).setOnClickListener {
      //          updateItem()
       // }

       // setHasOptionsMenu(true)

        return view
    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.delete_menu , menu)
//        super.onCreateOptionsMenu(menu, inflater)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (item.itemId == R.id.menu_delete){
//            deleteUser()
//        }
//        return super.onOptionsItemSelected(item)
//    }

//    private fun deleteUser() {
//        val builder = AlertDialog.Builder(requireContext())
//        builder.setIcon(R.drawable.ic_delete)
//            .setTitle("Delete ${args.currentUser.name} !!")
//            .setMessage("Are you sure delete ${args.currentUser.name} ?")
//            .setNegativeButton("No"){_,_ ->}
//            .setPositiveButton("Yes"){_,_ ->
//
//
//                Toast.makeText(requireContext(),"Successfully deleted",Toast.LENGTH_LONG).show()
//                findNavController().navigate(R.id.action_updateFragment_to_listFragment)
//            }.create().show()
//    }

//    private fun updateItem() {
//        val name = view?.findViewById<EditText>(R.id.updateName_et)?.text.toString()
//        val age = Integer.parseInt(view?.findViewById<EditText>(R.id.updateAge_et)?.text.toString())
//
//        if (inputCheck(name, view?.findViewById<EditText>(R.id.updateAge_et)?.text!!)){
//            val updateUser = User(args.currentUser.id, name , age)
//            mUserViewModel.updateUser(updateUser)
//            Toast.makeText(requireContext(),"Successfully updated",Toast.LENGTH_LONG).show()
//            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
//        }else{
//            Toast.makeText(requireContext(),"Please fill all fields!",Toast.LENGTH_LONG).show()
//        }
//    }

    private fun inputCheck(name: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(name) && age.isEmpty())
    }

}