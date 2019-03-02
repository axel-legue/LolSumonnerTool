package com.legue.axel.lolsummonertool.network;

import com.google.gson.annotations.SerializedName;
import com.legue.axel.lolsummonertool.database.model.Basic;
import com.legue.axel.lolsummonertool.database.model.item.ItemGroup;
import com.legue.axel.lolsummonertool.database.model.item.ItemTree;

import java.util.List;

public class ItemsResponse {

    private String type;
    private String version;
    private Basic basic;
    private Object data;
    @SerializedName("groups")
    private List<ItemGroup> groupsList;
    @SerializedName("tree")
    private List<ItemTree> itemTreeList;

    public ItemsResponse() {
    }

    public ItemsResponse(String type, String version, Basic basic, Object data, List<ItemGroup> groupsList, List<ItemTree> itemTreeList) {
        this.type = type;
        this.version = version;
        this.basic = basic;
        this.data = data;
        this.groupsList = groupsList;
        this.itemTreeList = itemTreeList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Basic getBasic() {
        return basic;
    }

    public void setBasic(Basic basic) {
        this.basic = basic;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public List<ItemGroup> getGroupsList() {
        return groupsList;
    }

    public void setGroupsList(List<ItemGroup> groupsList) {
        this.groupsList = groupsList;
    }

    public List<ItemTree> getItemTreeList() {
        return itemTreeList;
    }

    public void setItemTreeList(List<ItemTree> itemTreeList) {
        this.itemTreeList = itemTreeList;
    }
}
