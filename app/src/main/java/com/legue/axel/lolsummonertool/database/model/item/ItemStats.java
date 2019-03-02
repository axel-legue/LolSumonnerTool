package com.legue.axel.lolsummonertool.database.model.item;

import com.google.gson.annotations.SerializedName;

public class ItemStats {

    @SerializedName("FlatHPPoolMod")
    private float flatHPPoolMod;
    private float rFlatHPModPerLevel;
    @SerializedName("FlatMPPoolMod")
    private float flatMPPoolMod;
    private float rFlatMPModPerLevel;
    @SerializedName("PercentHPPoolMod")
    private float percentHPPoolMod;
    @SerializedName("PercentMPPoolMod")
    private float percentMPPoolMod;
    @SerializedName("FlatHPRegenMod")
    private float flatHPRegenMod;
    private float rFlatHPRegenModPerLevel;
    @SerializedName("PercentHPRegenMod")
    private float percentHPRegenMod;
    @SerializedName("FlatMPRegenMod")
    private float flatMPRegenMod;
    private float rFlatMPRegenModPerLevel;
    @SerializedName("PercentMPRegenMod")
    private float percentMPRegenMod;
    @SerializedName("FlatArmorMod")
    private float flatArmorMod;
    private float rFlatArmorModPerLevel;
    @SerializedName("PercentArmorMod")
    private float percentArmorMod;
    private float rFlatArmorPenetrationMod;
    private float rFlatArmorPenetrationModPerLevel;
    private float rPercentArmorPenetrationMod;
    private float rPercentArmorPenetrationModPerLevel;
    @SerializedName("FlatPhysicalDamageMod")
    private float flatPhysicalDamageMod;
    private float rFlatPhysicalDamageModPerLevel;
    @SerializedName("PercentPhysicalDamageMod")
    private float percentPhysicalDamageMod;
    @SerializedName("FlatMagicDamageMod")
    private float flatMagicDamageMod;
    private float rFlatMagicDamageModPerLevel;
    @SerializedName("PercentMagicDamageMod")
    private float percentMagicDamageMod;
    @SerializedName("FlatMovementSpeedMod")
    private float flatMovementSpeedMod;
    private float rFlatMovementSpeedModPerLevel;
    @SerializedName("PercentMovementSpeedMod")
    private float percentMovementSpeedMod;
    private float rPercentMovementSpeedModPerLevel;
    @SerializedName("FlatAttackSpeedMod")
    private float flatAttackSpeedMod;
    @SerializedName("PercentAttackSpeedMod")
    private float percentAttackSpeedMod;
    private float rPercentAttackSpeedModPerLevel;
    private float rFlatDodgeMod;
    private float rFlatDodgeModPerLevel;
    @SerializedName("PercentDodgeMod")
    private float percentDodgeMod;
    @SerializedName("FlatCritChanceMod")
    private float flatCritChanceMod;
    private float rFlatCritChanceModPerLevel;
    @SerializedName("PercentCritChanceMod")
    private float percentCritChanceMod;
    @SerializedName("FlatCritDamageMod")
    private float flatCritDamageMod;
    private float rFlatCritDamageModPerLevel;
    @SerializedName("PercentCritDamageMod")
    private float percentCritDamageMod;
    @SerializedName("FlatBlockMod")
    private float flatBlockMod;
    @SerializedName("PercentBlockMod")
    private float percentBlockMod;
    @SerializedName("FlatSpellBlockMod")
    private float flatSpellBlockMod;
    private float rFlatSpellBlockModPerLevel;
    @SerializedName("PercentSpellBlockMod")
    private float percentSpellBlockMod;
    @SerializedName("FlatEXPBonus")
    private float flatEXPBonus;
    @SerializedName("PercentEXPBonus")
    private float percentEXPBonus;
    private float rPercentCooldownMod;
    private float rPercentCooldownModPerLevel;
    private float rFlatTimeDeadMod;
    private float rFlatTimeDeadModPerLevel;
    private float rPercentTimeDeadMod;
    private float rPercentTimeDeadModPerLevel;
    private float rFlatGoldPer1Mod;
    private float rFlatMagicPenetrationMod;
    private float rFlatMagicPenetrationModPerLevel;
    private float rPercentMagicPenetrationMod;
    private float rPercentMagicPenetrationModPerLevel;
    @SerializedName("FlatEnergyRegenMod")
    private float flatEnergyRegenMod;
    private float rFlatEnergyRegenModPerLevel;
    @SerializedName("FlatEnergyPoolMod")
    private float flatEnergyPoolMod;
    private float rFlatEnergyModPerLevel;
    @SerializedName("PercentLifeStealMod")
    private float percentLifeStealMod;
    @SerializedName("PercentSpellVampMod")
    private float percentSpellVampMod;

