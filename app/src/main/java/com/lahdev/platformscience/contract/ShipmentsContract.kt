package com.lahdev.platformscience.contract

import com.lahdev.platformscience.model.SsShipment
import com.lahdev.platformscience.model.entity.Driver
import com.lahdev.platformscience.model.entity.Shipment

interface ShipmentsContract {
    interface View {
        fun showDrivers(drivers: List<Driver>)
        fun showShipments(shipments: List<SsShipment>)
        fun showToast(message: String)
    }

    interface Presenter {
        fun loadData()
        fun setDriver(driver: Driver)
        fun setShipment(shipment: Shipment)
        fun save()
    }
}