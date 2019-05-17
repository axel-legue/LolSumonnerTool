package com.legue.axel.lolsummonertool.database.model.item;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "item_maps",
        foreignKeys = {
                @ForeignKey(
                        entity = Item.class,
                        parentColumns = "id",
                        childColumns = "itemId",
                        onDelete = CASCADE

                )}
)
public class ItemMap {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String key;
    public Boolean value;
    @ColumnInfo(index = true)
    public int itemId;

    @Ignore
    public ItemMap() {
    }

    public ItemMap(int id, String key, Boolean value, int itemId) {
        this.id = id;
        this.key = key;
        this.value = value;
        this.itemId = itemId;
    }
}
