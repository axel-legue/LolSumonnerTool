package com.legue.axel.lolsummonertool.database.model.summonerspell

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.legue.axel.lolsummonertool.database.converter.FloatListConverters
import com.legue.axel.lolsummonertool.database.converter.IntegerListConverters
import com.legue.axel.lolsummonertool.database.converter.StringListConverters

//TODO add relation between SummonerSpell and SummonerSpellVars one to many
//TODO add relation between SummonerSpell and SummonerSpellImage
@TypeConverters(IntegerListConverters::class, FloatListConverters::class, StringListConverters::class)
@Entity(tableName = "summoner_spells")
data class SummonerSpell(
        var id: String,
        @PrimaryKey
        var key: Int = 0,
        var name: String? = null,
        var description: String? = null,
        var tooltip: String? = null,
        var maxrank: Int = 0,
        var cooldownBurn: String? = null,
        var costBurn: String? = null,
        var summonerLevel: Int = 0,
        var costType: String? = null,
        var maxammo: String? = null,
        var rangeBurn: String? = null,
        var resource: String? = null,
        var cost: List<Int>? = null,
        var effectBurn: List<Float>? = null,
        var cooldown: List<Int>? = null,
        var range: List<Int>? = null,
        var modes: List<String>? = null
)