package com.legue.axel.lolsummonertool.database.model.item;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "item_effects",
        foreignKeys = {
                @ForeignKey(
                        entity = Item.class,
                        parentColumns = "id",
                        childColumns = "itemId"
                )}
)
public class ItemEffect {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String key;
    public String value;
    @ColumnInfo(index = true)
    public int itemId;

    @Ignore
    public ItemEffect() {
    }

    public ItemEffect(int id, String key, String value, int itemId) {
        this.id = id;
        this.key = key;
        this.value = value;
        this.itemId = itemId;
    }
}
