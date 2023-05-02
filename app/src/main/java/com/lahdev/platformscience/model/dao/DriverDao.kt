package com.lahdev.platformscience.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lahdev.platformscience.model.entity.Driver

@Dao
interface DriverDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(drivers: List<Driver>)

    @Query("SELECT * FROM driver order by name asc")
    fun getAll(): List<Driver>

    @Query("SELECT d.* FROM driver d left join shipmenthasdriver shd on d.driver_id=shd.fk_driver_id where shd.id is null order by name asc")
    fun getFree(): List<Driver>
}