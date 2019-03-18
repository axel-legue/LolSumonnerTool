package com.legue.axel.lolsummonertool.database.model.item;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(
        tableName = "item_tags",
        foreignKeys = @ForeignKey(
                entity = Item.class,
                parentColumns = "id",
                childColumns = "itemId",
                onDelete = CASCADE)
)
public class ItemTag {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String tag;
    @ColumnInfo(index = true)
    public int itemId;

    @Ignore
    public ItemTag() {
    }

    public ItemTag(int id, String tag, int itemId) {
        this.id = id;
        this.tag = tag;
        this.itemId = itemId;
    }
}
