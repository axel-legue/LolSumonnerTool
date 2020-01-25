package com.legue.axel.lolsummonertool.database.model.champion

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "passives", foreignKeys = [ForeignKey(entity = Champion::class, parentColumns = ["key"], childColumns = ["championId"], onDelete = CASCADE)])
data class Passive(
        @PrimaryKey(autoGenerate = true)
        val id: Int? = null,
        val name: String? = null,
        val description: String? = null,
        val image: String? = null,
        @ColumnInfo(index = true)
        val championId: Int = 0
)