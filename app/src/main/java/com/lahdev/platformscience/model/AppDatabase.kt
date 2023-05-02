package com.lahdev.platformscience.model

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lahdev.platformscience.model.dao.DriverDao
import com.lahdev.platformscience.model.dao.ShipmentDao
import com.lahdev.platformscience.model.dao.ShipmentHasDriverDao
import com.lahdev.platformscience.model.entity.Driver
import com.lahdev.platformscience.model.entity.Shipment
import com.lahdev.platformscience.model.entity.ShipmentHasDriver
import com.lahdev.platformscience.util.LocalDateConverter

@Database(entities = [Shipment::class, Driver::class, ShipmentHasDriver::class], version = 1)
@TypeConverters(LocalDateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun shipmentDao(): ShipmentDao
    abstract fun driverDao(): DriverDao
    abstract fun shipmentHasDriverDao(): ShipmentHasDriverDao
}