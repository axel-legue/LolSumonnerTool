package com.legue.axel.lolsummonertool.database.model.champion;

import com.google.gson.annotations.SerializedName;

public class ChampionStats {
    private float hp;
    @SerializedName("hpperlevel")
    private float hpPerLevel;
    private float mp;
    @SerializedName("mpperlevel")
    private float mpPerLevel;
    @SerializedName("movespeed")
    private float moveSpeed;
    private float armor;
    @SerializedName("armorperlevel")
    private float armorPerLevel;
    @SerializedName("spellblock")
    private float spellBlock;
    @SerializedName("spellblockperlevel")
    private float spellBlockPerLevel;
    @SerializedName("attackrange")
    private float attackRange;
    @SerializedName("hpregen")
    private float hpRegen;
    @SerializedName("hpregenperlevel")
    private float HpRegenPerLevel;
    @SerializedName("mpregen")
    private float mpRegen;
    @SerializedName("mpregenperlevel")
    private float mpRegenPerLevel;
    @SerializedName("crit")
    private float crit;
    @SerializedName("critperlevel")
    private float critPerLevel;
    @SerializedName("attackdamage")
    private float attackDamage;
    @SerializedName("attackdamageperlevel")
    private float attackDamagePerLevel;
    @SerializedName("attackspeedoffset")
    private float attackSpeedOffset;
    @SerializedName("attackspeedperlevel")
    private float attackSpeedPerLevel;

    public ChampionStats() {
    }

    public ChampionStats(float hp, float hpPerLevel, float mp, float mpPerLevel, float moveSpeed,
                         float armor, float armorPerLevel, float spellBlock,
                         float spellBlockPerLevel, float attackRange, float hpRegen,
                         float hpRegenPerLevel, float mpRegen, float mpRegenPerLevel, float crit,
                         float critPerLevel, float attackDamage, float attackDamagePerLevel,
                         float attackSpeedOffset, float attackSpeedPerLevel) {
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
        HpRegenPerLevel = hpRegenPerLevel;
        this.mpRegen = mpRegen;
        this.mpRegenPerLevel = mpRegenPerLevel;
        this.crit = crit;
        this.critPerLevel = critPerLevel;
        this.attackDamage = attackDamage;
        this.attackDamagePerLevel = attackDamagePerLevel;
        this.attackSpeedOffset = attackSpeedOffset;
        this.attackSpeedPerLevel = attackSpeedPerLevel;
    }


    public float getHp() {
        return hp;
    }

    public void setHp(float hp) {
        this.hp = hp;
    }

    public float getHpPerLevel() {
        return hpPerLevel;
    }

    public void setHpPerLevel(float hpPerLevel) {
        this.hpPerLevel = hpPerLevel;
    }

    public float getMp() {
        return mp;
    }

    public void setMp(float mp) {
        this.mp = mp;
    }

    public float getMpPerLevel() {
        return mpPerLevel;
    }

    public void setMpPerLevel(float mpPerLevel) {
        this.mpPerLevel = mpPerLevel;
    }

    public float getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(float moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public float getArmor() {
        return armor;
    }

    public void setArmor(float armor) {
        this.armor = armor;
    }

    public float getArmorPerLevel() {
        return armorPerLevel;
    }

    public void setArmorPerLevel(float armorPerLevel) {
        this.armorPerLevel = armorPerLevel;
    }

    public float getSpellBlock() {
        return spellBlock;
    }

    public void setSpellBlock(float spellBlock) {
        this.spellBlock = spellBlock;
    }

    public float getSpellBlockPerLevel() {
        return spellBlockPerLevel;
    }

    public void setSpellBlockPerLevel(float spellBlockPerLevel) {
        this.spellBlockPerLevel = spellBlockPerLevel;
    }

    public float getAttackRange() {
        return attackRange;
    }

    public void setAttackRange(float attackRange) {
        this.attackRange = attackRange;
    }

    public float getHpRegen() {
        return hpRegen;
    }

    public void setHpRegen(float hpRegen) {
        this.hpRegen = hpRegen;
    }

    public float getHpRegenPerLevel() {
        return HpRegenPerLevel;
    }

    public void setHpRegenPerLevel(float hpRegenPerLevel) {
        HpRegenPerLevel = hpRegenPerLevel;
    }

    public float getMpRegen() {
        return mpRegen;
    }

    public void setMpRegen(float mpRegen) {
        this.mpRegen = mpRegen;
    }

    public float getMpRegenPerLevel() {
        return mpRegenPerLevel;
    }

    public void setMpRegenPerLevel(float mpRegenPerLevel) {
        this.mpRegenPerLevel = mpRegenPerLevel;
    }

    public float getCrit() {
        return crit;
    }

    public void setCrit(float crit) {
        this.crit = crit;
    }

    public float getCritPerLevel() {
        return critPerLevel;
    }

    public void setCritPerLevel(float critPerLevel) {
        this.critPerLevel = critPerLevel;
    }

    public float getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(float attackDamage) {
        this.attackDamage = attackDamage;
    }

    public float getAttackDamagePerLevel() {
        return attackDamagePerLevel;
    }

    public void setAttackDamagePerLevel(float attackDamagePerLevel) {
        this.attackDamagePerLevel = attackDamagePerLevel;
    }

    public float getAttackSpeedOffset() {
        return attackSpeedOffset;
    }

    public void setAttackSpeedOffset(float attackSpeedOffset) {
        this.attackSpeedOffset = attackSpeedOffset;
    }

    public float getAttackSpeedPerLevel() {
        return attackSpeedPerLevel;
    }

    public void setAttackSpeedPerLevel(float attackSpeedPerLevel) {
        this.attackSpeedPerLevel = attackSpeedPerLevel;
    }
}
