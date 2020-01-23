package com.legue.axel.lolsummonertool.database.model.champion

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "spell_images", foreignKeys = [ForeignKey(entity = Spell::class, parentColumns = ["id"], childColumns = ["spellId"], onDelete = CASCADE)])
data class SpellImage(
        @PrimaryKey(autoGenerate = true) var id: Int? = null,
        var full: String,
        var sprite: String,
        var group: String,
        var x: Int,
        var y: Int,
        var w: Int,
        var h: Int,
        var spellId: String
)