package com.legue.axel.lolsummonertool.database.model.item;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

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
    //TODO : Add Type Converters for tranform Into and From array as String separated comma
//    @ColumnInfo(index = true)
//    public int intoId;
//    @ColumnInfo(index = true)
//    public int fromId;

    @Ignore
    public Item() {
    }

    public Item(int id, String name, String description, String colloq, String plaintext,
                int depth) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.colloq = colloq;
        this.plaintext = plaintext;
        this.depth = depth;
    }
}
