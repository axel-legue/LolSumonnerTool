package com.legue.axel.lolsummonertool.database.model.champion

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

//@Entity(tableName = "passive_images",
//        foreignKeys = {
//                @ForeignKey(
//                        entity = Passive.class,
//                        parentColumns = "id",
//                        childColumns = "passiveId",
//                        onDelete = CASCADE
//                )}
//)
@Deprecated("")
data class PassiveImage(
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
        val passiveId: Int? = 0
)