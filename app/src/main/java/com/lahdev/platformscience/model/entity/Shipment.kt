package com.lahdev.platformscience.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class Shipment(
    @PrimaryKey val id: Long,
    @ColumnInfo val address: String,
    @ColumnInfo val date: LocalDate = LocalDate.now()
)
