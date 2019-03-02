package com.legue.axel.lolsummonertool.database.model.item;

import com.google.gson.annotations.SerializedName;

public class ItemGroup {
    private String id;
    @SerializedName("MaxGroupOwnable")
    private String maxGroupOwnable;

    public ItemGroup() {
    }

    public ItemGroup(String id, String maxGroupOwnable) {
        this.id = id;
        this.maxGroupOwnable = maxGroupOwnable;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaxGroupOwnable() {
        return maxGroupOwnable;
    }

    public void setMaxGroupOwnable(String maxGroupOwnable) {
        this.maxGroupOwnable = maxGroupOwnable;
    }
}
