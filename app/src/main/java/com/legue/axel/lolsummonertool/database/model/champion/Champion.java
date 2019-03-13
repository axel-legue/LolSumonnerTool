package com.legue.axel.lolsummonertool.database.model.champion;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "champions")
public class Champion {

    @PrimaryKey()
    @NonNull
    public int key;
    public String id;
    public String name;
    public String title;
    public String lore;
    public String blurb;
    public String partype;

    @Ignore
    public Champion() {
    }

    public Champion(String id, int key, String name, String title, String partype, String lore, String blurb) {
        this.id = id;
        this.key = key;
        this.name = name;
        this.title = title;
        this.lore = lore;
        this.blurb = blurb;
        this.partype = partype;
    }
    


}
