package com.legue.axel.lolsummonertool.database.model.item


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "item_images", foreignKeys = [ForeignKey(entity = Item::class, parentColumns = ["id"], childColumns = ["itemId"], onDelete = CASCADE)])
data class ItemImage(
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
        val itemId: Int = 0
)