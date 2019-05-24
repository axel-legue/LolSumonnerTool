package com.legue.axel.lolsummonertool.database.model.match;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

import static androidx.room.ForeignKey.CASCADE;

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

    public PlayerIdentity( String accountId, int participantId, String currentPlatformId,
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
