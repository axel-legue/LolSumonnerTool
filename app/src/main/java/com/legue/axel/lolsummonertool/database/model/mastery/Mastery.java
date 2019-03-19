package com.legue.axel.lolsummonertool.database.model.mastery;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.legue.axel.lolsummonertool.database.converter.DescriptionConverters;

import java.util.List;

@Entity(tableName = "masteries")
public class Mastery {

    @PrimaryKey
    public int id;
    public String name;
    @TypeConverters(DescriptionConverters.class)
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
