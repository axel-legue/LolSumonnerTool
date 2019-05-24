package com.legue.axel.lolsummonertool.database.model.summoner;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;


@Entity(tableName = "summoners")
public class Summoner {

    @PrimaryKey()
    @NonNull
    public String id;
    public String name;
    public String puuid;
    public int summonerLevel;
    public String accountId;
    public long revisionDate;
    public int profileIconId;

    @Ignore
    public Summoner() {
    }

    public Summoner( String id, String name, String puuid, int summonerLevel, String accountId, long revisionDate, int profileIconId) {
        this.id = id;
        this.name = name;
        this.puuid = puuid;
        this.summonerLevel = summonerLevel;
        this.accountId = accountId;
        this.revisionDate = revisionDate;
        this.profileIconId = profileIconId;
    }
}
