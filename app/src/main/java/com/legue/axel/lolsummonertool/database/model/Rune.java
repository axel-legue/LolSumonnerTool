package com.legue.axel.lolsummonertool.database.model;

import com.google.gson.annotations.SerializedName;

public class Rune {

    @SerializedName("isrune")
    private boolean isRune;
    private int tier;
    private String type;

    public Rune() {
    }

    public Rune(boolean isRune, int tier, String type) {
        this.isRune = isRune;
        this.tier = tier;
        this.type = type;
    }

    public boolean isRune() {
        return isRune;
    }

    public void setRune(boolean rune) {
        isRune = rune;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
