package com.legue.axel.lolsummonertool.database.model.champion;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "level_tip")
public class LevelTip {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @SerializedName("label")
    private List<String> labels;
    @SerializedName("effect")
    private List<String> effects;

    @Ignore
    public LevelTip() {
    }

    public LevelTip(int id, List<String> labels, List<String> effects) {
        this.id = id;
        this.labels = labels;
        this.effects = effects;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public List<String> getEffects() {
        return effects;
    }

    public void setEffects(List<String> effects) {
        this.effects = effects;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
