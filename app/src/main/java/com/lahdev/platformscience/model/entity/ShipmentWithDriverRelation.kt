package com.lahdev.platformscience.model.entity

import androidx.room.Embedded
import androidx.room.Relation

data class ShipmentWithDriverRelation(
    @Embedded val shipmentHasDriver: ShipmentHasDriver,
    @Relation(parentColumn = "shipment_id", entityColumn = "id")
    val shipment: Shipment,
    @Relation(parentColumn = "fk_driver_id", entityColumn = "driver_id")
    val driver: Driver,
)

