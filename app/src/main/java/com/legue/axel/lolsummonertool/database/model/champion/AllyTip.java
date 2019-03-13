package com.legue.axel.lolsummonertool.database.model.champion;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "allytips")
public class AllyTip {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @SerializedName("allytip")
    @ColumnInfo(name = "allyTip")
    public String allyTip;

    @Ignore
    public AllyTip() {
    }

    public AllyTip(int id, String allyTip) {
        this.id = id;
        this.allyTip = allyTip;
    }
    
}
