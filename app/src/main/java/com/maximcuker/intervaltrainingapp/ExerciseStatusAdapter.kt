package com.maximcuker.intervaltrainingapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_exercise_status.view.*

class ExerciseStatusAdapter(val items:ArrayList<ExerciseModel>, val context:Context): RecyclerView.Adapter<ExerciseStatusAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_exercise_status,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: ExerciseModel = items[position]
        holder.tvItem.text = model.id.toString()

        if (model.isSelected) {
            holder.tvItem.background = ContextCompat.getDrawable(context, R.drawable.item_circular_thin_color_accent_background)
            holder.tvItem.setTextColor(ContextCompat.getColor(context,R.color.black))
        } else if (model.isCompleted) {
            holder.tvItem.background = ContextCompat.getDrawable(context, R.drawable.item_circular_color_accent_background)
            holder.tvItem.setTextColor(ContextCompat.getColor(context,R.color.white))
        } else {
            holder.tvItem.background = ContextCompat.getDrawable(context, R.drawable.item_circular_color_gray_background)
            holder.tvItem.setTextColor(ContextCompat.getColor(context,R.color.black))

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view:View) : RecyclerView.ViewHolder(view) {
        val tvItem = view.tvItem
    }

}