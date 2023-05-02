package com.lahdev.platformscience.model

import com.lahdev.platformscience.model.entity.Driver
import com.lahdev.platformscience.model.entity.Shipment

data class SsShipment(
    val shipment: Shipment,
    val driver: Driver? = null,
    var ss: Double = 0.0
) {
}