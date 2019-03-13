package com.legue.axel.lolsummonertool.database.model.champion;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "champion_stats",
        foreignKeys = @ForeignKey(
                entity = Champion.class,
                parentColumns = "key",
                childColumns = "championId"
        ))
public class ChampionStats {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public float hp;
    @SerializedName("hpperlevel")
    public float hpPerLevel;
    public float mp;
    @SerializedName("mpperlevel")
    public float mpPerLevel;
    @SerializedName("movespeed")
    public float moveSpeed;
    public float armor;
    @SerializedName("armorperlevel")
    public float armorPerLevel;
    @SerializedName("spellblock")
    public float spellBlock;
    @SerializedName("spellblockperlevel")
    public float spellBlockPerLevel;
    @SerializedName("attackrange")
    public float attackRange;
    @SerializedName("hpregen")
    public float hpRegen;
    @SerializedName("hpregenperlevel")
    public float hpRegenPerLevel;
    @SerializedName("mpregen")
    public float mpRegen;
    @SerializedName("mpregenperlevel")
    public float mpRegenPerLevel;
    @SerializedName("crit")
    public float crit;
    @SerializedName("critperlevel")
    public float critPerLevel;
    @SerializedName("attackdamage")
    public float attackDamage;
    @SerializedName("attackdamageperlevel")
    public float attackDamagePerLevel;
    @SerializedName("attackspeedperlevel")
    public float attackSpeedPerLevel;
    @SerializedName("attackspeed")
    public float attackSpeed;
    @ColumnInfo(index = true)
    public int championId;


    @Ignore
    public ChampionStats() {
    }

    public ChampionStats(int id, float hp, float hpPerLevel, float mp, float mpPerLevel,
                         float moveSpeed, float armor, float armorPerLevel, float spellBlock,
                         float spellBlockPerLevel, float attackRange, float hpRegen,
                         float hpRegenPerLevel, float mpRegen, float mpRegenPerLevel, float crit,
                         float critPerLevel, float attackDamage, float attackDamagePerLevel,
                         float attackSpeedPerLevel, float attackSpeed, int championId) {
        this.id = id;
        this.hp = hp;
        this.hpPerLevel = hpPerLevel;
        this.mp = mp;
        this.mpPerLevel = mpPerLevel;
        this.moveSpeed = moveSpeed;
        this.armor = armor;
        this.armorPerLevel = armorPerLevel;
        this.spellBlock = spellBlock;
        this.spellBlockPerLevel = spellBlockPerLevel;
        this.attackRange = attackRange;
        this.hpRegen = hpRegen;
        this.hpRegenPerLevel = hpRegenPerLevel;
        this.mpRegen = mpRegen;
        this.mpRegenPerLevel = mpRegenPerLevel;
        this.crit = crit;
        this.critPerLevel = critPerLevel;
        this.attackDamage = attackDamage;
        this.attackDamagePerLevel = attackDamagePerLevel;
        this.attackSpeedPerLevel = attackSpeedPerLevel;
        this.attackSpeed = attackSpeed;
        this.championId = championId;
    }
}
