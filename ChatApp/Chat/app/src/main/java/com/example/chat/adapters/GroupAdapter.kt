package com.example.splashandviewbager.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.splashandviewbager.R
import com.example.splashandviewbager.modles.Group

class GroupAdapter(val context: Context?, private val list: ArrayList<Group>, val click: onClick) :
    RecyclerView.Adapter<GroupAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate = LayoutInflater.from(context).inflate(R.layout.item_group, parent, false)
        return ViewHolder(inflate)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.room.text = list[position].room
        holder.card.setOnClickListener {
            click.onClickItem(holder.adapterPosition)
        }
    }


    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val room: TextView = item.findViewById(R.id.tv_group_room)

        val card: CardView = item.findViewById(R.id.group_card)
    }

    interface onClick {
        fun onClickItem(position: Int)
    }
}