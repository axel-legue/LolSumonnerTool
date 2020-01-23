package com.legue.axel.lolsummonertool.database.model.champion

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import com.legue.axel.lolsummonertool.database.converter.StringListConverters

@Entity(tableName = "level_tip", foreignKeys = [ForeignKey(entity = Spell::class, parentColumns = ["id"], childColumns = ["spellId"], onDelete = CASCADE)])
@TypeConverters(StringListConverters::class)
data class LevelTip(
        @PrimaryKey(autoGenerate = true)
        var id: Int? = null,
        var label: List<String>? = null,
        var effect: List<String>? = null,
        @ColumnInfo(index = true)
        var spellId: String
)