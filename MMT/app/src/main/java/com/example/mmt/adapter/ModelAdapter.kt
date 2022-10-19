package com.example.mmt.adapter

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipDescription
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.mmt.R
import com.example.mmt.model.Model

class ModelAdapter: RecyclerView.Adapter<ModelAdapter.MyViewHolder>() {
    private var newsList = emptyList<Model>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
       var modelTitle: TextView = itemView.findViewById(R.id.tv_tool_name)
        var toolImage: ImageView = itemView.findViewById(R.id.iv_model)
        var cvModel: CardView = itemView.findViewById(R.id.cv_model)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.mode_item , parent , false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = newsList[position]
        holder.modelTitle.text = currentItem.name
        holder.toolImage.setImageResource(currentItem.image)

        var clipText = "arm"
        when(currentItem.name){
            "الحامل" -> {clipText = "الحامل"}
            "لهب بنزن" -> {clipText = "لهب بنزن"}
            "الشبكة" -> {clipText = "الشبكة"}
            "زنك" -> {clipText = "زنك"}
            "برادة الخارصين" -> {clipText = "زنك"}
            "كبريت" -> {clipText = "كبريت"}
            "مسحوف كبريت" -> {clipText = "كبريت"}
            "وعاء" -> {clipText = "وعاء"}
            "ماء" -> {clipText = "ماء"}
            "كالسيوم" -> {clipText = "كالسيوم"}
            "دوار الشمس الأحمر" -> {clipText = "دوار الشمس الأحمر"}
            "دوار الشمس الأزرق" -> {clipText = "دوار الشمس الأزرق"}
            "أنبوب" -> {clipText = "أنبوب"}
        }
        holder.cvModel.setOnLongClickListener {
            val item = ClipData.Item(clipText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(clipText, mimeTypes, item)
            val dragShadowBuilder = View.DragShadowBuilder(holder.toolImage)
            holder.toolImage.startDragAndDrop(data, dragShadowBuilder, holder.toolImage, 0)
           // it.visibility = View.INVISIBLE
            true
        }



    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(new: List<Model>){
        this.newsList = new
        notifyDataSetChanged()
    }
}