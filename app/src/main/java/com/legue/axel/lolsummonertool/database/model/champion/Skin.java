package com.legue.axel.lolsummonertool.database.model.champion;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "skins")
public class Skin {
    @PrimaryKey
    @NonNull
    private String id;
    private int num;
    private String name;
    private boolean chromas;

    @Ignore
    public Skin() {
    }

    public Skin(String id, int num, String name, boolean chromas) {
        this.id = id;
        this.num = num;
        this.name = name;
        this.chromas = chromas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChromas() {
        return chromas;
    }

    public void setChromas(boolean chromas) {
        this.chromas = chromas;
    }
}