    public ItemStats() {
    }

    public ItemStats(float flatHPPoolMod, float rFlatHPModPerLevel, float flatMPPoolMod,
                     float rFlatMPModPerLevel, float percentHPPoolMod, float percentMPPoolMod,
                     float flatHPRegenMod, float rFlatHPRegenModPerLevel, float percentHPRegenMod,
                     float flatMPRegenMod, float rFlatMPRegenModPerLevel, float percentMPRegenMod,
                     float flatArmorMod, float rFlatArmorModPerLevel, float percentArmorMod,
                     float rFlatArmorPenetrationMod, float rFlatArmorPenetrationModPerLevel,
                     float rPercentArmorPenetrationMod, float rPercentArmorPenetrationModPerLevel,
                     float flatPhysicalDamageMod, float rFlatPhysicalDamageModPerLevel,
                     float percentPhysicalDamageMod, float flatMagicDamageMod,
                     float rFlatMagicDamageModPerLevel, float percentMagicDamageMod,
                     float flatMovementSpeedMod, float rFlatMovementSpeedModPerLevel,
                     float percentMovementSpeedMod, float rPercentMovementSpeedModPerLevel,
                     float flatAttackSpeedMod, float percentAttackSpeedMod,
                     float rPercentAttackSpeedModPerLevel, float rFlatDodgeMod,
                     float rFlatDodgeModPerLevel, float percentDodgeMod, float flatCritChanceMod,
                     float rFlatCritChanceModPerLevel, float percentCritChanceMod,
                     float flatCritDamageMod, float rFlatCritDamageModPerLevel,
                     float percentCritDamageMod, float flatBlockMod, float percentBlockMod,
                     float flatSpellBlockMod, float rFlatSpellBlockModPerLevel,
                     float percentSpellBlockMod, float flatEXPBonus, float percentEXPBonus,
                     float rPercentCooldownMod, float rPercentCooldownModPerLevel,
                     float rFlatTimeDeadMod, float rFlatTimeDeadModPerLevel,
                     float rPercentTimeDeadMod, float rPercentTimeDeadModPerLevel,
                     float rFlatGoldPer1Mod, float rFlatMagicPenetrationMod,
                     float rFlatMagicPenetrationModPerLevel, float rPercentMagicPenetrationMod,
                     float rPercentMagicPenetrationModPerLevel, float flatEnergyRegenMod,
                     float rFlatEnergyRegenModPerLevel, float flatEnergyPoolMod,
                     float rFlatEnergyModPerLevel, float percentLifeStealMod,
                     float percentSpellVampMod) {

        this.flatHPPoolMod = flatHPPoolMod;
        this.rFlatHPModPerLevel = rFlatHPModPerLevel;
        this.flatMPPoolMod = flatMPPoolMod;
        this.rFlatMPModPerLevel = rFlatMPModPerLevel;
        this.percentHPPoolMod = percentHPPoolMod;
        this.percentMPPoolMod = percentMPPoolMod;
        this.flatHPRegenMod = flatHPRegenMod;
        this.rFlatHPRegenModPerLevel = rFlatHPRegenModPerLevel;
        this.percentHPRegenMod = percentHPRegenMod;
        this.flatMPRegenMod = flatMPRegenMod;
        this.rFlatMPRegenModPerLevel = rFlatMPRegenModPerLevel;
        this.percentMPRegenMod = percentMPRegenMod;
        this.flatArmorMod = flatArmorMod;
        this.rFlatArmorModPerLevel = rFlatArmorModPerLevel;
        this.percentArmorMod = percentArmorMod;
        this.rFlatArmorPenetrationMod = rFlatArmorPenetrationMod;
        this.rFlatArmorPenetrationModPerLevel = rFlatArmorPenetrationModPerLevel;
        this.rPercentArmorPenetrationMod = rPercentArmorPenetrationMod;
        this.rPercentArmorPenetrationModPerLevel = rPercentArmorPenetrationModPerLevel;
        this.flatPhysicalDamageMod = flatPhysicalDamageMod;
        this.rFlatPhysicalDamageModPerLevel = rFlatPhysicalDamageModPerLevel;
        this.percentPhysicalDamageMod = percentPhysicalDamageMod;
        this.flatMagicDamageMod = flatMagicDamageMod;
        this.rFlatMagicDamageModPerLevel = rFlatMagicDamageModPerLevel;
        this.percentMagicDamageMod = percentMagicDamageMod;
        this.flatMovementSpeedMod = flatMovementSpeedMod;
        this.rFlatMovementSpeedModPerLevel = rFlatMovementSpeedModPerLevel;
        this.percentMovementSpeedMod = percentMovementSpeedMod;
        this.rPercentMovementSpeedModPerLevel = rPercentMovementSpeedModPerLevel;
        this.flatAttackSpeedMod = flatAttackSpeedMod;
        this.percentAttackSpeedMod = percentAttackSpeedMod;
        this.rPercentAttackSpeedModPerLevel = rPercentAttackSpeedModPerLevel;
        this.rFlatDodgeMod = rFlatDodgeMod;
        this.rFlatDodgeModPerLevel = rFlatDodgeModPerLevel;
        this.percentDodgeMod = percentDodgeMod;
        this.flatCritChanceMod = flatCritChanceMod;
        this.rFlatCritChanceModPerLevel = rFlatCritChanceModPerLevel;
        this.percentCritChanceMod = percentCritChanceMod;
        this.flatCritDamageMod = flatCritDamageMod;
        this.rFlatCritDamageModPerLevel = rFlatCritDamageModPerLevel;
        this.percentCritDamageMod = percentCritDamageMod;
        this.flatBlockMod = flatBlockMod;
        this.percentBlockMod = percentBlockMod;
        this.flatSpellBlockMod = flatSpellBlockMod;
        this.rFlatSpellBlockModPerLevel = rFlatSpellBlockModPerLevel;
        this.percentSpellBlockMod = percentSpellBlockMod;
        this.flatEXPBonus = flatEXPBonus;
        this.percentEXPBonus = percentEXPBonus;
        this.rPercentCooldownMod = rPercentCooldownMod;
        this.rPercentCooldownModPerLevel = rPercentCooldownModPerLevel;
        this.rFlatTimeDeadMod = rFlatTimeDeadMod;
        this.rFlatTimeDeadModPerLevel = rFlatTimeDeadModPerLevel;
        this.rPercentTimeDeadMod = rPercentTimeDeadMod;
        this.rPercentTimeDeadModPerLevel = rPercentTimeDeadModPerLevel;
        this.rFlatGoldPer1Mod = rFlatGoldPer1Mod;
        this.rFlatMagicPenetrationMod = rFlatMagicPenetrationMod;
        this.rFlatMagicPenetrationModPerLevel = rFlatMagicPenetrationModPerLevel;
        this.rPercentMagicPenetrationMod = rPercentMagicPenetrationMod;
        this.rPercentMagicPenetrationModPerLevel = rPercentMagicPenetrationModPerLevel;
        this.flatEnergyRegenMod = flatEnergyRegenMod;
        this.rFlatEnergyRegenModPerLevel = rFlatEnergyRegenModPerLevel;
        this.flatEnergyPoolMod = flatEnergyPoolMod;
        this.rFlatEnergyModPerLevel = rFlatEnergyModPerLevel;
        this.percentLifeStealMod = percentLifeStealMod;
        this.percentSpellVampMod = percentSpellVampMod;
    }

