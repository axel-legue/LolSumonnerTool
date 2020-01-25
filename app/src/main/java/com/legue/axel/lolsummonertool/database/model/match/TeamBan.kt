package com.legue.axel.lolsummonertool.database.model.match

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "teamBans", foreignKeys = [ForeignKey(entity = TeamStat::class, parentColumns = ["id"], childColumns = ["teamStatId"], onDelete = CASCADE)])
data class TeamBan(
        @PrimaryKey(autoGenerate = true)
        val id: Int? = null,
        val pickTurn: Int? = null,
        val championId: Int? = null,
        @ColumnInfo(index = true)
        var teamStatId: Int? = null
) 