package com.legue.axel.lolsummonertool.database.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DoubleMapConverters {

    @TypeConverter
    fun fromString(value: String?): Map<String, Double>? {

        if (value == null || value == "null") {
            return emptyMap()
        }

        val mapType = object : TypeToken<Map<String, Double>>() {}.type
        val objects = Gson().fromJson(value, mapType) as Map<String, Double>
        return objects.toMap()
    }

    @TypeConverter
    fun fromDoubleMap(map: Map<String, Double>): String {
        return Gson().toJson(map)
    }
}
