package com.legue.axel.lolsummonertool.database.model.match;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(
        tableName = "teamStats",
        foreignKeys = @ForeignKey(
                entity = Match.class,
                parentColumns = "gameId",
                childColumns = "matchId",
                onDelete = CASCADE)
)
public class TeamStat {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public boolean firstDragon;
    public boolean firstInhibitor;
    public int baronKills;
    public boolean firstRiftHerald;
    public boolean firstBaron;
    public int riftHeraldKills;
    public boolean firstBlood;
    public int teamId; // 100 for blue side - 200 for red side
    public boolean firstTower;
    public int vilemawKills;
    public int inhibitorKills;
    public int towerKills;
    public int dominionVictoryScore;
    public String win; // String indicating whether or not the team won. There are only two values visibile in public Match history. (Legal values: Fail, Win)
    public int dragonKills;
    @ColumnInfo(index = true)
    public int matchId;


    @Ignore
    public TeamStat() {
    }

    public TeamStat(int id, boolean firstDragon, boolean firstInhibitor, int baronKills,
                    boolean firstRiftHerald, boolean firstBaron, int riftHeraldKills,
                    boolean firstBlood, int teamId, boolean firstTower, int vilemawKills,
                    int inhibitorKills, int towerKills, int dominionVictoryScore, String win,
                    int dragonKills, int matchId) {
        this.id = id;
        this.firstDragon = firstDragon;
        this.firstInhibitor = firstInhibitor;
        this.baronKills = baronKills;
        this.firstRiftHerald = firstRiftHerald;
        this.firstBaron = firstBaron;
        this.riftHeraldKills = riftHeraldKills;
        this.firstBlood = firstBlood;
        this.teamId = teamId;
        this.firstTower = firstTower;
        this.vilemawKills = vilemawKills;
        this.inhibitorKills = inhibitorKills;
        this.towerKills = towerKills;
        this.dominionVictoryScore = dominionVictoryScore;
        this.win = win;
        this.dragonKills = dragonKills;
        this.matchId = matchId;
    }
}
