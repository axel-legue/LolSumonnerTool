package com.legue.axel.lolsummonertool.database.model.match;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

// TODO: 29/04/2019  Add relation to Champion ?
@Entity(
        tableName = "participants",
        foreignKeys = @ForeignKey(
                entity = Match.class,
                parentColumns = "gameId",
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
    @ColumnInfo(index = true)
    public long matchId;

    @Ignore
    public Participant() {
    }

    public Participant(int id, int participantId, int teamId, int spell2Id,
                       String highestAchievedSeasonTier, int spell1Id, int championId,
                       long matchId) {
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
