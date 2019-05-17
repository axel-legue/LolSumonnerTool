package com.legue.axel.lolsummonertool.database.converter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class DoubleListConverters {
    @TypeConverter
    public static List<Double> doubleToList(String value) {
        Gson gson = new Gson();

        if (value == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Float>>() {
        }.getType();

        return gson.fromJson(value, listType);
    }

    @TypeConverter
    public static String ListToString(List<Double> descriptions) {
        Gson gson = new Gson();
        return gson.toJson(descriptions);
    }
}
