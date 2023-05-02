package com.lahdev.platformscience.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.lahdev.platformscience.databinding.ItemDriverBinding
import com.lahdev.platformscience.model.entity.Driver

class DriverViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val binding = ItemDriverBinding.bind(view)

    fun render(driver: Driver) {
        binding.nameTV.text = driver.name
    }
}