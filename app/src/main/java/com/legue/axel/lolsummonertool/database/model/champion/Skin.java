package com.legue.axel.lolsummonertool.database.model.champion;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

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
