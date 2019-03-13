package com.legue.axel.lolsummonertool.database.model.champion;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "vars")
public class Var {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String link;
    public Float coeff;
    public String key;

    @Ignore
    public Var() {
    }

    public Var(int id, String link, Float coeff, String key) {
        this.id = id;
        this.link = link;
        this.coeff = coeff;
        this.key = key;
    }

}
