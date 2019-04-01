package com.legue.axel.lolsummonertool.database.model.champion;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "spell_images",
        foreignKeys = {
                @ForeignKey(
                        entity = Passive.class,
                        parentColumns = "id",
                        childColumns = "spellId",
                        onDelete = CASCADE
                )}
)
public class SpellImage {

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
    public int spellId;

    @Ignore
    public SpellImage() {
    }

    public SpellImage(int id, String full, String sprite, String group, int x, int y, int w, int h, int spellId) {
        this.id = id;
        this.full = full;
        this.sprite = sprite;
        this.group = group;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.spellId = spellId;

    }
}
