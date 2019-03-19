package com.legue.axel.lolsummonertool.database.model.champion;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "level_tip")
public class LevelTip {
    @PrimaryKey(autoGenerate = true)
    public int id;
    // TODO : add relation between LevelTip and Labels / Effecs


    @Ignore
    public LevelTip() {
    }

    public LevelTip(int id) {
        this.id = id;
    }

}
