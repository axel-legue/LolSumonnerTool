package com.legue.axel.lolsummonertool.database.model.champion

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "spell_images", foreignKeys = [ForeignKey(entity = Spell::class, parentColumns = ["id"], childColumns = ["spellId"], onDelete = CASCADE)])
data class SpellImage(
        @PrimaryKey(autoGenerate = true) var id: Int? = null,
        val full: String,
        val sprite: String,
        val group: String,
        val x: Int,
        val y: Int,
        val w: Int,
        val h: Int,
        val spellId: String
)