package com.legue.axel.lolsummonertool.database.model.champion

import androidx.room.Entity
import androidx.room.PrimaryKey

// TODO add Relation between item and block
// List<Item> itemList; // (item should contain [id(string) / count(int) / hideCount(bool)]

@Entity(tableName = "blocks")
data class Block(
        @PrimaryKey(autoGenerate = true)
        var id: Int = 0,
        var type: String? = null,
        var recMath: Boolean = false,
        var recSteps: Boolean = false,
        var minSummonerLevel: Int = 0,
        var maxSummonerLevel: Int = 0,
        var showIfSummonerSpell: String? = null,
        var hideIfSummonerSpell: String? = null,
        var appendAfterSection: String? = null
)
