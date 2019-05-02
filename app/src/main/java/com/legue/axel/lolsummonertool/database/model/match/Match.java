package com.legue.axel.lolsummonertool.database.model.match;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "matches")
public class Match {

    @PrimaryKey
    public long gameId;
    public int seasonId;
    public int queueId;
    public String gameVersion;
    public String platformId;
    public String gameMode;
    public int mapId;
    public String gameType;
    public long gameDuration;
    public long gameCreation;

    @Ignore
    public Match() {
    }

    public Match(long gameId, int seasonId, int queueId, String gameVersion, String platformId, String gameMode, int mapId, String gameType, long gameDuration, long gameCreation) {
        this.gameId = gameId;
        this.seasonId = seasonId;
        this.queueId = queueId;
        this.gameVersion = gameVersion;
        this.platformId = platformId;
        this.gameMode = gameMode;
        this.mapId = mapId;
        this.gameType = gameType;
        this.gameDuration = gameDuration;
        this.gameCreation = gameCreation;
    }
}
