package com.legue.axel.lolsummonertool.database.model.champion

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import com.google.gson.annotations.SerializedName
import com.legue.axel.lolsummonertool.database.converter.FloatListConverters

//TODO add relation beetween Spell and /Effect/EffectBurn
//TODO : handle effect and effectBurns values ...
@TypeConverters(FloatListConverters::class)
@Entity(tableName = "spells", foreignKeys = [ForeignKey(entity = Champion::class, parentColumns = ["key"], childColumns = ["championId"], onDelete = CASCADE)], indices = [Index("championId")])
data class Spell(
        @PrimaryKey
        val id: String,
        val name: String? = null,
        val description: String? = null,
        @SerializedName("tooltip")
        val toolTip: String? = null,
        @SerializedName("maxrank")
        val maxRank: Int? = null,
        val cooldown: List<Float>? = null,
        val cooldownBurn: String? = null,
        val cost: List<Float>? = null,
        val costBurn: String? = null,
        val costType: String? = null,
        val maxammo: String? = null,
        val range: List<Float>? = null,
        val rangeBurn: String? = null,
        val resource: String? = null,
        val championId: Int = 0
)