package com.legue.axel.lolsummonertool.database.model.champion;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(
        tableName = "spells",
        foreignKeys = @ForeignKey(
                entity = Champion.class,
                parentColumns = "key",
                childColumns = "championId",
                onDelete = CASCADE),
        indices = @Index("championId")
)
public class Spell {

    //TODO add relation beetween Spell and LevelTip/Cooldown/Cost/Effect/EffectBurn/Vars/Range/ChampionImage
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    public int key;
    public String id;
    public String name;
    public String description;
    @SerializedName("tooltip")
    public String toolTip;
    @SerializedName("maxrank")
    public int maxRank;
    public String cooldownBurn;
    public String costBurn;
    public String costType;
    public String maxammo;
    public String rangeBurn;
    public String resource;
    public int championId;

    @Ignore
    public Spell() {
    }

    public Spell(int key, String id, String name, String description, String toolTip, int maxRank,
                 String cooldownBurn, String costBurn, String costType, String maxammo,
                 String rangeBurn, String resource, int championId) {
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
        this.championId = championId;
    }
}
