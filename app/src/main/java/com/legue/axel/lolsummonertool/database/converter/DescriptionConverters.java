package com.legue.axel.lolsummonertool.database.converter;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class DescriptionConverters {

    @TypeConverter
    public static List<String> stringToDescriptionList(String description) {
        Gson gson = new Gson();

        if (description == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<String>>() {
        }.getType();

        return gson.fromJson(description, listType);
    }

    @TypeConverter
    public static String descriptionListToString(List<String> descriptions) {
        Gson gson = new Gson();
        return gson.toJson(descriptions);
    }
}
