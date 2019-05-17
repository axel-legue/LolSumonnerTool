package com.legue.axel.lolsummonertool.database.model.champion;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import static androidx.room.ForeignKey.CASCADE;


@Entity(
        tableName = "recommended",
        foreignKeys = @ForeignKey(
                entity = Champion.class,
                parentColumns = "key",
                childColumns = "championId",
                onDelete = CASCADE),
        indices = @Index("championId")
)
public class Recommended {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String champion;
    public String title;
    public String map;
    public String mode;
    public String type;
    public String customTag;
    @SerializedName("sortrank")
    public int sortRank;
    public boolean extensionPage;
    public int championId;

    // TODO ADD RELATION  BETWEEN Blocks and  Recommended

    @Ignore
    public Recommended() {
    }

    public Recommended(int id, String champion, String title, String map, String mode, String type, String customTag, int sortRank, boolean extensionPage, int championId) {
        this.id = id;
        this.champion = champion;
        this.title = title;
        this.map = map;
        this.mode = mode;
        this.type = type;
        this.customTag = customTag;
        this.sortRank = sortRank;
        this.extensionPage = extensionPage;
        this.championId = championId;
    }
}
