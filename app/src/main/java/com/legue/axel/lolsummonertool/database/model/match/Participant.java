package com.legue.axel.lolsummonertool.database.model.match;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

// TODO: 29/04/2019  Add relation to Champion ?
@Entity(
        tableName = "participants",
        foreignKeys = @ForeignKey(
                entity = Match.class,
                parentColumns = "id",
                childColumns = "matchId",
                onDelete = CASCADE)
)
public class Participant {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public int participantId;
    public int teamId;
    public int spell2Id; //Second Summoner Spell id.
    public String highestAchievedSeasonTier; // Legal values: CHALLENGER, MASTER, DIAMOND, PLATINUM, GOLD, SILVER, BRONZE, UNRANKED.
    public int spell1Id;
    public int championId;
    public int matchId;

    @Ignore
    public Participant() {
    }

    public Participant(int id, int participantId, int teamId, int spell2Id,
                       String highestAchievedSeasonTier, int spell1Id, int championId,
                       int matchId) {
        this.id = id;
        this.participantId = participantId;
        this.teamId = teamId;
        this.spell2Id = spell2Id;
        this.highestAchievedSeasonTier = highestAchievedSeasonTier;
        this.spell1Id = spell1Id;
        this.championId = championId;
        this.matchId = matchId;
    }
}
