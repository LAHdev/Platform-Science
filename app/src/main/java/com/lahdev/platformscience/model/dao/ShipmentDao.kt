package com.lahdev.platformscience.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lahdev.platformscience.model.entity.Shipment
import java.time.LocalDate

@Dao
interface ShipmentDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(shipments: List<Shipment>)

    @Query("SELECT date FROM shipment order by date desc limit 1")
    fun getLastUpdate(): LocalDate

    @Query("SELECT * FROM shipment order by id asc")
    fun getAll(): List<Shipment>

    @Query("SELECT s.* FROM shipment s left join shipmenthasdriver shd on s.id=shd.shipment_id where shd.id is null order by id asc")
    fun getFree(): List<Shipment>
}