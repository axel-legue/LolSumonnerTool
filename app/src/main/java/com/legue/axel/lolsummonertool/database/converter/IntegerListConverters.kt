package com.legue.axel.lolsummonertool.database.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class IntegerListConverters {

    @TypeConverter
    fun integerToList(value: String?): List<Int>? {
        if (value == null || value == "null") {
            return emptyList()
        }

        val listType = object : TypeToken<List<Int>>() {}.type
        val objects = Gson().fromJson(value, listType) as List<Int>
        return objects.toList()
    }

    @TypeConverter
    fun ListToString(descriptions: List<Int>): String {
        return Gson().toJson(descriptions)
    }
}
