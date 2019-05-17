package com.legue.axel.lolsummonertool.database.model.champion;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

import static androidx.room.ForeignKey.CASCADE;

@Entity(
        tableName = "skins",
        foreignKeys = @ForeignKey(
                entity = Champion.class,
                parentColumns = "key",
                childColumns = "championId",
                onDelete = CASCADE),
        indices = @Index("championId")
)
public class Skin {
    @PrimaryKey
    @NonNull
    public int id;
    public int num;
    public String name;
    public boolean chromas;
    public int championId;

    @Ignore
    public Skin() {
    }

    public Skin(int id, int num, String name, boolean chromas, int championId) {
        this.id = id;
        this.num = num;
        this.name = name;
        this.chromas = chromas;
        this.championId = championId;
    }
}
