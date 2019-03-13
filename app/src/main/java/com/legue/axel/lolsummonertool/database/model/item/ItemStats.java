package com.legue.axel.lolsummonertool.database.model.item;

import com.google.gson.annotations.SerializedName;

public class ItemStats {

    @SerializedName("FlatHPPoolMod")
    public float flatHPPoolMod;
    public float rFlatHPModPerLevel;
    @SerializedName("FlatMPPoolMod")
    public float flatMPPoolMod;
    public float rFlatMPModPerLevel;
    @SerializedName("PercentHPPoolMod")
    public float percentHPPoolMod;
    @SerializedName("PercentMPPoolMod")
    public float percentMPPoolMod;
    @SerializedName("FlatHPRegenMod")
    public float flatHPRegenMod;
    public float rFlatHPRegenModPerLevel;
    @SerializedName("PercentHPRegenMod")
    public float percentHPRegenMod;
    @SerializedName("FlatMPRegenMod")
    public float flatMPRegenMod;
    public float rFlatMPRegenModPerLevel;
    @SerializedName("PercentMPRegenMod")
    public float percentMPRegenMod;
    @SerializedName("FlatArmorMod")
    public float flatArmorMod;
    public float rFlatArmorModPerLevel;
    @SerializedName("PercentArmorMod")
    public float percentArmorMod;
    public float rFlatArmorPenetrationMod;
    public float rFlatArmorPenetrationModPerLevel;
    public float rPercentArmorPenetrationMod;
    public float rPercentArmorPenetrationModPerLevel;
    @SerializedName("FlatPhysicalDamageMod")
    public float flatPhysicalDamageMod;
    public float rFlatPhysicalDamageModPerLevel;
    @SerializedName("PercentPhysicalDamageMod")
    public float percentPhysicalDamageMod;
    @SerializedName("FlatMagicDamageMod")
    public float flatMagicDamageMod;
    public float rFlatMagicDamageModPerLevel;
    @SerializedName("PercentMagicDamageMod")
    public float percentMagicDamageMod;
    @SerializedName("FlatMovementSpeedMod")
    public float flatMovementSpeedMod;
    public float rFlatMovementSpeedModPerLevel;
    @SerializedName("PercentMovementSpeedMod")
    public float percentMovementSpeedMod;
    public float rPercentMovementSpeedModPerLevel;
    @SerializedName("FlatAttackSpeedMod")
    public float flatAttackSpeedMod;
    @SerializedName("PercentAttackSpeedMod")
    public float percentAttackSpeedMod;
    public float rPercentAttackSpeedModPerLevel;
    public float rFlatDodgeMod;
    public float rFlatDodgeModPerLevel;
    @SerializedName("PercentDodgeMod")
    public float percentDodgeMod;
    @SerializedName("FlatCritChanceMod")
    public float flatCritChanceMod;
    public float rFlatCritChanceModPerLevel;
    @SerializedName("PercentCritChanceMod")
    public float percentCritChanceMod;
    @SerializedName("FlatCritDamageMod")
    public float flatCritDamageMod;
    public float rFlatCritDamageModPerLevel;
    @SerializedName("PercentCritDamageMod")
    public float percentCritDamageMod;
    @SerializedName("FlatBlockMod")
    public float flatBlockMod;
    @SerializedName("PercentBlockMod")
    public float percentBlockMod;
    @SerializedName("FlatSpellBlockMod")
    public float flatSpellBlockMod;
    public float rFlatSpellBlockModPerLevel;
    @SerializedName("PercentSpellBlockMod")
    public float percentSpellBlockMod;
    @SerializedName("FlatEXPBonus")
    public float flatEXPBonus;
    @SerializedName("PercentEXPBonus")
    public float percentEXPBonus;
    public float rPercentCooldownMod;
    public float rPercentCooldownModPerLevel;
    public float rFlatTimeDeadMod;
    public float rFlatTimeDeadModPerLevel;
    public float rPercentTimeDeadMod;
    public float rPercentTimeDeadModPerLevel;
    public float rFlatGoldPer1Mod;
    public float rFlatMagicPenetrationMod;
    public float rFlatMagicPenetrationModPerLevel;
    public float rPercentMagicPenetrationMod;
    public float rPercentMagicPenetrationModPerLevel;
    @SerializedName("FlatEnergyRegenMod")
    public float flatEnergyRegenMod;
    public float rFlatEnergyRegenModPerLevel;
    @SerializedName("FlatEnergyPoolMod")
    public float flatEnergyPoolMod;
    public float rFlatEnergyModPerLevel;
    @SerializedName("PercentLifeStealMod")
    public float percentLifeStealMod;
    @SerializedName("PercentSpellVampMod")
    public float percentSpellVampMod;

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
}
