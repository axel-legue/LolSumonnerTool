package com.legue.axel.lolsummonertool.database.model.mastery;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.legue.axel.lolsummonertool.database.converter.StringListConverters;

import java.util.List;

@Entity(tableName = "masteries")
public class Mastery {

    @PrimaryKey
    public int id;
    public String name;
    @TypeConverters(StringListConverters.class)
    public List<String> description;
    public int ranks;
    public String prereq;

    @Ignore
    public Mastery() {
    }

    public Mastery(int id, String name, List<String> description, int ranks, String prereq) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ranks = ranks;
        this.prereq = prereq;
    }
}
