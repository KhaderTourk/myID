package com.example.splashandviewbager.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.splashandviewbager.Units.Companion
import com.example.splashandviewbager.R
import com.example.splashandviewbager.modles.User

import java.util.ArrayList

/**
 * Created by Parsania Hardik on 26-Jun-17.
 */
class SearchAdapter(ctx: Context, private val imageModelArrayList: ArrayList<User>) :
    RecyclerView.Adapter<SearchAdapter.MyViewHolder>() {

    private val inflater: LayoutInflater
    private val arraylist: ArrayList<User>

    init {

        inflater = LayoutInflater.from(ctx)
        this.arraylist = ArrayList<User>()
        this.arraylist.addAll(Companion.usersArray)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapter.MyViewHolder {

        val view = inflater.inflate(R.layout.item_user, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchAdapter.MyViewHolder, position: Int) {

        holder.name.text = imageModelArrayList[position].name
        holder.phone.text = imageModelArrayList[position].phone
        holder.img.text = imageModelArrayList[position].name.substring(0, 2)
    }

    override fun getItemCount(): Int {
        return imageModelArrayList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var name: TextView
        var phone: TextView
        var img: TextView

        init {
            name = itemView.findViewById(R.id.tv_user_name) as TextView
            phone = itemView.findViewById(R.id.tv_user_phone) as TextView
            img = itemView.findViewById(R.id.user_img) as TextView
        }

    }
}