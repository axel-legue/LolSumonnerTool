package com.legue.axel.lolsummonertool.database.model.champion;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.legue.axel.lolsummonertool.database.model.champion.Champion;
import com.legue.axel.lolsummonertool.database.model.champion.Passive;

@Entity(tableName = "images",
        foreignKeys = {
                @ForeignKey(
                        entity = Champion.class,
                        parentColumns = "key",
                        childColumns = "championId"
                )}
)
public class ChampionImage {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String full;
    public String sprite;
    public String group;
    public int x;
    public int y;
    public int w;
    public int h;
    @ColumnInfo(index = true)
    public int championId;
//    @ColumnInfo(index = true)
//    public int passiveId;

    @Ignore
    public ChampionImage() {
    }

    public ChampionImage(int id, String full, String sprite, String group, int x, int y, int w, int h, int championId) {
        this.id = id;
        this.full = full;
        this.sprite = sprite;
        this.group = group;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.championId = championId;

    }
}
