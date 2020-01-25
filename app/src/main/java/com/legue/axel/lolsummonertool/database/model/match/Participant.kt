package com.legue.axel.lolsummonertool.database.model.match

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

// TODO: 29/04/2019  Add relation to Champion ?
@Entity(tableName = "participants", foreignKeys = [ForeignKey(entity = Match::class, parentColumns = ["gameId"], childColumns = ["matchId"], onDelete = CASCADE)])
data class Participant(
        @PrimaryKey(autoGenerate = true)
        val id: Int? = null,
        val participantId: Int? = null,
        val teamId: Int? = null,
        val spell2Id: Int? = null,//Second Summoner Spell id.
        val highestAchievedSeasonTier: String? = null, // Legal values: CHALLENGER, MASTER, DIAMOND, PLATINUM, GOLD, SILVER, BRONZE, UNRANKED.
        val spell1Id: Int? = null,
        val championId: Int? = null,
        @ColumnInfo(index = true)
        var matchId: Long = 0
)