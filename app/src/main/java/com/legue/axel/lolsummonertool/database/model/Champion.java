package com.legue.axel.lolsummonertool.database.model;

import java.util.List;

public class Champion {
    private String version;
    private String id;
    private String key;
    private String name;
    private String title;
    private String blurb;   // Texte de présentation
    private ChampionInfo championInfo;
    private ChampionImage championImage;
    private List<String> tags;
    private String partype;
    private ChampionStats championStats;

    public Champion(String version, String id, String key, String name, String title, String blurb, ChampionInfo championInfo, ChampionImage championImage, List<String> tags, String partype, ChampionStats championStats) {
        this.version = version;
        this.id = id;
        this.key = key;
        this.name = name;
        this.title = title;
        this.blurb = blurb;
        this.championInfo = championInfo;
        this.championImage = championImage;
        this.tags = tags;
        this.partype = partype;
        this.championStats = championStats;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBlurb() {
        return blurb;
    }

    public void setBlurb(String blurb) {
        this.blurb = blurb;
    }

    public ChampionInfo getChampionInfo() {
        return championInfo;
    }

    public void setChampionInfo(ChampionInfo championInfo) {
        this.championInfo = championInfo;
    }

    public ChampionImage getChampionImage() {
        return championImage;
    }

    public void setChampionImage(ChampionImage championImage) {
        this.championImage = championImage;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getPartype() {
        return partype;
    }

    public void setPartype(String partype) {
        this.partype = partype;
    }

    public ChampionStats getChampionStats() {
        return championStats;
    }

    public void setChampionStats(ChampionStats championStats) {
        this.championStats = championStats;
    }
}
