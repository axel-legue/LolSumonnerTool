package com.legue.axel.lolsummonertool.database.model.champion;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "recommended")
public class Recommended {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String champion;
    private String title;
    private String map;
    private String mode;
    private String type;
    private String customTag;
    @SerializedName("sortrank")
    private int sortRank;
    private boolean extensionPage;

    // TODO ADD RELATION  BETWEEN Blocks and  Recommended

    @Ignore
    public Recommended() {
    }

    public Recommended(int id, String champion, String title, String map, String mode, String type,
                       String customTag, int sortRank, boolean extensionPage) {
        this.id = id;
        this.champion = champion;
        this.title = title;
        this.map = map;
        this.mode = mode;
        this.type = type;
        this.customTag = customTag;
        this.sortRank = sortRank;
        this.extensionPage = extensionPage;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChampion() {
        return champion;
    }

    public void setChampion(String champion) {
        this.champion = champion;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCustomTag() {
        return customTag;
    }

    public void setCustomTag(String customTag) {
        this.customTag = customTag;
    }

    public int getSortRank() {
        return sortRank;
    }

    public void setSortRank(int sortRank) {
        this.sortRank = sortRank;
    }

    public boolean isExtensionPage() {
        return extensionPage;
    }

    public void setExtensionPage(boolean extensionPage) {
        this.extensionPage = extensionPage;
    }

}
