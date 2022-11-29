package com.example.splashandviewbager.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.splashandviewbager.Units.Constants.RECEIVE_ID
import com.example.splashandviewbager.Units.Constants.SEND_ID
import com.example.splashandviewbager.R
import com.example.splashandviewbager.modles.Message

class MessageAdapter(val context: Context?, private val list: ArrayList<Message>) :
    RecyclerView.Adapter<MessageAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate = LayoutInflater.from(context).inflate(R.layout.item_message, parent, false)
        return ViewHolder(inflate)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentMessage = list[position]

        when (currentMessage.id) {
            SEND_ID -> {
                holder.imgSender?.text = list[position].sender.substring(0,1)
                holder.messageSender?.text = list[position].message
                holder.timeSender?.text = list[position].time
                holder.senderLayout.apply {
                    visibility = View.VISIBLE
                }
                holder.receverLayout.visibility = View.GONE
            }
            RECEIVE_ID -> {
                holder.imgRecev?.text = list[position].sender.substring(0,2)
                holder.messageReceve?.text = list[position].message
                holder.timeReceve?.text = list[position].time
                holder.receverLayout.apply {
                    visibility = View.VISIBLE
                }
                holder.senderLayout.visibility = View.GONE
            }
        }

    }


    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val receverLayout = item.findViewById<LinearLayout>(R.id.tv_reciver_message)
        val imgRecev = item.findViewById<TextView>(R.id.message_img)
        val messageReceve = item.findViewById<TextView>(R.id.tv_message_text)
        val timeReceve = item.findViewById<TextView>(R.id.tv_message_time)

        val senderLayout = item.findViewById<LinearLayout>(R.id.tv_sender_message)
        val imgSender = item.findViewById<TextView>(R.id.message_img_sender)
        val messageSender = item.findViewById<TextView>(R.id.tv_message_text_sender)
        val timeSender = item.findViewById<TextView>(R.id.tv_message_time_sender)
    }


}