package com.lahdev.platformscience.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lahdev.platformscience.R
import com.lahdev.platformscience.adapter.viewholder.DriverViewHolder
import com.lahdev.platformscience.model.entity.Driver

class DriverAdapter(private var list: List<Driver>, private val mListener: OnDriverClickListener) :
    RecyclerView.Adapter<DriverViewHolder>() {
    private var selectedItemPosition = -1

    interface OnDriverClickListener {
        fun onDriverClick(driver: Driver)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DriverViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return DriverViewHolder(layoutInflater.inflate(R.layout.item_driver, parent, false))
    }

    override fun onBindViewHolder(holder: DriverViewHolder, position: Int) {
        val itemValue = list[position]

        if (position == selectedItemPosition) {
            holder.binding.itemDriver.setBackgroundResource(R.drawable.rounded_border_fill_blue)
        } else {
            holder.binding.itemDriver.setBackgroundResource(R.drawable.rounded_border_blue)
        }
        holder.itemView.setOnClickListener {
            mListener.onDriverClick(itemValue)
            if (selectedItemPosition != position) {
                val previousSelected = selectedItemPosition
                selectedItemPosition = holder.adapterPosition
                notifyItemChanged(previousSelected)
                notifyItemChanged(selectedItemPosition)
            }
        }
        holder.render(itemValue)
    }


    override fun getItemCount(): Int = list.size

}