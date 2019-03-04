package com.legue.axel.lolsummonertool.database.model.champion;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "ennemy_tip")
public class EnnemyTip {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String ennemytip;

    @Ignore
    public EnnemyTip() {
    }

    public EnnemyTip(int id, String ennemytip) {
        this.id = id;
        this.ennemytip = ennemytip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnnemytip() {
        return ennemytip;
    }

    public void setEnnemytip(String ennemytip) {
        this.ennemytip = ennemytip;
    }

}
