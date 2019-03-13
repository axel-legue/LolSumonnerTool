package com.legue.axel.lolsummonertool.database.model.champion;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "passives",
        foreignKeys = @ForeignKey(
                entity = Champion.class,
                parentColumns = "key",
                childColumns = "championId"
        ))
public class Passive {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public String description;
    @ColumnInfo(index = true)
    public int championId;

    @Ignore
    public Passive() {
    }

    public Passive(int id, String name, String description, int championId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.championId = championId;
    }
}
