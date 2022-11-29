package com.example.splashandviewbager.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.splashandviewbager.R
import com.example.splashandviewbager.modles.User

class UserAdapter(val context: Context?, private val list: ArrayList<User>, val click: onClick) :
        RecyclerView.Adapter<UserAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false)
        return ViewHolder(inflate)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.img?.text = list[position].name.substring(0, 1)
            holder.phone?.text = list[position].phone
            holder.name?.text = list[position].name
            holder.card.setOnClickListener {
                click.onClickItem(holder.adapterPosition)
        }
    }


    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val img = item.findViewById<TextView>(R.id.user_img)
        val phone = item.findViewById<TextView>(R.id.tv_user_phone)
        val name = item.findViewById<TextView>(R.id.tv_user_name)
        val card = item.findViewById<CardView>(R.id.cardview)

    }

    interface onClick {
        fun onClickItem(position: Int)
    }
}