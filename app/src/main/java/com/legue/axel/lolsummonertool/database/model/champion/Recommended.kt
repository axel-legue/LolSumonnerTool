package com.legue.axel.lolsummonertool.database.model.champion

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "recommended", foreignKeys = [ForeignKey(entity = Champion::class, parentColumns = ["key"], childColumns = ["championId"], onDelete = CASCADE)], indices = [Index("championId")])
data class Recommended(
        @PrimaryKey(autoGenerate = true)
        val id: Int? = null,
        val champion: String? = null,
        val title: String? = null,
        val map: String? = null,
        val mode: String? = null,
        val type: String? = null,
        val customTag: String? = null,
        @SerializedName("sortrank")
        val sortRank: Int? = null,
        val extensionPage: Boolean? = null,
        val championId: Int = 0
)