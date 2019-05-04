package com.legue.axel.lolsummonertool.database.model.match;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(
        tableName = "playersIdentity",
        foreignKeys = @ForeignKey(
                entity = Match.class,
                parentColumns = "gameId",
                childColumns = "matchId",
                onDelete = CASCADE)
)
public class PlayerIdentity {

    @PrimaryKey
    @NonNull
    public String accountId;
    public int participantId;
    public String currentPlatformId;
    public String summonerName;
    public String matchHistoryUri;
    public String platformId;
    public String currentAccountId;
    public int profileIcon;
    public String summonerId;
    @ColumnInfo(index = true)
    public int matchId;

    @Ignore
    public PlayerIdentity() {
    }

    public PlayerIdentity(String accountId, int participantId, String currentPlatformId,
                          String summonerName, String matchHistoryUri, String platformId,
                          String currentAccountId, int profileIcon, String summonerId, int matchId) {
        this.accountId = accountId;
        this.participantId = participantId;
        this.currentPlatformId = currentPlatformId;
        this.summonerName = summonerName;
        this.matchHistoryUri = matchHistoryUri;
        this.platformId = platformId;
        this.currentAccountId = currentAccountId;
        this.profileIcon = profileIcon;
        this.summonerId = summonerId;
        this.matchId = matchId;
    }
}
