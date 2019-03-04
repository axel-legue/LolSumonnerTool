package com.legue.axel.lolsummonertool.database.model.champion;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "cooldown")
public class Cooldown {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int duration;

    @Ignore
    public Cooldown() {
    }

    public Cooldown(int id, int duration) {
        this.id = id;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
