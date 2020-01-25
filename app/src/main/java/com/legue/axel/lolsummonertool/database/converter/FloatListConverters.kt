package com.legue.axel.lolsummonertool.database.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FloatListConverters {

    @TypeConverter
    fun floatToList(value: String?): List<Float>? {
        if (value == null || value == "null") {
            return emptyList()
        }

        val listType = object : TypeToken<List<Float>>() {}.type
        val objects = Gson().fromJson(value, listType) as List<Float>
        return objects.toList()
    }

    @TypeConverter
    fun ListToString(descriptions: List<Float>?): String {
        return Gson().toJson(descriptions)
    }
}
