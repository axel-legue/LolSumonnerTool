package com.legue.axel.lolsummonertool.database.model.item;

import com.google.gson.annotations.SerializedName;

public class ItemMap {
    @SerializedName("1")
    public boolean one;
    @SerializedName("8")
    public boolean height;
    @SerializedName("10")
    public boolean ten;
    @SerializedName("12")
    public boolean twelve;

    public ItemMap() {
    }

    public ItemMap(boolean one, boolean height, boolean ten, boolean twelve) {
        this.one = one;
        this.height = height;
        this.ten = ten;
        this.twelve = twelve;
    }

}
