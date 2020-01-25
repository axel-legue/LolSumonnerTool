package com.legue.axel.lolsummonertool.database.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DoubleListConverters {
    @TypeConverter
    fun doubleToList(value: String?): List<Double>? {
        if (value == null || value == "null") {
            return emptyList()
        }

        val listType = object : TypeToken<List<Double>>() {}.type
        val objects = Gson().fromJson(value, listType) as List<Double>
        return objects.toList()
    }

    @TypeConverter
    fun ListToString(descriptions: List<Double>): String {
        return Gson().toJson(descriptions)
    }
}
