package com.legue.axel.lolsummonertool.database.model.champion;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.legue.axel.lolsummonertool.database.model.RiotImage;

@Entity(tableName = "passive")
public class Passive {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String description;
    // TODO ADD RELATION  BETWEEN Passive and  RiotImage

    @Ignore
    public Passive() {
    }

    public Passive(int id, String name, String description, RiotImage image) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
