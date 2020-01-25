package com.legue.axel.lolsummonertool.database.model.summonerspell

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "summoner_spell_images", foreignKeys = [ForeignKey(entity = SummonerSpell::class, parentColumns = ["key"], childColumns = ["summonerSpellId"], onDelete = CASCADE)])
data class SummonerSpellImage(
        @PrimaryKey(autoGenerate = true)
        var id: Int? = null,
        var full: String? = null,
        var sprite: String? = null,
        var group: String? = null,
        var x: Int = 0,
        var y: Int = 0,
        var w: Int = 0,
        var h: Int = 0,
        @ColumnInfo(index = true)
        var summonerSpellId: Int = 0
)