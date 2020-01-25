package com.legue.axel.lolsummonertool.database.model.champion

import androidx.room.Entity
import androidx.room.PrimaryKey

// TODO add Relation between item and block
// List<Item> itemList; // (item should contain [id(string) / count(int) / hideCount(bool)]

@Entity(tableName = "blocks")
data class Block(
        @PrimaryKey(autoGenerate = true)
        val id: Int = 0,
        val type: String? = null,
        val recMath: Boolean = false,
        val recSteps: Boolean = false,
        val minSummonerLevel: Int = 0,
        val maxSummonerLevel: Int = 0,
        val showIfSummonerSpell: String? = null,
        val hideIfSummonerSpell: String? = null,
        val appendAfterSection: String? = null
)
