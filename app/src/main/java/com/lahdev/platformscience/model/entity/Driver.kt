package com.lahdev.platformscience.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["name"], unique = true)])
data class Driver(
    @PrimaryKey(autoGenerate = true) val driver_id: Long = 0,
    @ColumnInfo val name: String
)
