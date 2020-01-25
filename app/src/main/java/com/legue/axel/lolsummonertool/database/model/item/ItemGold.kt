package com.legue.axel.lolsummonertool.database.model.item


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "item_golds", foreignKeys = [ForeignKey(entity = Item::class, parentColumns = ["id"], childColumns = ["itemId"], onDelete = CASCADE)])
data class ItemGold(
        @PrimaryKey(autoGenerate = true)
        val id: Int? = null,
        val base: Int? = null,
        val total: Int? = null,
        val sell: Int? = null,
        val purchasable: Boolean = false,
        @ColumnInfo(index = true)
        val itemId: Int? = null
)