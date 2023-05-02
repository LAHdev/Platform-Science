package com.lahdev.platformscience.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.lahdev.platformscience.databinding.ItemShipmentBinding
import com.lahdev.platformscience.model.SsShipment

class ShipmentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val binding = ItemShipmentBinding.bind(view)

    fun render(item: SsShipment) {
        binding.commonIdTV.text = item.shipment.id.toString()
        binding.commonAddressTV.text = item.shipment.address

        if (item.ss == 0.0) binding.scoreRow.visibility = View.GONE
        else {
            binding.scoreRow.visibility = View.VISIBLE
            binding.scoreTv.text = item.ss.toString()
        }
    }
}