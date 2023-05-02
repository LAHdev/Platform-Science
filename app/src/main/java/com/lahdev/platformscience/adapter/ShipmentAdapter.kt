package com.lahdev.platformscience.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lahdev.platformscience.R
import com.lahdev.platformscience.adapter.viewholder.ShipmentViewHolder
import com.lahdev.platformscience.model.SsShipment
import com.lahdev.platformscience.model.entity.Shipment

class ShipmentAdapter(
    private val list: List<SsShipment>,
    private val mListener: OnShipmentClickListener
) :
    RecyclerView.Adapter<ShipmentViewHolder>() {
    private var selectedItemPosition = -1

    interface OnShipmentClickListener {
        fun onShipmentClick(shipment: Shipment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShipmentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ShipmentViewHolder(layoutInflater.inflate(R.layout.item_shipment, parent, false))
    }

    override fun onBindViewHolder(holder: ShipmentViewHolder, position: Int) {
        val itemValue = list[position]

        if (itemValue.ss > 0 && (position == 0 || itemValue.ss == list[0].ss) && position == selectedItemPosition)
            holder.binding.itemShipment.setBackgroundResource(R.drawable.rounded_border_fill_orange)
        else if (itemValue.ss > 0 && (position == 0 || itemValue.ss == list[0].ss))
            holder.binding.itemShipment.setBackgroundResource(R.drawable.rounded_border_orange)
        else {
            if (position == selectedItemPosition) {
                holder.binding.itemShipment.setBackgroundResource(R.drawable.rounded_border_fill_gray)
            } else {
                holder.binding.itemShipment.setBackgroundResource(R.drawable.rounded_border_green)
            }
        }
        holder.itemView.setOnClickListener {
            mListener.onShipmentClick(itemValue.shipment)
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