package com.lahdev.platformscience.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    indices = [Index(value = ["shipment_id", "fk_driver_id"])],
    tableName = "shipmenthasdriver",
    foreignKeys = [
        ForeignKey(
            entity = Shipment::class,
            parentColumns = ["id"],
            childColumns = ["shipment_id"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = Driver::class,
            parentColumns = ["driver_id"],
            childColumns = ["fk_driver_id"],
            onDelete = CASCADE
        )
    ]
)
data class ShipmentHasDriver(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    val id: Long = 0,

    @ColumnInfo(name = "shipment_id")
    val shipmentId: Long = 0,

    @ColumnInfo(name = "fk_driver_id")
    val driverId: Long = 0,
) {


}