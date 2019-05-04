package com.legue.axel.lolsummonertool.database.model.match;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(
        tableName = "teamBans",
        foreignKeys = @ForeignKey(
                entity = TeamStat.class,
                parentColumns = "id",
                childColumns = "teamStatId",
                onDelete = CASCADE)
)
public class TeamBan {
// TODO: 29/04/2019  Add relation to Champion ?

    @PrimaryKey(autoGenerate = true)
    public int id;
    public int pickTurn;
    public int championId;
    @ColumnInfo(index = true)
    public int teamStatId;

    @Ignore
    public TeamBan() {
    }

    public TeamBan(int id, int pickTurn, int championId, int teamStatId) {
        this.id = id;
        this.pickTurn = pickTurn;
        this.championId = championId;
        this.teamStatId = teamStatId;
    }
}
