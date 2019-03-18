package com.legue.axel.lolsummonertool.network;

import com.google.gson.annotations.SerializedName;
import com.legue.axel.lolsummonertool.database.model.item.ItemGold;
import com.legue.axel.lolsummonertool.database.model.item.ItemImage;

import java.util.LinkedHashMap;
import java.util.List;

public class ItemDetailResponse {
    public String name;
    public String description;
    public String colloq;
    public String plaintext;
    public List<String> from;
    public Boolean hideFromAll;
    public List<String> into;
    @SerializedName("image")
    public ItemImage itemImage;
    public ItemGold gold;
    public List<String> tags;
    public LinkedHashMap<String, Boolean> maps;
    public LinkedHashMap<String, Float> stats;
    public LinkedHashMap<String, String> effect;
    public int depth;

    public ItemDetailResponse() {
    }

    public ItemDetailResponse(String name, String description, String colloq, String plaintext, List<String> from, Boolean hideFromAll, List<String> into, ItemImage itemImage, ItemGold gold, List<String> tags, LinkedHashMap<String, Boolean> maps, LinkedHashMap<String, Float> stats, LinkedHashMap<String, String> effect, int depth) {
        this.name = name;
        this.description = description;
        this.colloq = colloq;
        this.plaintext = plaintext;
        this.from = from;
        this.hideFromAll = hideFromAll;
        this.into = into;
        this.itemImage = itemImage;
        this.gold = gold;
        this.tags = tags;
        this.maps = maps;
        this.stats = stats;
        this.effect = effect;
        this.depth = depth;
    }
}
