package com.legue.axel.lolsummonertool.database.model.item

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "item_stats", foreignKeys = [ForeignKey(entity = Item::class, parentColumns = ["id"], childColumns = ["itemId"], onDelete = CASCADE)])
data class ItemStat(
        @PrimaryKey(autoGenerate = true)
        val id: Int? = null,
        val key: String? = null,
        val value: Float = 0.toFloat(),
        @ColumnInfo(index = true)
        val itemId: Int = 0
)