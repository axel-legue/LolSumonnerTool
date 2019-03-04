package com.legue.axel.lolsummonertool.database.model.champion;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "tag")
public class Tag {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String tag;

    @Ignore
    public Tag() {
    }

    public Tag(int id, String tag) {
        this.id = id;
        this.tag = tag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
