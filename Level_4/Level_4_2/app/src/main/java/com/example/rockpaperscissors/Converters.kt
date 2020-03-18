package com.example.rockpaperscissors

import androidx.room.TypeConverter
import java.util.*

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun resultFromString(result: RPS.Result?): String? {
        return result?.value
    }

    @TypeConverter
    fun resultToString(value: String?): RPS.Result? {
        return RPS.Result.values().associateBy(RPS.Result::value)[value]
    }
}