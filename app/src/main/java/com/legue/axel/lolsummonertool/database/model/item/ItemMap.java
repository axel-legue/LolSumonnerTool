package com.legue.axel.lolsummonertool.database.model.item;

import com.google.gson.annotations.SerializedName;

public class ItemMap {
    @SerializedName("1")
    private boolean one;
    @SerializedName("8")
    private boolean height;
    @SerializedName("10")
    private boolean ten;
    @SerializedName("12")
    private boolean twelve;

    public ItemMap() {
    }

    public ItemMap(boolean one, boolean height, boolean ten, boolean twelve) {
        this.one = one;
        this.height = height;
        this.ten = ten;
        this.twelve = twelve;
    }

    public boolean isOne() {
        return one;
    }

    public void setOne(boolean one) {
        this.one = one;
    }

    public boolean isHeight() {
        return height;
    }

    public void setHeight(boolean height) {
        this.height = height;
    }

    public boolean isTen() {
        return ten;
    }

    public void setTen(boolean ten) {
        this.ten = ten;
    }

    public boolean isTwelve() {
        return twelve;
    }

    public void setTwelve(boolean twelve) {
        this.twelve = twelve;
    }
}
