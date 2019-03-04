package com.legue.axel.lolsummonertool.database.model.champion;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
import com.legue.axel.lolsummonertool.database.model.RiotImage;

import java.util.List;

@Entity(tableName = "spells")
public class Spell {

    //TODO add relation beetween Spell and LevelTip/Cooldown/Cost/Effect/EffectBurn/Vars/Range/RiotImage
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    private int key;
    @Ignore
    private String id;
    private String name;
    private String description;
    @SerializedName("tooltip")
    private String toolTip;
    @SerializedName("maxrank")
    private int maxRank;
    private String cooldownBurn;
    private String costBurn;
    private String costType;
    private String maxammo;
    private String rangeBurn;
    private String resource;

    @Ignore
    public Spell() {
    }

    public Spell(int key, String id, String name, String description, String toolTip, int maxRank,
                 String cooldownBurn, String costBurn, String costType, String maxammo,
                 String rangeBurn, String resource) {
        this.key = key;
        this.id = id;
        this.name = name;
        this.description = description;
        this.toolTip = toolTip;
        this.maxRank = maxRank;
        this.cooldownBurn = cooldownBurn;
        this.costBurn = costBurn;
        this.costType = costType;
        this.maxammo = maxammo;
        this.rangeBurn = rangeBurn;
        this.resource = resource;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getToolTip() {
        return toolTip;
    }

    public void setToolTip(String toolTip) {
        this.toolTip = toolTip;
    }

    public int getMaxRank() {
        return maxRank;
    }

    public void setMaxRank(int maxRank) {
        this.maxRank = maxRank;
    }

    public String getCooldownBurn() {
        return cooldownBurn;
    }

    public void setCooldownBurn(String cooldownBurn) {
        this.cooldownBurn = cooldownBurn;
    }

    public String getCostBurn() {
        return costBurn;
    }

    public void setCostBurn(String costBurn) {
        this.costBurn = costBurn;
    }

    public String getCostType() {
        return costType;
    }

    public void setCostType(String costType) {
        this.costType = costType;
    }

    public String getMaxammo() {
        return maxammo;
    }

    public void setMaxammo(String maxammo) {
        this.maxammo = maxammo;
    }

    public String getRangeBurn() {
        return rangeBurn;
    }

    public void setRangeBurn(String rangeBurn) {
        this.rangeBurn = rangeBurn;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }
}
