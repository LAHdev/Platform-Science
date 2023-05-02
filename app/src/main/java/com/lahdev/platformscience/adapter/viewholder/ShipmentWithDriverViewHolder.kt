package com.lahdev.platformscience.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.lahdev.platformscience.databinding.ItemAssignedShipmentBinding
import com.lahdev.platformscience.model.entity.ShipmentWithDriverRelation
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

class ShipmentWithDriverViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val binding = ItemAssignedShipmentBinding.bind(view)

    fun render(shipment: ShipmentWithDriverRelation) {
        binding.commonIdTV.text = shipment.shipment.id.toString()
        binding.commonAddressTV.text = shipment.shipment.address
        binding.driverNameTV.text = shipment.driver.name

        val dateFormatter =
            DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(Locale.US)
        binding.dateTv.text = shipment.shipment.date.format(dateFormatter)
    }
}