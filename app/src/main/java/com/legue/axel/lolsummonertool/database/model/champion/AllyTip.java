package com.legue.axel.lolsummonertool.database.model.champion;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "allytips")
public class AllyTip {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @SerializedName("allytip")
    @ColumnInfo(name = "allyTip")
    private String allyTip;

    @Ignore
    public AllyTip() {
    }

    public AllyTip(int id, String allytip) {
        this.id = id;
        this.allyTip = allytip;
    }

    public String getAllytip() {
        return allyTip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAllytip(String allytip) {
        this.allyTip = allytip;
    }
}
