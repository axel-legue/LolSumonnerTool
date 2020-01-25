package com.legue.axel.lolsummonertool.database.model.mastery

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "mastery_images", foreignKeys = [ForeignKey(entity = Mastery::class, parentColumns = ["id"], childColumns = ["masteryId"], onDelete = CASCADE)])
data class MasteryImage(
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
        val masteryId: Int = 0
)