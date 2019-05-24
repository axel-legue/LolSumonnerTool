package com.legue.axel.lolsummonertool.database.model.summonerspell;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import androidx.annotation.NonNull;

import com.legue.axel.lolsummonertool.database.converter.FloatListConverters;
import com.legue.axel.lolsummonertool.database.converter.IntegerListConverters;
import com.legue.axel.lolsummonertool.database.converter.StringListConverters;

import java.util.List;

@Entity(tableName = "summoner_spells")
public class SummonerSpell {

    //TODO add relation between SummonerSpell and SummonerSpellVars one to many
    //TODO add relation between SummonerSpell and SummonerSpellImage
    public String id;
    @PrimaryKey
    public int key;
    public String name;
    public String description;
    public String tooltip;
    public int maxrank;
    public String cooldownBurn;
    public String costBurn;
    public int summonerLevel;
    public String costType;
    public String maxammo;
    public String rangeBurn;
    public String resource;
    @TypeConverters(IntegerListConverters.class)
    public List<Integer> cost;
    @TypeConverters(FloatListConverters.class)
    public List<Float> effectBurn;
    @TypeConverters(IntegerListConverters.class)
    public List<Integer> cooldown;
    @TypeConverters(IntegerListConverters.class)
    public List<Integer> range;
    @TypeConverters(StringListConverters.class)
    public List<String> modes;

    @Ignore
    public SummonerSpell() {
    }

    public SummonerSpell(String id, int key, String name, String description, String tooltip,
                         int maxrank, String cooldownBurn, String costBurn, int summonerLevel,
                         String costType, String maxammo, String rangeBurn, String resource,
                         List<Integer> cost, List<Float> effectBurn,
                         List<Integer> cooldown, List<Integer> range, List<String> modes) {
        this.id = id;
        this.key = key;
        this.name = name;
        this.description = description;
        this.tooltip = tooltip;
        this.maxrank = maxrank;
        this.cooldownBurn = cooldownBurn;
        this.costBurn = costBurn;
        this.summonerLevel = summonerLevel;
        this.costType = costType;
        this.maxammo = maxammo;
        this.rangeBurn = rangeBurn;
        this.resource = resource;
        this.cost = cost;
        this.effectBurn = effectBurn;
        this.cooldown = cooldown;
        this.range = range;
        this.modes = modes;
    }
}
