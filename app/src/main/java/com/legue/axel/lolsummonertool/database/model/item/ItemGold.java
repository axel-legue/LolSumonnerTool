package com.legue.axel.lolsummonertool.database.model.item;

public class ItemGold {
    public int base;
    public int total;
    public int sell;
    public boolean purchasable;

    public ItemGold() {
    }

    public ItemGold(int base, int total, int sell, boolean purchasable) {
        this.base = base;
        this.total = total;
        this.sell = sell;
        this.purchasable = purchasable;
    }
}
