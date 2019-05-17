package com.legue.axel.lolsummonertool.database.model.champion;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "champion_infos",
        foreignKeys = @ForeignKey(
                entity = Champion.class,
                parentColumns = "key",
                childColumns = "championId",
                onDelete = CASCADE
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
