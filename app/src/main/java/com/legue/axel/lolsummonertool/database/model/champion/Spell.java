package com.legue.axel.lolsummonertool.database.model.champion;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.google.gson.annotations.SerializedName;
import com.legue.axel.lolsummonertool.database.converter.FloatListConverters;
import com.legue.axel.lolsummonertool.database.converter.IntegerListConverters;

import java.util.List;

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

    //TODO add relation beetween Spell and /Effect/EffectBurn
    //TODO : handle effect and effectBurns values ....
    @PrimaryKey(autoGenerate = true)
    public int key;
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
    @TypeConverters(IntegerListConverters.class)
    public List<Integer> cost;
    public String costBurn;
    public String costType;
    public String maxammo;
    @TypeConverters(IntegerListConverters.class)
    public List<Integer> range;
    public String rangeBurn;
    public String resource;
    public int championId;

    @Ignore
    public Spell() {
    }


    public Spell(int key, String id, String name, String description, String toolTip,
                 int maxRank, List<Float> cooldown, String cooldownBurn, List<Integer> cost,
                 String costBurn, String costType, String maxammo, List<Integer> range,
                 String rangeBurn, String resource, int championId) {
        this.key = key;
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
