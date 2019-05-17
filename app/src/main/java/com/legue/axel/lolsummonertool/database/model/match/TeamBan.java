package com.legue.axel.lolsummonertool.database.model.match;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

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
