package com.legue.axel.lolsummonertool.database.model.match

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "playersIdentity", foreignKeys = [ForeignKey(entity = Match::class, parentColumns = ["gameId"], childColumns = ["matchId"], onDelete = CASCADE)])
data class PlayerIdentity(
        @PrimaryKey
        @NonNull
        val accountId: String,
        val participantId: Int? = null,
        val currentPlatformId: String? = null,
        val summonerName: String? = null,
        val matchHistoryUri: String? = null,
        val platformId: String? = null,
        val currentAccountId: String? = null,
        val profileIcon: Int? = null,
        val summonerId: String? = null,
        @ColumnInfo(index = true)
        var matchId: Int? = null
)
