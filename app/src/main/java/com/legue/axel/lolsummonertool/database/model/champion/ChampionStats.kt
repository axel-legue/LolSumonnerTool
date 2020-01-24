package com.legue.axel.lolsummonertool.database.model.champion

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "champion_stats", foreignKeys = [ForeignKey(entity = Champion::class, parentColumns = ["key"], childColumns = ["championId"], onDelete = CASCADE)])
data class ChampionStats(
        @PrimaryKey(autoGenerate = true)
        var id: Int? = null,
        var hp: Float? = null,
        @SerializedName("hpperlevel")
        var hpPerLevel: Float? = null,
        var mp: Float? = null,
        @SerializedName("mpperlevel")
        var mpPerLevel: Float? = null,
        @SerializedName("movespeed")
        var moveSpeed: Float? = null,
        var armor: Float? = null,
        @SerializedName("armorperlevel")
        var armorPerLevel: Float? = null,
        @SerializedName("spellblock")
        var spellBlock: Float? = null,
        @SerializedName("spellblockperlevel")
        var spellBlockPerLevel: Float? = null,
        @SerializedName("attackrange")
        var attackRange: Float? = null,
        @SerializedName("hpregen")
        var hpRegen: Float? = null,
        @SerializedName("hpregenperlevel")
        var hpRegenPerLevel: Float? = null,
        @SerializedName("mpregen")
        var mpRegen: Float? = null,
        @SerializedName("mpregenperlevel")
        var mpRegenPerLevel: Float? = null,
        @SerializedName("crit")
        var crit: Float? = null,
        @SerializedName("critperlevel")
        var critPerLevel: Float? = null,
        @SerializedName("attackdamage")
        var attackDamage: Float? = null,
        @SerializedName("attackdamageperlevel")
        var attackDamagePerLevel: Float? = null,
        @SerializedName("attackspeedperlevel")
        var attackSpeedPerLevel: Float? = null,
        @SerializedName("attackspeed")
        var attackSpeed: Float? = null,
        @ColumnInfo(index = true)
        var championId: Int = 0
)