package com.legue.axel.lolsummonertool.database.model.item;

import com.google.gson.annotations.SerializedName;

public class ItemGroup {
    public String id;
    @SerializedName("MaxGroupOwnable")
    public String maxGroupOwnable;

    public ItemGroup() {
    }

    public ItemGroup(String id, String maxGroupOwnable) {
        this.id = id;
        this.maxGroupOwnable = maxGroupOwnable;
    }

}
