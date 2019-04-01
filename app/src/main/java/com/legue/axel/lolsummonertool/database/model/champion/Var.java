package com.legue.axel.lolsummonertool.database.model.champion;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "vars",
        foreignKeys = {
                @ForeignKey(
                        entity = Spell.class,
                        parentColumns = "key",
                        childColumns = "spellId",
                        onDelete = CASCADE
                )}
)
public class Var {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String link;
    public Float coeff;
    public String key;
    @ColumnInfo(index = true)
    public int spellId;

    @Ignore
    public Var() {
    }

    public Var(int id, String link, Float coeff, String key, int spellId) {
        this.id = id;
        this.link = link;
        this.coeff = coeff;
        this.key = key;
        this.spellId = spellId;
    }
}
