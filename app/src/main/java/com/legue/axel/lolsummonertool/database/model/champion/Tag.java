package com.legue.axel.lolsummonertool.database.model.champion;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(
        tableName = "tags",
        foreignKeys = @ForeignKey(
                entity = Champion.class,
                parentColumns = "key",
                childColumns = "championId",
                onDelete = CASCADE),
        indices = @Index("championId")
)
public class Tag {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String tag;
    public int championId;

    @Ignore
    public Tag() {
    }

    public Tag(int id, String tag, int championId) {
        this.id = id;
        this.tag = tag;
        this.championId = championId;
    }
}
