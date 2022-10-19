package com.example.todo_for_blockchain.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.example.todo_for_blockchain.MySingleton
import com.example.todo_for_blockchain.R
import com.example.todo_for_blockchain.model.Task
import com.example.todo_for_blockchain.util.CCcompanion

class TaskAdapterNeed(private val progress: ProgressBar): RecyclerView.Adapter<TaskAdapterNeed.MyViewHolder>() {
    private var newsList = emptyList<Task>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var id: TextView = itemView.findViewById(R.id.tv_id)
        var title: TextView = itemView.findViewById(R.id.tv_title)
        var description: TextView = itemView.findViewById(R.id.tv_description)
        var date: TextView = itemView.findViewById(R.id.tv_date_complete)
        var toggle: TextView = itemView.findViewById(R.id.tv_toggle)
        var reward: TextView = itemView.findViewById(R.id.tv_reward)
        var isToggled: CheckBox = itemView.findViewById(R.id.cb_toggle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.task_item , parent , false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = newsList[position]
        holder.id.text = currentItem.id
        holder.title.text = currentItem.title
        holder.description.text = currentItem.description
        holder.date.text = currentItem.date
        holder.toggle.text = "Finish"
        holder.reward.text = currentItem.reward

        holder.isToggled.setOnCheckedChangeListener { _, _ ->
            progress.visibility = View.VISIBLE
            val uri = "${CCcompanion.MAIN_PATH}/toggleFinesh?purse=${CCcompanion.ADDRESS}&privateKey=${CCcompanion.PRIVATE_KEY}&taskId=${currentItem.id.toInt()}"

            val jsonObjectRequest = JsonObjectRequest(
                Request.Method.POST, uri, null,
                { response ->
                    Log.e("hzm", response.toString())
                    progress.visibility = View.GONE
                },
                { error ->
                    Log.e("hzm Error", error.toString())
                    progress.visibility = View.GONE
                })

            MySingleton.getInstance()!!.addRequestQueue(jsonObjectRequest)
        }

    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(new: List<Task>){
        this.newsList = new
        notifyDataSetChanged()
    }
}