    public float getFlatHPPoolMod() {
        return flatHPPoolMod;
    }

    public void setFlatHPPoolMod(float flatHPPoolMod) {
        this.flatHPPoolMod = flatHPPoolMod;
    }

    public float getrFlatHPModPerLevel() {
        return rFlatHPModPerLevel;
    }

    public void setrFlatHPModPerLevel(float rFlatHPModPerLevel) {
        this.rFlatHPModPerLevel = rFlatHPModPerLevel;
    }

    public float getFlatMPPoolMod() {
        return flatMPPoolMod;
    }

    public void setFlatMPPoolMod(float flatMPPoolMod) {
        this.flatMPPoolMod = flatMPPoolMod;
    }

    public float getrFlatMPModPerLevel() {
        return rFlatMPModPerLevel;
    }

    public void setrFlatMPModPerLevel(float rFlatMPModPerLevel) {
        this.rFlatMPModPerLevel = rFlatMPModPerLevel;
    }

    public float getPercentHPPoolMod() {
        return percentHPPoolMod;
    }

    public void setPercentHPPoolMod(float percentHPPoolMod) {
        this.percentHPPoolMod = percentHPPoolMod;
    }

    public float getPercentMPPoolMod() {
        return percentMPPoolMod;
    }

    public void setPercentMPPoolMod(float percentMPPoolMod) {
        this.percentMPPoolMod = percentMPPoolMod;
    }

