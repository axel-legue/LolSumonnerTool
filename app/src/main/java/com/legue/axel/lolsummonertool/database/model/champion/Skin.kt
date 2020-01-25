package com.legue.axel.lolsummonertool.database.model.champion

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey
import io.reactivex.annotations.NonNull

@Entity(tableName = "skins", foreignKeys = [ForeignKey(entity = Champion::class, parentColumns = ["key"], childColumns = ["championId"], onDelete = CASCADE)], indices = [Index("championId")])
data class Skin(
        @PrimaryKey
        @NonNull
        val id: Int,
        val num: Int? = null,
        val name: String? = null,
        val chromas: Boolean? = null,
        val championId: Int? = null
)