package com.example.caching.placies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.findFragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.caching.MainActivity
import com.example.caching.R

class PlaceAdapter: RecyclerView.Adapter<PlaceAdapter.MyViewHolder>() {
    private var userList = emptyList<Place>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.place_item , parent , false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.itemView.findViewById<TextView>(R.id.tv_place_title).text =currentItem.title
        Glide.with(holder.itemView)
            .load(currentItem.image)
            .into( holder.itemView.findViewById(R.id.iv_place))

        holder.itemView.findViewById<CardView>(R.id.place_row).setOnClickListener {
            MainActivity.from = "information"
            MainActivity.title = currentItem.title
            MainActivity.detail = currentItem.details
            MainActivity.image = currentItem.image
            NavHostFragment.findNavController(holder.itemView.findFragment()).navigate(R.id.action_informationFragment_to_detailsFragment)
        }

    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setData(user: List<Place>){
        this.userList = user
        notifyDataSetChanged()
    }
}