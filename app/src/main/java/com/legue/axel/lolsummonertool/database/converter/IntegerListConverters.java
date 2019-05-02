package com.legue.axel.lolsummonertool.database.converter;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class IntegerListConverters {

    @TypeConverter
    public static List<Integer> integerToList(String value) {
        Gson gson = new Gson();

        if (value == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Integer>>() {
        }.getType();

        return gson.fromJson(value, listType);
    }

    @TypeConverter
    public static String ListToString(List<Integer> descriptions) {
        Gson gson = new Gson();
        return gson.toJson(descriptions);
    }
}
