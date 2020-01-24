package com.legue.axel.lolsummonertool.database.model.champion

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "images", foreignKeys = [ForeignKey(entity = Champion::class, parentColumns = ["key"], childColumns = ["championId"], onDelete = CASCADE)])
data class ChampionImage(
        @PrimaryKey(autoGenerate = true)
        val id: Int? = null,
        val full: String? = null,
        val sprite: String? = null,
        val group: String? = null,
        val x: Int? = null,
        val y: Int? = null,
        val w: Int? = null,
        val h: Int? = null,
        @ColumnInfo(index = true)
        var championId: Int = 0
//    @ColumnInfo(index = true)
//    public int passiveId;

)

