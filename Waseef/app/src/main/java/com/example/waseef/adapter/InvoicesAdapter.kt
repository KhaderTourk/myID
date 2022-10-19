package com.example.waseef.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.waseef.R
import com.example.waseef.model.InvoiceListItem

class InvoicesAdapter: RecyclerView.Adapter<InvoicesAdapter.MyViewHolder>() {
    private var invoices = emptyList<InvoiceListItem>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var name: TextView = itemView.findViewById(R.id.name)
        var date: TextView = itemView.findViewById(R.id.date)
        var description: TextView = itemView.findViewById(R.id.description)
        var price: TextView = itemView.findViewById(R.id.price)
        var statusC: TextView = itemView.findViewById(R.id.status_c)
        var statusF: TextView = itemView.findViewById(R.id.status_f)
        var statusP: TextView = itemView.findViewById(R.id.status_p)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.invoice_item , parent , false))
    }

    @SuppressLint("ResourceAsColor", "SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = invoices[position]
        holder.name.text = currentItem.FullName
        holder.date.text = currentItem.TransactionDate
        holder.description.text = currentItem.Description
        holder.price.text = currentItem.Amount.toString()

        holder.statusC.visibility = View.GONE
        holder.statusF.visibility = View.GONE
        holder.statusP.visibility = View.GONE

            when(currentItem.TransactionStatus){
            1 -> {
                holder.statusC.text ="Collected"
                holder.statusC.visibility = View.VISIBLE
            }
            0 -> {
                holder.statusP.text ="Pending"
                holder.statusP.visibility = View.VISIBLE
            }
            -1 -> {
                holder.statusF.text ="Failed"
                holder.statusF.visibility = View.VISIBLE
            }
           else -> {
               holder.statusP.text ="Pending"
               holder.statusP.visibility = View.VISIBLE
           }
        }

    }

    override fun getItemCount(): Int {
        return invoices.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(new: List<InvoiceListItem>){
        this.invoices = new
        notifyDataSetChanged()
    }
}