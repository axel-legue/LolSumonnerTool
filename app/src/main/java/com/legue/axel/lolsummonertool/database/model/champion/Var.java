package com.legue.axel.lolsummonertool.database.model.champion;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.legue.axel.lolsummonertool.database.converter.FloatListConverters;

import java.util.List;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "vars",
        foreignKeys = {
                @ForeignKey(
                        entity = Spell.class,
                        parentColumns = "id",
                        childColumns = "spellId",
                        onDelete = CASCADE
                )}
)
public class Var {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String link;
    @TypeConverters(FloatListConverters.class)
    public List<Float> coeff;
    public String key;
    @ColumnInfo(index = true)
    public String spellId;

    @Ignore
    public Var() {
    }

    public Var(int id, String link, List<Float> coeff, String key, String spellId) {
        this.id = id;
        this.link = link;
        this.coeff = coeff;
        this.key = key;
        this.spellId = spellId;
    }
}
