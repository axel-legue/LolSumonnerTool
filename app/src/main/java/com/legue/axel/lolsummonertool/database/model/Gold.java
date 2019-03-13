package com.legue.axel.lolsummonertool.database.model;

public class Gold {
    public int base;
    public int total;
    public int sell;
    public boolean purchasable;

    public Gold() {
    }

    public Gold(int base, int total, int sell, boolean purchasable) {
        this.base = base;
        this.total = total;
        this.sell = sell;
        this.purchasable = purchasable;
    }
}
