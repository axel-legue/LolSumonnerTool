package com.legue.axel.lolsummonertool.database.model;

import com.google.gson.annotations.SerializedName;

public class Rune {

    @SerializedName("isrune")
    public boolean isRune;
    public int tier;
    public String type;

    public Rune() {
    }

    public Rune(boolean isRune, int tier, String type) {
        this.isRune = isRune;
        this.tier = tier;
        this.type = type;
    }

}
