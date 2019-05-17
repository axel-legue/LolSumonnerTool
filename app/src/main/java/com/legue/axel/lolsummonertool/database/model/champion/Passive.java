package com.legue.axel.lolsummonertool.database.model.champion;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "passives",
        foreignKeys = @ForeignKey(
                entity = Champion.class,
                parentColumns = "key",
                childColumns = "championId",
                onDelete = CASCADE
        ))
public class Passive {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public String description;
    public String image;
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
