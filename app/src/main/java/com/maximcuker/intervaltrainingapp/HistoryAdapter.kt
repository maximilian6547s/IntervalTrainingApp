package com.maximcuker.intervaltrainingapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_history_row.view.*
import java.util.ArrayList

class HistoryAdapter(val context: Context, val items: ArrayList<String>) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_history_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val date: String = items.get(position)
        holder.tvPosition.text = (position + 1).toString()
        holder.tvItem.text = date

        if (position % 2 == 0) {
            holder.llHistoryMainItem.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.color_light_gray
                )
            )
        } else {
            holder.llHistoryMainItem.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.white
                )
            )
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val llHistoryMainItem = view.llHistoryItemMain
        val tvItem = view.tvItem
        val tvPosition = view.tvPosition
    }
}