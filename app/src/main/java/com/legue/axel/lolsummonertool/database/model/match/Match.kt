package com.legue.axel.lolsummonertool.database.model.match

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "matches")
data class Match(
        @PrimaryKey
        val gameId: Long = 0,
        val seasonId: Int? = null,
        val queueId: Int? = null,
        val gameVersion: String? = null,
        val platformId: String? = null,
        val gameMode: String? = null,
        val mapId: Int? = null,
        val gameType: String? = null,
        val gameDuration: Long? = null,
        val gameCreation: Long? = null
)