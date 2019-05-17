package com.legue.axel.lolsummonertool.database.model.item;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "item_stats",
        foreignKeys = {
                @ForeignKey(
                        entity = Item.class,
                        parentColumns = "id",
                        childColumns = "itemId",
                        onDelete = CASCADE
                )}
)
public class ItemStat {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String key;
    public float value;
    @ColumnInfo(index = true)
    public int itemId;

    @Ignore
    public ItemStat() {
    }

    public ItemStat(int id, String key, float value, int itemId) {
        this.id = id;
        this.key = key;
        this.value = value;
        this.itemId = itemId;
    }
}
