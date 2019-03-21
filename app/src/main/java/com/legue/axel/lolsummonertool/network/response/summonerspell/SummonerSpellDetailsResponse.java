package com.legue.axel.lolsummonertool.network.response.summonerspell;

import com.legue.axel.lolsummonertool.database.model.summonerspell.SummonerSpellImage;

import java.util.List;

public class SummonerSpellDetailsResponse {
    public String id;
    public int key;
    public String name;
    public String description;
    public String tooltip;
    public int maxrank;
    public List<Integer> cooldown;
    public String cooldownBurn;
    public List<Integer> cost;
    public String costBurn;
    public List<Float> effectBurn;
    public int summonerLevel;
    public List<String> modes;
    public String costType;
    public String maxammo;
    public List<Integer> range;
    public String rangeBurn;
    public SummonerSpellImage image;
    public String resource;

    public SummonerSpellDetailsResponse() {
    }

    public SummonerSpellDetailsResponse(String id, int key, String name, String description, String tooltip, int maxrank, List<Integer> cooldown, String cooldownBurn, List<Integer> cost, String costBurn, List<Float> effectBurn, int summonerLevel, List<String> modes, String costType, String maxammo, List<Integer> range, String rangeBurn, SummonerSpellImage image, String resource) {
        this.id = id;
        this.key = key;
        this.name = name;
        this.description = description;
        this.tooltip = tooltip;
        this.maxrank = maxrank;
        this.cooldown = cooldown;
        this.cooldownBurn = cooldownBurn;
        this.cost = cost;
        this.costBurn = costBurn;
        this.effectBurn = effectBurn;
        this.summonerLevel = summonerLevel;
        this.modes = modes;
        this.costType = costType;
        this.maxammo = maxammo;
        this.range = range;
        this.rangeBurn = rangeBurn;
        this.image = image;
        this.resource = resource;
    }
}
