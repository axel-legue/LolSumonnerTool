package com.legue.axel.lolsummonertool.database.model.item;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "item_golds",
        foreignKeys = {
                @ForeignKey(
                        entity = Item.class,
                        parentColumns = "id",
                        childColumns = "itemId",
                        onDelete = CASCADE
                )}
)
public class ItemGold {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int base;
    public int total;
    public int sell;
    public boolean purchasable;
    @ColumnInfo(index = true)
    public int itemId;

    @Ignore
    public ItemGold() {
    }

    public ItemGold(int id, int base, int total, int sell, boolean purchasable, int itemId) {
        this.id = id;
        this.base = base;
        this.total = total;
        this.sell = sell;
        this.purchasable = purchasable;
        this.itemId = itemId;
    }
}
