package com.legue.axel.lolsummonertool.database.model.champion;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "cooldown")
public class Cooldown {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int duration;

    @Ignore
    public Cooldown() {
    }

    public Cooldown(int id, int duration) {
        this.id = id;
        this.duration = duration;
    }
}
