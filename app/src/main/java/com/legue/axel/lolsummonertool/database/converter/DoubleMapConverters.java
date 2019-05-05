package com.legue.axel.lolsummonertool.database.converter;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Map;

public class DoubleMapConverters {

    @TypeConverter
    public static Map<String, Double> fromString(String value) {
        Gson gson = new Gson();

        if (value == null) {
            return Collections.emptyMap();
        }

        Type mapType = new TypeToken<Map<String, Double>>() {
        }.getType();
        return gson.fromJson(value, mapType);
    }

    @TypeConverter
    public static String fromDoubleMap(Map<String, Double> map) {
        Gson gson = new Gson();
        return gson.toJson(map);
    }
}
