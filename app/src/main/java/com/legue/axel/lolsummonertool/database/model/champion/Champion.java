package com.legue.axel.lolsummonertool.database.model.champion;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.legue.axel.lolsummonertool.database.converter.StringListConverters;

import java.util.List;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "champions")
public class Champion {
    //TODO :Add recommended Object and relation if enough time.

    @PrimaryKey()
    @NonNull
    public int key;
    public String id;
    public String name;
    public String title;
    public String lore;
    public String blurb;
    @TypeConverters(StringListConverters.class)
    public List<String> tags;
    @TypeConverters(StringListConverters.class)
    public List<String> enemytips;
    @TypeConverters(StringListConverters.class)
    public List<String> allytips;
    public String partype;

    @Ignore
    public Champion() {
    }

    public Champion(int key, String id, String name, String title, String lore, String blurb, List<String> tags, List<String> enemytips, List<String> allytips, String partype) {
        this.key = key;
        this.id = id;
        this.name = name;
        this.title = title;
        this.lore = lore;
        this.blurb = blurb;
        this.tags = tags;
        this.enemytips = enemytips;
        this.allytips = allytips;
        this.partype = partype;
    }
}
