package com.legue.axel.lolsummonertool.network.response.item;

import com.google.gson.annotations.SerializedName;
import com.legue.axel.lolsummonertool.database.model.item.ItemGroup;
import com.legue.axel.lolsummonertool.database.model.item.ItemTree;

import java.util.LinkedHashMap;
import java.util.List;

public class ItemsResponse {

    public String type;
    public String version;
    //public Basic basic;
    @SerializedName("data")
    public LinkedHashMap<String, ItemDetailResponse> itemList;
    @SerializedName("groups")
    public List<ItemGroup> groupsList;
    @SerializedName("tree")
    public List<ItemTree> itemTreeList;

    public ItemsResponse() {
    }

    public ItemsResponse(String type, String version, LinkedHashMap<String, ItemDetailResponse> itemList, List<ItemGroup> groupsList, List<ItemTree> itemTreeList) {
        this.type = type;
        this.version = version;
        this.itemList = itemList;
        this.groupsList = groupsList;
        this.itemTreeList = itemTreeList;
    }

}
