package com.legue.axel.lolsummonertool.database.converter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class StringListConverters {

    @TypeConverter
    public static List<String> stringToList(String value) {
        Gson gson = new Gson();

        if (value == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<String>>() {
        }.getType();

        return gson.fromJson(value, listType);
    }

    @TypeConverter
    public static String ListToString(List<String> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }
}
