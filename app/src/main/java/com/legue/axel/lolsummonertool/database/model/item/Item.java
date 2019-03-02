package com.legue.axel.lolsummonertool.database.model.item;

import com.google.gson.annotations.SerializedName;
import com.legue.axel.lolsummonertool.database.model.Gold;
import com.legue.axel.lolsummonertool.database.model.RiotImage;

import java.util.List;

public class Item {

    //TODO : http://ddragon.leagueoflegends.com/cdn/6.24.1/data/en_US/item.json
    //Field maps for what purpose ?
    private String name;
    private String description;
    private String colloq;
    private String plaintext;
    private int depth;
    private List<String> into;
    private RiotImage image;
    private Gold gold;
    private List<String> tags;
    @SerializedName("from")
    private List<String> fromIds;
    @SerializedName("into")
    private List<String> intoIds;

    public Item() {
    }

    public Item(String name, String description, String colloq, String plaintext, int depth, List<String> into, RiotImage image, Gold gold, List<String> tags, List<String> fromIds, List<String> intoIds) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColloq() {
        return colloq;
    }

    public void setColloq(String colloq) {
        this.colloq = colloq;
    }

    public String getPlaintext() {
        return plaintext;
    }

    public void setPlaintext(String plaintext) {
        this.plaintext = plaintext;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public List<String> getInto() {
        return into;
    }

    public void setInto(List<String> into) {
        this.into = into;
    }

    public RiotImage getImage() {
        return image;
    }

    public void setImage(RiotImage image) {
        this.image = image;
    }

    public Gold getGold() {
        return gold;
    }

    public void setGold(Gold gold) {
        this.gold = gold;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getFromIds() {
        return fromIds;
    }

    public void setFromIds(List<String> fromIds) {
        this.fromIds = fromIds;
    }

    public List<String> getIntoIds() {
        return intoIds;
    }

    public void setIntoIds(List<String> intoIds) {
        this.intoIds = intoIds;
    }
}
