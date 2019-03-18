package com.legue.axel.lolsummonertool.database.model.item;

public class ItemStat {

    public float FlatMagicDamageMod;
    public float PercentMovementSpeedMod;

    public ItemStat(float flatMagicDamageMod, float percentMovementSpeedMod) {
        FlatMagicDamageMod = flatMagicDamageMod;
        PercentMovementSpeedMod = percentMovementSpeedMod;
    }

    public ItemStat() {
    }
}
