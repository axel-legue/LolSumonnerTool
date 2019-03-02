package com.legue.axel.lolsummonertool.database.model;

public class Gold {
    private int base;
    private int total;
    private int sell;
    private boolean purchasable;

    public Gold() {
    }

    public Gold(int base, int total, int sell, boolean purchasable) {
        this.base = base;
        this.total = total;
        this.sell = sell;
        this.purchasable = purchasable;
    }

    public int getBase() {
        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSell() {
        return sell;
    }

    public void setSell(int sell) {
        this.sell = sell;
    }

    public boolean isPurchasable() {
        return purchasable;
    }

    public void setPurchasable(boolean purchasable) {
        this.purchasable = purchasable;
    }
}
