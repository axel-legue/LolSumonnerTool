package com.legue.axel.lolsummonertool.database.model

import com.legue.axel.lolsummonertool.database.model.item.ItemGold

data class Basic(
        var name: String? = null,
        var rune: Rune? = null,
        var itemGold: ItemGold? = null,
        var group: String? = null,
        var description: String? = null,
        var colloq: String? = null,
        var plaintext: String? = null,
        var consumed: Boolean = false,
        var stacks: Int = 0,
        var depths: Int = 0,
        var consumeOnFull: Boolean = false,
        var from: List<Int>? = null,
        var into: List<Int>? = null,
        var specialRecipe: Int = 0,
        var inStore: Boolean = false,
        var hideFromALl: Boolean = false,
        var requiredChampion: String? = null,
        var tags: List<String>? = null
)