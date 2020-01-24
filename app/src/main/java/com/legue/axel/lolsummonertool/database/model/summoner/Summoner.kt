package com.legue.axel.lolsummonertool.database.model.summoner

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "summoners")
data class Summoner(
        @PrimaryKey
        var id: String,
        var name: String? = null,
        var puuid: String? = null,
        var summonerLevel: Int = 0,
        var accountId: String,
        var revisionDate: Long = 0,
        var profileIconId: Int = 0
)