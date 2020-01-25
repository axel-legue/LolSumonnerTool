package com.legue.axel.lolsummonertool.network.response.champion

import com.legue.axel.lolsummonertool.database.model.champion.SpellImage

data class SpellResponse(
        var id: String,
        var name: String,
        var description: String,
        var toolTip: String,
        var levelTip: LevelTipResponse? = null,
        var maxRank: Int,
        var cooldown: List<Float>,
        var cooldownBurn: String,
        var cost: List<Float>,
        var costBurn: String,
        var effect: List<List<Float>>,
        var effectBurn: List<String>,
        /*       val vars: List<Var>,*/
        var costType: String,
        var maxAmmo: String,
        var range: List<Float>,
        var rangeBurn: String,
        var resource: String,
        var image: SpellImage
)
