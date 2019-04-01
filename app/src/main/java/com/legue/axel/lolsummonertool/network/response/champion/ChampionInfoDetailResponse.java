package com.legue.axel.lolsummonertool.network.response.champion;

import com.legue.axel.lolsummonertool.database.model.champion.ChampionImage;
import com.legue.axel.lolsummonertool.database.model.champion.ChampionInfo;
import com.legue.axel.lolsummonertool.database.model.champion.ChampionStats;
import com.legue.axel.lolsummonertool.database.model.champion.Passive;
import com.legue.axel.lolsummonertool.database.model.champion.Spell;

import java.util.List;

public class ChampionInfoDetailResponse {

    private String key;
    private String id;
    private String name;
    private String title;
    private String lore;
    private String blurb;
    private String partype;
    private ChampionInfo info;
    private ChampionImage image;
    private List<String> tags;
    private ChampionStats stats;
    private Passive passive;
    private List<Spell> spells;

    public ChampionInfoDetailResponse() {
    }

    public ChampionInfoDetailResponse(String key, String id, String name, String title, String lore, String blurb, String partype, ChampionInfo info, ChampionImage image, List<String> tags, ChampionStats stats, Passive passive, List<Spell> spells) {
        this.key = key;
        this.id = id;
        this.name = name;
        this.title = title;
        this.lore = lore;
        this.blurb = blurb;
        this.partype = partype;
        this.info = info;
        this.image = image;
        this.tags = tags;
        this.stats = stats;
        this.passive = passive;
        this.spells = spells;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLore() {
        return lore;
    }

    public void setLore(String lore) {
        this.lore = lore;
    }

    public String getBlurb() {
        return blurb;
    }

    public void setBlurb(String blurb) {
        this.blurb = blurb;
    }

    public String getPartype() {
        return partype;
    }

    public void setPartype(String partype) {
        this.partype = partype;
    }

    public ChampionInfo getInfo() {
        return info;
    }

    public void setInfo(ChampionInfo info) {
        this.info = info;
    }

    public ChampionImage getImage() {
        return image;
    }

    public void setImage(ChampionImage image) {
        this.image = image;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public ChampionStats getStats() {
        return stats;
    }

    public void setStats(ChampionStats stats) {
        this.stats = stats;
    }

    public Passive getPassive() {
        return passive;
    }

    public void setPassive(Passive passive) {
        this.passive = passive;
    }

    public List<Spell> getSpells() {
        return spells;
    }

    public void setSpells(List<Spell> spells) {
        this.spells = spells;
    }
}
