package com.legue.axel.lolsummonertool.database.model.item;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

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
