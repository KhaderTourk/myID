package com.example.todo_for_blockchain.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_for_blockchain.R
import com.example.todo_for_blockchain.model.Task

class TaskAdapterActions: RecyclerView.Adapter<TaskAdapterActions.MyViewHolder>() {
    private var newsList = emptyList<Task>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var id: TextView = itemView.findViewById(R.id.tv_id)
        var title: TextView = itemView.findViewById(R.id.tv_title)
        var description: TextView = itemView.findViewById(R.id.tv_description)
        var date: TextView = itemView.findViewById(R.id.tv_date_complete)
        var toggle: TextView = itemView.findViewById(R.id.tv_toggle)
        var isToggled: CheckBox = itemView.findViewById(R.id.cb_toggle)
        var reward: TextView = itemView.findViewById(R.id.tv_reward)
        var cvModel: CardView = itemView.findViewById(R.id.cv_task)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.task_item , parent , false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = newsList[position]
        holder.id.text = currentItem.id
        holder.title.text = currentItem.title
        holder.description.text = currentItem.description
        holder.reward.text = currentItem.reward
        if (currentItem.date.contains("1970")){
            holder.date.visibility = View.GONE
        }else{
            holder.date.text = currentItem.date
        }

        holder.isToggled.isEnabled = false
        if (currentItem.isToggled){
            holder.isToggled.isChecked = true
            holder.toggle.text = "Finished"
        }else if (currentItem.isDone){
            holder.isToggled.isChecked = true
            holder.toggle.text = "Done"
        }else{
            holder.isToggled.isChecked = false
            holder.toggle.text = "Done"
        }


        holder.cvModel.setOnClickListener {


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