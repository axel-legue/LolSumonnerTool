package com.legue.axel.lolsummonertool.database.model.champion;

import androidx.room.ColumnInfo;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

//@Entity(tableName = "passive_images",
//        foreignKeys = {
//                @ForeignKey(
//                        entity = Passive.class,
//                        parentColumns = "id",
//                        childColumns = "passiveId",
//                        onDelete = CASCADE
//                )}
//)
@Deprecated
public class PassiveImage {

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
    public int passiveId;

    @Ignore
    public PassiveImage() {
    }

    public PassiveImage(int id, String full, String sprite, String group, int x, int y, int w, int h, int passiveId) {
        this.id = id;
        this.full = full;
        this.sprite = sprite;
        this.group = group;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.passiveId = passiveId;

    }
}
