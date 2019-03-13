package com.legue.axel.lolsummonertool.database.model.champion;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "champion_infos",
        foreignKeys = @ForeignKey(
                entity = Champion.class,
                parentColumns = "key",
                childColumns = "championId"
        ))
public class ChampionInfo {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int attack;
    public int defense;
    public int magic;
    public int difficulty;
    @ColumnInfo(index = true)
    public int championId;

    @Ignore
    public ChampionInfo() {
    }

    public ChampionInfo(int id, int attack, int defense, int magic, int difficulty, int championId) {
        this.id = id;
        this.attack = attack;
        this.defense = defense;
        this.magic = magic;
        this.difficulty = difficulty;
        this.championId = championId;
    }
}
