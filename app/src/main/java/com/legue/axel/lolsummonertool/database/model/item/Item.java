package com.legue.axel.lolsummonertool.database.model.item;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import androidx.annotation.NonNull;

import com.legue.axel.lolsummonertool.database.converter.StringListConverters;

import java.util.List;

@Entity(tableName = "items")
public class Item {

    @PrimaryKey()
    @NonNull
    public int id;
    public String name;
    public String description;
    public String colloq;
    public String plaintext;
    public int depth;
    @TypeConverters(StringListConverters.class)
    public List<String> into;
    @TypeConverters(StringListConverters.class)
    public List<String> from;


    @Ignore
    public Item() {
    }

    public Item(int id, String name, String description, String colloq, String plaintext, int depth, List<String> into, List<String> from) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.colloq = colloq;
        this.plaintext = plaintext;
        this.depth = depth;
        this.into = into;
        this.from = from;
    }
}
