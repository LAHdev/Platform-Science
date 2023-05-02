package com.lahdev.platformscience.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lahdev.platformscience.R
import com.lahdev.platformscience.adapter.viewholder.ShipmentWithDriverViewHolder
import com.lahdev.platformscience.model.entity.ShipmentWithDriverRelation

class ShipmentWithDriverAdapter(private val list: List<ShipmentWithDriverRelation>) :
    RecyclerView.Adapter<ShipmentWithDriverViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ShipmentWithDriverViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ShipmentWithDriverViewHolder(
            layoutInflater.inflate(
                R.layout.item_assigned_shipment,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ShipmentWithDriverViewHolder, position: Int) {
        val item = list[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = list.size
}