    public float getFlatHPRegenMod() {
        return flatHPRegenMod;
    }

    public void setFlatHPRegenMod(float flatHPRegenMod) {
        this.flatHPRegenMod = flatHPRegenMod;
    }

    public float getrFlatHPRegenModPerLevel() {
        return rFlatHPRegenModPerLevel;
    }

    public void setrFlatHPRegenModPerLevel(float rFlatHPRegenModPerLevel) {
        this.rFlatHPRegenModPerLevel = rFlatHPRegenModPerLevel;
    }

    public float getPercentHPRegenMod() {
        return percentHPRegenMod;
    }

    public void setPercentHPRegenMod(float percentHPRegenMod) {
        this.percentHPRegenMod = percentHPRegenMod;
    }

    public float getFlatMPRegenMod() {
        return flatMPRegenMod;
    }

    public void setFlatMPRegenMod(float flatMPRegenMod) {
        this.flatMPRegenMod = flatMPRegenMod;
    }

    public float getrFlatMPRegenModPerLevel() {
        return rFlatMPRegenModPerLevel;
    }

    public void setrFlatMPRegenModPerLevel(float rFlatMPRegenModPerLevel) {
        this.rFlatMPRegenModPerLevel = rFlatMPRegenModPerLevel;
    }

    public float getPercentMPRegenMod() {
        return percentMPRegenMod;
    }

    public void setPercentMPRegenMod(float percentMPRegenMod) {
        this.percentMPRegenMod = percentMPRegenMod;
    }

    public float getFlatArmorMod() {
        return flatArmorMod;
    }

    public void setFlatArmorMod(float flatArmorMod) {
        this.flatArmorMod = flatArmorMod;
    }

    public float getrFlatArmorModPerLevel() {
        return rFlatArmorModPerLevel;
    }

    public void setrFlatArmorModPerLevel(float rFlatArmorModPerLevel) {
        this.rFlatArmorModPerLevel = rFlatArmorModPerLevel;
    }

    public float getPercentArmorMod() {
        return percentArmorMod;
    }

    public void setPercentArmorMod(float percentArmorMod) {
        this.percentArmorMod = percentArmorMod;
    }

    public float getrFlatArmorPenetrationMod() {
        return rFlatArmorPenetrationMod;
    }

    public void setrFlatArmorPenetrationMod(float rFlatArmorPenetrationMod) {
        this.rFlatArmorPenetrationMod = rFlatArmorPenetrationMod;
    }

    public float getrFlatArmorPenetrationModPerLevel() {
        return rFlatArmorPenetrationModPerLevel;
    }

    public void setrFlatArmorPenetrationModPerLevel(float rFlatArmorPenetrationModPerLevel) {
        this.rFlatArmorPenetrationModPerLevel = rFlatArmorPenetrationModPerLevel;
    }

    public float getrPercentArmorPenetrationMod() {
        return rPercentArmorPenetrationMod;
    }

    public void setrPercentArmorPenetrationMod(float rPercentArmorPenetrationMod) {
        this.rPercentArmorPenetrationMod = rPercentArmorPenetrationMod;
    }

    public float getrPercentArmorPenetrationModPerLevel() {
        return rPercentArmorPenetrationModPerLevel;
    }

