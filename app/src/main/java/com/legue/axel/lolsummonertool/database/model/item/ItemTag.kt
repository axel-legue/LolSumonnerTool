package com.legue.axel.lolsummonertool.database.model.item

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "item_tags", foreignKeys = [ForeignKey(entity = Item::class, parentColumns = ["id"], childColumns = ["itemId"], onDelete = CASCADE)])
data class ItemTag(
        @PrimaryKey(autoGenerate = true)
        val id: Int?= null,
        val tag: String? = null,
        @ColumnInfo(index = true)
        val itemId: Int = 0

)