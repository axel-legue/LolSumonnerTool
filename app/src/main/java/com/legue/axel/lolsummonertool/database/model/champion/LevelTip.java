package com.legue.axel.lolsummonertool.database.model.champion;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.legue.axel.lolsummonertool.database.converter.StringListConverters;

import java.util.List;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "level_tip",
        foreignKeys = {
                @ForeignKey(
                        entity = Spell.class,
                        parentColumns = "id",
                        childColumns = "spellId",
                        onDelete = CASCADE
                )}
)
public class LevelTip {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @TypeConverters(StringListConverters.class)
    public List<String> label;
    @TypeConverters(StringListConverters.class)
    public List<String> effect;
    @ColumnInfo(index = true)
    public String spellId;


    @Ignore
    public LevelTip() {
    }

    public LevelTip(int id, List<String> label, List<String> effect, String spellId) {
        this.id = id;
        this.label = label;
        this.effect = effect;
        this.spellId = spellId;
    }
}
