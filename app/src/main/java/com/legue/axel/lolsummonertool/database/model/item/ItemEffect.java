package com.legue.axel.lolsummonertool.database.model.item;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "item_effects",
        foreignKeys = {
                @ForeignKey(
                        entity = Item.class,
                        parentColumns = "id",
                        childColumns = "itemId",
                        onDelete = CASCADE
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
