package com.legue.axel.lolsummonertool.database.model.item

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.legue.axel.lolsummonertool.database.converter.StringListConverters

@TypeConverters(StringListConverters::class)
@Entity(tableName = "items")
data class Item(
        @PrimaryKey
        val id: Int = 0,
        val name: String? = null,
        val description: String? = null,
        val colloq: String? = null,
        val plaintext: String? = null,
        val depth: Int? = null,
        val into: List<String>? = null,
        val from: List<String>? = null
)