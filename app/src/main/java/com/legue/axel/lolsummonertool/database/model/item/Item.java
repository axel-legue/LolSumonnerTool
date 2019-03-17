package com.legue.axel.lolsummonertool.database.model.item;

import com.google.gson.annotations.SerializedName;
import com.legue.axel.lolsummonertool.database.model.champion.ChampionImage;
import com.legue.axel.lolsummonertool.database.model.Gold;

import java.util.List;

public class Item {

    //TODO : http://ddragon.leagueoflegends.com/cdn/6.24.1/data/en_US/item.json
    //Field maps for what purpose ?
    public String name;
    public String description;
    public String colloq;
    public String plaintext;
    public int depth;
    public List<String> into;
    public ChampionImage image;
    public Gold gold;
    public List<String> tags;
    @SerializedName("from")
    public List<String> fromIds;
    @SerializedName("into")
    public List<String> intoIds;

    public Item() {
    }

    public Item(String name, String description, String colloq, String plaintext, int depth, List<String> into, ChampionImage image, Gold gold, List<String> tags, List<String> fromIds, List<String> intoIds) {
        this.name = name;
        this.description = description;
        this.colloq = colloq;
        this.plaintext = plaintext;
        this.depth = depth;
        this.into = into;
        this.image = image;
        this.gold = gold;
        this.tags = tags;
        this.fromIds = fromIds;
        this.intoIds = intoIds;
    }

}
