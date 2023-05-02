package com.lahdev.platformscience.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lahdev.platformscience.PlatformScienceApp
import com.lahdev.platformscience.R
import com.lahdev.platformscience.adapter.DriverAdapter
import com.lahdev.platformscience.adapter.ShipmentAdapter
import com.lahdev.platformscience.contract.ShipmentsContract
import com.lahdev.platformscience.databinding.FragmentShipmentsBinding
import com.lahdev.platformscience.model.SsShipment
import com.lahdev.platformscience.model.entity.Driver
import com.lahdev.platformscience.model.entity.Shipment
import com.lahdev.platformscience.presenter.ShipmentPresenter

class ShipmentsFragment : Fragment(), ShipmentsContract.View, DriverAdapter.OnDriverClickListener,
    ShipmentAdapter.OnShipmentClickListener {
    private lateinit var binding: FragmentShipmentsBinding
    private lateinit var presenter: ShipmentsContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val db = (requireContext().applicationContext as PlatformScienceApp).db
        presenter = ShipmentPresenter(this, db)

        binding = FragmentShipmentsBinding.inflate(inflater)
        binding.driversRV.layoutManager = LinearLayoutManager(requireContext())
        binding.shipmentsRV.layoutManager = LinearLayoutManager(requireContext())

        binding.saveBtn.setOnClickListener { presenter.save() }
        binding.listBtn.setOnClickListener {
            it.findNavController()
                .navigate(R.id.action_shipmentsFragment_to_assignedShipmentsFragment)
        }

        presenter.loadData()

        return binding.root
    }

    override fun showDrivers(drivers: List<Driver>) {
        binding.driversRV.adapter = DriverAdapter(drivers, this)
        if (drivers.isEmpty()) binding.emptyDrivers.visibility = View.VISIBLE
        else binding.emptyDrivers.visibility = View.GONE

    }

    override fun showShipments(shipments: List<SsShipment>) {
        if (shipments.isEmpty()) binding.emptyShipments.visibility = View.VISIBLE
        else binding.emptyShipments.visibility = View.GONE
        binding.shipmentsRV.adapter = ShipmentAdapter(shipments, this)
    }


    override fun onDriverClick(driver: Driver) {
        presenter.setDriver(driver)
    }

    override fun onShipmentClick(shipment: Shipment) {
        presenter.setShipment(shipment)
    }

    override fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}