package com.legue.axel.lolsummonertool.database.model.champion;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.legue.axel.lolsummonertool.database.converter.FloatListConverters;

import java.util.List;

import static androidx.room.ForeignKey.CASCADE;

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

    //TODO add relation beetween Spell and /Effect/EffectBurn
    //TODO : handle effect and effectBurns values ....
    @PrimaryKey()
    @NonNull
    public String id;
    public String name;
    public String description;
    @SerializedName("tooltip")
    public String toolTip;
    @SerializedName("maxrank")
    public int maxRank;
    @TypeConverters(FloatListConverters.class)
    public List<Float> cooldown;
    public String cooldownBurn;
    @TypeConverters(FloatListConverters.class)
    public List<Float> cost;
    public String costBurn;
    public String costType;
    public String maxammo;
    @TypeConverters(FloatListConverters.class)
    public List<Float> range;
    public String rangeBurn;
    public String resource;
    public int championId;

    @Ignore
    public Spell() {
    }


    public Spell(String id, String name, String description, String toolTip,
                 int maxRank, List<Float> cooldown, String cooldownBurn, List<Float> cost,
                 String costBurn, String costType, String maxammo, List<Float> range,
                 String rangeBurn, String resource, int championId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.toolTip = toolTip;
        this.maxRank = maxRank;
        this.cooldown = cooldown;
        this.cooldownBurn = cooldownBurn;
        this.cost = cost;
        this.costBurn = costBurn;
        this.costType = costType;
        this.maxammo = maxammo;
        this.range = range;
        this.rangeBurn = rangeBurn;
        this.resource = resource;
        this.championId = championId;
    }
}
