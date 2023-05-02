package com.lahdev.platformscience.presenter

import com.lahdev.platformscience.contract.ShipmentsContract
import com.lahdev.platformscience.model.AppDatabase
import com.lahdev.platformscience.model.SsShipment
import com.lahdev.platformscience.model.entity.Driver
import com.lahdev.platformscience.model.entity.Shipment
import com.lahdev.platformscience.model.entity.ShipmentHasDriver
import com.lahdev.platformscience.util.TopSecretAlgorithm
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShipmentPresenter(
    private val view: ShipmentsContract.View,
    private val db: AppDatabase
) : ShipmentsContract.Presenter {
    private lateinit var shipmentsSS: ArrayList<SsShipment>
    private var selectedDriver: Driver? = null
    private var selectedShipment: Shipment? = null

    override fun loadData() {
        CoroutineScope(Dispatchers.IO).launch {
            val shipments = db.shipmentDao().getFree()
            val drivers = db.driverDao().getFree()
            shipmentsSS = ArrayList()

            for (shipment in shipments)
                shipmentsSS.add(SsShipment(shipment = shipment, ss = 0.0))

            withContext(Dispatchers.Main) {
                view.showShipments(shipmentsSS)
                view.showDrivers(drivers)
            }
        }
    }

    override fun setDriver(driver: Driver) {
        selectedDriver = driver
        for (item in shipmentsSS) {
            item.ss =
                TopSecretAlgorithm().calculateSuitabilityScore(driver.name, item.shipment.address)
        }
        shipmentsSS.sortByDescending { it.ss }
        view.showShipments(shipmentsSS)
    }

    override fun setShipment(shipment: Shipment) {
        selectedShipment = shipment
    }

    override fun save() {
        CoroutineScope(Dispatchers.IO).launch {
            if (selectedShipment != null && selectedDriver != null) {
                val shd = ShipmentHasDriver(
                    driverId = selectedDriver!!.driver_id,
                    shipmentId = selectedShipment!!.id
                )
                db.shipmentHasDriverDao().insert(shd)
                selectedDriver = null
                selectedShipment = null
                withContext(Dispatchers.Main) {
                    view.showToast("Successful driver assignment")
                }
                loadData()
            } else {
                withContext(Dispatchers.Main) {
                    view.showToast("Select a shipment and driver")
                }
            }
        }
    }
}