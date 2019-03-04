package com.legue.axel.lolsummonertool.database.model.champion;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "champions")
public class Champion {

    @PrimaryKey()
    @NonNull
    private String key;
    @Ignore
    private String id;
    private String name;
    private String title;
    private String lore;
    private String blurb;
    private String partype;

    @Ignore
    public Champion() {
    }

    public Champion(String id, String key, String name, String title, String partype, String lore, String blurb) {
        this.id = id;
        this.key = key;
        this.name = name;
        this.title = title;
        this.lore = lore;
        this.blurb = blurb;
        this.partype = partype;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLore() {
        return lore;
    }

    public void setLore(String lore) {
        this.lore = lore;
    }

    public String getBlurb() {
        return blurb;
    }

    public void setBlurb(String blurb) {
        this.blurb = blurb;
    }

    public String getPartype() {
        return partype;
    }

    public void setPartype(String partype) {
        this.partype = partype;
    }


}
