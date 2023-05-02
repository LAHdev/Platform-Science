package com.lahdev.platformscience.util

import androidx.room.TypeConverter
import java.time.LocalDate

/**
 * Used for conversion in room in order to be able to use LocalDate as a column data type
 */
class LocalDateConverter {
    @TypeConverter
    fun fromLong(value: Long?): LocalDate? {
        return value?.let {
            LocalDate.ofEpochDay(it)
        }
    }

    @TypeConverter
    fun toLong(date: LocalDate?): Long? {
        return date?.toEpochDay()
    }
}
