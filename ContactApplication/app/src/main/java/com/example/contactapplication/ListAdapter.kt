package com.example.contactapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    private var userList = emptyList<Person>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row , parent , false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.itemView.findViewById<TextView>(R.id.idTextView).text =currentItem.name
        holder.itemView.findViewById<TextView>(R.id.nameTextView).text =currentItem.number
        holder.itemView.findViewById<TextView>(R.id.ageTextView).text =currentItem.address

        holder.itemView.findViewById<LinearLayout>(R.id.rowLayout).setOnClickListener {
//            val action = ListFragmentDirections.actionListFragmentToDetailsFragment(currentItem)
//            holder.itemView.findNavController().navigate(action)
        }
        holder.itemView.findViewById<LinearLayout>(R.id.rowLayout).setOnLongClickListener {
//            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
//            holder.itemView.findNavController().navigate(action)
            return@setOnLongClickListener true
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setData(user: List<Person>){
        this.userList = user
        notifyDataSetChanged()
    }
}