package com.legue.axel.lolsummonertool.database.model.match

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "teamStats", foreignKeys = [ForeignKey(entity = Match::class, parentColumns = ["gameId"], childColumns = ["matchId"], onDelete = CASCADE)])
data class TeamStat(
        @PrimaryKey(autoGenerate = true)
        @NonNull
        var id: Int,
        var firstDragon: Boolean? = null,
        var firstInhibitor: Boolean? = null,
        var baronKills: Int? = null,
        var firstRiftHerald: Boolean? = null,
        var firstBaron: Boolean? = null,
        var riftHeraldKills: Int? = null,
        var firstBlood: Boolean? = null,
        var teamId: Int? = null, // 100 for blue side - 200 for red side
        var firstTower: Boolean? = null,
        var vilemawKills: Int? = null,
        var inhibitorKills: Int? = null,
        var towerKills: Int? = null,
        var dominionVictoryScore: Int? = null,
        var win: String? = null, // String indicating whether or not the team won. There are only two values visibile in public Match history. (Legal values: Fail, Win)
        var dragonKills: Int? = null,
        @ColumnInfo(index = true)
        var matchId: Long? = null
)