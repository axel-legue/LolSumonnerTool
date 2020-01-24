package com.legue.axel.lolsummonertool.database.model.champion

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import com.legue.axel.lolsummonertool.database.converter.FloatListConverters

@TypeConverters(FloatListConverters::class)
@Entity(tableName = "vars", foreignKeys = [ForeignKey(entity = Spell::class, parentColumns = ["id"], childColumns = ["spellId"], onDelete = CASCADE)])
data class Var(
        @PrimaryKey(autoGenerate = true)
        val id: Int? = null,
        val link: String? = null,
        val coeff: List<Float>? = null,
        val key: String? = null,
        @ColumnInfo(index = true)
        val spellId: String
)