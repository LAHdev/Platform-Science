package com.lahdev.platformscience.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.lahdev.platformscience.model.entity.ShipmentHasDriver
import com.lahdev.platformscience.model.entity.ShipmentWithDriverRelation


@Dao
interface ShipmentHasDriverDao {
    @Insert
    fun insert(shipmentHasDriver: ShipmentHasDriver)

    @Query("SELECT * FROM shipmenthasdriver order by shipment_id asc")
    fun getAllShipmentsWithDrivers(): List<ShipmentWithDriverRelation>
}