    public void setrPercentArmorPenetrationModPerLevel(float rPercentArmorPenetrationModPerLevel) {
        this.rPercentArmorPenetrationModPerLevel = rPercentArmorPenetrationModPerLevel;
    }

    public float getFlatPhysicalDamageMod() {
        return flatPhysicalDamageMod;
    }

    public void setFlatPhysicalDamageMod(float flatPhysicalDamageMod) {
        this.flatPhysicalDamageMod = flatPhysicalDamageMod;
    }

    public float getrFlatPhysicalDamageModPerLevel() {
        return rFlatPhysicalDamageModPerLevel;
    }

    public void setrFlatPhysicalDamageModPerLevel(float rFlatPhysicalDamageModPerLevel) {
        this.rFlatPhysicalDamageModPerLevel = rFlatPhysicalDamageModPerLevel;
    }

    public float getPercentPhysicalDamageMod() {
        return percentPhysicalDamageMod;
    }

    public void setPercentPhysicalDamageMod(float percentPhysicalDamageMod) {
        this.percentPhysicalDamageMod = percentPhysicalDamageMod;
    }

    public float getFlatMagicDamageMod() {
        return flatMagicDamageMod;
    }

    public void setFlatMagicDamageMod(float flatMagicDamageMod) {
        this.flatMagicDamageMod = flatMagicDamageMod;
    }

    public float getrFlatMagicDamageModPerLevel() {
        return rFlatMagicDamageModPerLevel;
    }

    public void setrFlatMagicDamageModPerLevel(float rFlatMagicDamageModPerLevel) {
        this.rFlatMagicDamageModPerLevel = rFlatMagicDamageModPerLevel;
    }

    public float getPercentMagicDamageMod() {
        return percentMagicDamageMod;
    }

    public void setPercentMagicDamageMod(float percentMagicDamageMod) {
        this.percentMagicDamageMod = percentMagicDamageMod;
    }

    public float getFlatMovementSpeedMod() {
        return flatMovementSpeedMod;
    }

    public void setFlatMovementSpeedMod(float flatMovementSpeedMod) {
        this.flatMovementSpeedMod = flatMovementSpeedMod;
    }

    public float getrFlatMovementSpeedModPerLevel() {
        return rFlatMovementSpeedModPerLevel;
    }

    public void setrFlatMovementSpeedModPerLevel(float rFlatMovementSpeedModPerLevel) {
        this.rFlatMovementSpeedModPerLevel = rFlatMovementSpeedModPerLevel;
    }

    public float getPercentMovementSpeedMod() {
        return percentMovementSpeedMod;
    }

    public void setPercentMovementSpeedMod(float percentMovementSpeedMod) {
        this.percentMovementSpeedMod = percentMovementSpeedMod;
    }

    public float getrPercentMovementSpeedModPerLevel() {
        return rPercentMovementSpeedModPerLevel;
    }

    public void setrPercentMovementSpeedModPerLevel(float rPercentMovementSpeedModPerLevel) {
        this.rPercentMovementSpeedModPerLevel = rPercentMovementSpeedModPerLevel;
    }

    public float getFlatAttackSpeedMod() {
        return flatAttackSpeedMod;
    }

    public void setFlatAttackSpeedMod(float flatAttackSpeedMod) {
        this.flatAttackSpeedMod = flatAttackSpeedMod;
    }

    public float getPercentAttackSpeedMod() {
        return percentAttackSpeedMod;
    }

    public void setPercentAttackSpeedMod(float percentAttackSpeedMod) {
        this.percentAttackSpeedMod = percentAttackSpeedMod;
    }

    public float getrPercentAttackSpeedModPerLevel() {
        return rPercentAttackSpeedModPerLevel;
    }

    public void setrPercentAttackSpeedModPerLevel(float rPercentAttackSpeedModPerLevel) {
        this.rPercentAttackSpeedModPerLevel = rPercentAttackSpeedModPerLevel;
    }

    public float getrFlatDodgeMod() {
        return rFlatDodgeMod;
    }

    public void setrFlatDodgeMod(float rFlatDodgeMod) {
        this.rFlatDodgeMod = rFlatDodgeMod;
    }

