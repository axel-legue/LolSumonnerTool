package com.legue.axel.lolsummonertool.database.model.champion;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "blocks")
public class Block {
    // TODO add Relation between item and block
    // List<Item> itemList; // (item should contain [id(string) / count(int) / hideCount(bool)]

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String type;
    public boolean recMath;
    public boolean recSteps;
    public int minSummonerLevel;
    public int maxSummonerLevel;
    public String showIfSummonerSpell;
    public String hideIfSummonerSpell;
    public String appendAfterSection;

    @Ignore
    public Block() {
    }

    public Block(int id, String type, boolean recMath, boolean recSteps, int minSummonerLevel, int maxSummonerLevel, String showIfSummonerSpell, String hideIfSummonerSpell, String appendAfterSection) {
        this.id = id;
        this.type = type;
        this.recMath = recMath;
        this.recSteps = recSteps;
        this.minSummonerLevel = minSummonerLevel;
        this.maxSummonerLevel = maxSummonerLevel;
        this.showIfSummonerSpell = showIfSummonerSpell;
        this.hideIfSummonerSpell = hideIfSummonerSpell;
        this.appendAfterSection = appendAfterSection;
    }
}
