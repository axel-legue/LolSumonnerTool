package com.legue.axel.lolsummonertool.database.model.mastery

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.legue.axel.lolsummonertool.database.converter.StringListConverters

@TypeConverters(StringListConverters::class)
@Entity(tableName = "masteries")
data class Mastery(
        @PrimaryKey
        val id: Int,
        val name: String? = null,
        val description: List<String>? = null,
        val ranks: Int? = null,
        val prereq: String? = null
)