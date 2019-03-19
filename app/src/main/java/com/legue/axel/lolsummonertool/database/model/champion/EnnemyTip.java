package com.legue.axel.lolsummonertool.database.model.champion;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "ennemy_tip")
public class EnnemyTip {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String ennemytip;
    //TODO : transform with TypeConverters

    @Ignore
    public EnnemyTip() {
    }

    public EnnemyTip(int id, String ennemytip) {
        this.id = id;
        this.ennemytip = ennemytip;
    }


}