    public float getrFlatDodgeModPerLevel() {
        return rFlatDodgeModPerLevel;
    }

    public void setrFlatDodgeModPerLevel(float rFlatDodgeModPerLevel) {
        this.rFlatDodgeModPerLevel = rFlatDodgeModPerLevel;
    }

    public float getPercentDodgeMod() {
        return percentDodgeMod;
    }

    public void setPercentDodgeMod(float percentDodgeMod) {
        this.percentDodgeMod = percentDodgeMod;
    }

    public float getFlatCritChanceMod() {
        return flatCritChanceMod;
    }

    public void setFlatCritChanceMod(float flatCritChanceMod) {
        this.flatCritChanceMod = flatCritChanceMod;
    }

    public float getrFlatCritChanceModPerLevel() {
        return rFlatCritChanceModPerLevel;
    }

    public void setrFlatCritChanceModPerLevel(float rFlatCritChanceModPerLevel) {
        this.rFlatCritChanceModPerLevel = rFlatCritChanceModPerLevel;
    }

    public float getPercentCritChanceMod() {
        return percentCritChanceMod;
    }

    public void setPercentCritChanceMod(float percentCritChanceMod) {
        this.percentCritChanceMod = percentCritChanceMod;
    }

    public float getFlatCritDamageMod() {
        return flatCritDamageMod;
    }

    public void setFlatCritDamageMod(float flatCritDamageMod) {
        this.flatCritDamageMod = flatCritDamageMod;
    }

    public float getrFlatCritDamageModPerLevel() {
        return rFlatCritDamageModPerLevel;
    }

    public void setrFlatCritDamageModPerLevel(float rFlatCritDamageModPerLevel) {
        this.rFlatCritDamageModPerLevel = rFlatCritDamageModPerLevel;
    }

    public float getPercentCritDamageMod() {
        return percentCritDamageMod;
    }

    public void setPercentCritDamageMod(float percentCritDamageMod) {
        this.percentCritDamageMod = percentCritDamageMod;
    }

    public float getFlatBlockMod() {
        return flatBlockMod;
    }

    public void setFlatBlockMod(float flatBlockMod) {
        this.flatBlockMod = flatBlockMod;
    }

    public float getPercentBlockMod() {
        return percentBlockMod;
    }

    public void setPercentBlockMod(float percentBlockMod) {
        this.percentBlockMod = percentBlockMod;
    }

    public float getFlatSpellBlockMod() {
        return flatSpellBlockMod;
    }

    public void setFlatSpellBlockMod(float flatSpellBlockMod) {
        this.flatSpellBlockMod = flatSpellBlockMod;
    }

    public float getrFlatSpellBlockModPerLevel() {
        return rFlatSpellBlockModPerLevel;
    }

    public void setrFlatSpellBlockModPerLevel(float rFlatSpellBlockModPerLevel) {
        this.rFlatSpellBlockModPerLevel = rFlatSpellBlockModPerLevel;
    }

    public float getPercentSpellBlockMod() {
        return percentSpellBlockMod;
    }

    public void setPercentSpellBlockMod(float percentSpellBlockMod) {
        this.percentSpellBlockMod = percentSpellBlockMod;
    }

    public float getFlatEXPBonus() {
        return flatEXPBonus;
    }

    public void setFlatEXPBonus(float flatEXPBonus) {
        this.flatEXPBonus = flatEXPBonus;
    }

    public float getPercentEXPBonus() {
        return percentEXPBonus;
    }

    public void setPercentEXPBonus(float percentEXPBonus) {
        this.percentEXPBonus = percentEXPBonus;
    }

    public float getrPercentCooldownMod() {
        return rPercentCooldownMod;
    }

    public void setrPercentCooldownMod(float rPercentCooldownMod) {
        this.rPercentCooldownMod = rPercentCooldownMod;
    }

    public float getrPercentCooldownModPerLevel() {
        return rPercentCooldownModPerLevel;
    }

    public void setrPercentCooldownModPerLevel(float rPercentCooldownModPerLevel) {
        this.rPercentCooldownModPerLevel = rPercentCooldownModPerLevel;
    }

    public float getrFlatTimeDeadMod() {
        return rFlatTimeDeadMod;
    }

