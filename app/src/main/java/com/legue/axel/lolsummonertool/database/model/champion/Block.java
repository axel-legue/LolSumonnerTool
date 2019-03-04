package com.legue.axel.lolsummonertool.database.model.champion;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "blocks")
public class Block {
    // TODO add Relation between item and block
    // List<Item> itemList; // (item should contain [id(string) / count(int) / hideCount(bool)]

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String type;
    private boolean recMath;
    private boolean recSteps;
    private int minSummonerLevel;
    private int maxSummonerLevel;
    private String showIfSummonerSpell;
    private String hideIfSummonerSpell;

    @Ignore
    public Block() {
    }

    public Block(int id, String type, boolean recMath, boolean recSteps, int minSummonerLevel, int maxSummonerLevel, String showIfSummonerSpell, String hideIfSummonerSpell) {
        this.id = id;
        this.type = type;
        this.recMath = recMath;
        this.recSteps = recSteps;
        this.minSummonerLevel = minSummonerLevel;
        this.maxSummonerLevel = maxSummonerLevel;
        this.showIfSummonerSpell = showIfSummonerSpell;
        this.hideIfSummonerSpell = hideIfSummonerSpell;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isRecMath() {
        return recMath;
    }

    public void setRecMath(boolean recMath) {
        this.recMath = recMath;
    }

    public boolean isRecSteps() {
        return recSteps;
    }

    public void setRecSteps(boolean recSteps) {
        this.recSteps = recSteps;
    }

    public int getMinSummonerLevel() {
        return minSummonerLevel;
    }

    public void setMinSummonerLevel(int minSummonerLevel) {
        this.minSummonerLevel = minSummonerLevel;
    }

    public int getMaxSummonerLevel() {
        return maxSummonerLevel;
    }

    public void setMaxSummonerLevel(int maxSummonerLevel) {
        this.maxSummonerLevel = maxSummonerLevel;
    }

    public String getShowIfSummonerSpell() {
        return showIfSummonerSpell;
    }

    public void setShowIfSummonerSpell(String showIfSummonerSpell) {
        this.showIfSummonerSpell = showIfSummonerSpell;
    }

    public String getHideIfSummonerSpell() {
        return hideIfSummonerSpell;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHideIfSummonerSpell(String hideIfSummonerSpell) {
        this.hideIfSummonerSpell = hideIfSummonerSpell;
    }
}
