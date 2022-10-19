package com.example.caching.features.Parts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.findFragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.caching.MainActivity
import com.example.caching.R
import com.example.caching.data.Part
import com.example.caching.databinding.PartItemBinding

class PartAdapter : ListAdapter<Part, PartAdapter.PartViewHolder>(PartComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartViewHolder {
        val binding =
            PartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PartViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
           holder.bind(currentItem)
        }
    }

    class  PartViewHolder(private val binding: PartItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(part: Part){
            binding.apply {
                textViewTitle.text = part.title
                textViewShortd.text = part.discription
                row.setOnClickListener {
                    MainActivity.from = "history"
                    MainActivity.title = part.title
                    MainActivity.detail = part.discription
                    MainActivity.image = part.logo
                   findNavController(itemView.findFragment()).navigate(R.id.action_historyFragment_to_detailsFragment)
                }
            }
        }
    }
    class PartComparator : DiffUtil.ItemCallback<Part>() {
        override fun areItemsTheSame(oldItem: Part, newItem: Part) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Part, newItem: Part) =
            oldItem == newItem
    }
}