    public void setrFlatTimeDeadMod(float rFlatTimeDeadMod) {
        this.rFlatTimeDeadMod = rFlatTimeDeadMod;
    }

    public float getrFlatTimeDeadModPerLevel() {
        return rFlatTimeDeadModPerLevel;
    }

    public void setrFlatTimeDeadModPerLevel(float rFlatTimeDeadModPerLevel) {
        this.rFlatTimeDeadModPerLevel = rFlatTimeDeadModPerLevel;
    }

    public float getrPercentTimeDeadMod() {
        return rPercentTimeDeadMod;
    }

    public void setrPercentTimeDeadMod(float rPercentTimeDeadMod) {
        this.rPercentTimeDeadMod = rPercentTimeDeadMod;
    }

    public float getrPercentTimeDeadModPerLevel() {
        return rPercentTimeDeadModPerLevel;
    }

    public void setrPercentTimeDeadModPerLevel(float rPercentTimeDeadModPerLevel) {
        this.rPercentTimeDeadModPerLevel = rPercentTimeDeadModPerLevel;
    }

    public float getrFlatGoldPer1Mod() {
        return rFlatGoldPer1Mod;
    }

    public void setrFlatGoldPer1Mod(float rFlatGoldPer1Mod) {
        this.rFlatGoldPer1Mod = rFlatGoldPer1Mod;
    }

    public float getrFlatMagicPenetrationMod() {
        return rFlatMagicPenetrationMod;
    }

    public void setrFlatMagicPenetrationMod(float rFlatMagicPenetrationMod) {
        this.rFlatMagicPenetrationMod = rFlatMagicPenetrationMod;
    }

    public float getrFlatMagicPenetrationModPerLevel() {
        return rFlatMagicPenetrationModPerLevel;
    }

    public void setrFlatMagicPenetrationModPerLevel(float rFlatMagicPenetrationModPerLevel) {
        this.rFlatMagicPenetrationModPerLevel = rFlatMagicPenetrationModPerLevel;
    }

    public float getrPercentMagicPenetrationMod() {
        return rPercentMagicPenetrationMod;
    }

    public void setrPercentMagicPenetrationMod(float rPercentMagicPenetrationMod) {
        this.rPercentMagicPenetrationMod = rPercentMagicPenetrationMod;
    }

    public float getrPercentMagicPenetrationModPerLevel() {
        return rPercentMagicPenetrationModPerLevel;
    }

    public void setrPercentMagicPenetrationModPerLevel(float rPercentMagicPenetrationModPerLevel) {
        this.rPercentMagicPenetrationModPerLevel = rPercentMagicPenetrationModPerLevel;
    }

    public float getFlatEnergyRegenMod() {
        return flatEnergyRegenMod;
    }

    public void setFlatEnergyRegenMod(float flatEnergyRegenMod) {
        this.flatEnergyRegenMod = flatEnergyRegenMod;
    }

    public float getrFlatEnergyRegenModPerLevel() {
        return rFlatEnergyRegenModPerLevel;
    }

    public void setrFlatEnergyRegenModPerLevel(float rFlatEnergyRegenModPerLevel) {
        this.rFlatEnergyRegenModPerLevel = rFlatEnergyRegenModPerLevel;
    }

    public float getFlatEnergyPoolMod() {
        return flatEnergyPoolMod;
    }

    public void setFlatEnergyPoolMod(float flatEnergyPoolMod) {
        this.flatEnergyPoolMod = flatEnergyPoolMod;
    }

    public float getrFlatEnergyModPerLevel() {
        return rFlatEnergyModPerLevel;
    }

    public void setrFlatEnergyModPerLevel(float rFlatEnergyModPerLevel) {
        this.rFlatEnergyModPerLevel = rFlatEnergyModPerLevel;
    }

    public float getPercentLifeStealMod() {
        return percentLifeStealMod;
    }

    public void setPercentLifeStealMod(float percentLifeStealMod) {
        this.percentLifeStealMod = percentLifeStealMod;
    }

    public float getPercentSpellVampMod() {
        return percentSpellVampMod;
    }

    public void setPercentSpellVampMod(float percentSpellVampMod) {
        this.percentSpellVampMod = percentSpellVampMod;
    }
}
