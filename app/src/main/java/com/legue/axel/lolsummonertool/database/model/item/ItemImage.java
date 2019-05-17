package com.legue.axel.lolsummonertool.database.model.item;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "item_images",
        foreignKeys = {
                @ForeignKey(
                        entity = Item.class,
                        parentColumns = "id",
                        childColumns = "itemId",
                        onDelete = CASCADE
                )}
)
public class ItemImage {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String full;
    public String sprite;
    public String group;
    public int x;
    public int y;
    public int w;
    public int h;
    @ColumnInfo(index = true)
    public int itemId;

    @Ignore
    public ItemImage() {
    }

    public ItemImage(int id, String full, String sprite, String group, int x, int y, int w, int h, int itemId) {
        this.id = id;
        this.full = full;
        this.sprite = sprite;
        this.group = group;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.itemId = itemId;
    }
}
