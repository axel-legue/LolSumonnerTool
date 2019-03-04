package com.legue.axel.lolsummonertool.database.model.champion;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "vars")
public class Var {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String link;
    private Float coeff;
    private String key;

    public Var() {
    }

    public Var(int id, String link, Float coeff, String key) {
        this.id = id;
        this.link = link;
        this.coeff = coeff;
        this.key = key;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Float getCoeff() {
        return coeff;
    }

    public void setCoeff(Float coeff) {
        this.coeff = coeff;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
