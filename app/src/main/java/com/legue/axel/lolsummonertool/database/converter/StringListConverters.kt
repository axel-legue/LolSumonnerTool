package com.legue.axel.lolsummonertool.database.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class StringListConverters {

    @TypeConverter
    fun stringToList(value: String): List<String>? {
        if(value == "null"){
            return emptyList()
        }
        val listType = object : TypeToken<List<String>>() {}.type
        val objects = Gson().fromJson(value, listType) as List<String>

        return objects.toList()
    }

    @TypeConverter
    fun ListToString(value: List<String>?): String {
        return Gson().toJson(value)
    }
}
