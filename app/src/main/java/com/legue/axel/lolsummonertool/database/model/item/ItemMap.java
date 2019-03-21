package com.legue.axel.lolsummonertool.database.model.item;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

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
