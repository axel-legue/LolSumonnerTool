package com.legue.axel.lolsummonertool.database.model.champion


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "champion_infos", foreignKeys = [ForeignKey(entity = Champion::class, parentColumns = ["key"], childColumns = ["championId"], onDelete = CASCADE)])
data class ChampionInfo(
        @PrimaryKey(autoGenerate = true)
        val id: Int? = null,
        val attack: Int? = null,
        val defense: Int? = null,
        val magic: Int? = null,
        val difficulty: Int? = null,
        @ColumnInfo(index = true)
        var championId: Int = 0
)