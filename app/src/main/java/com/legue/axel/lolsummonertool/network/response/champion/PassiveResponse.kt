package com.legue.axel.lolsummonertool.network.response.champion

import com.legue.axel.lolsummonertool.database.model.champion.PassiveImage

data class PassiveResponse(
        var name: String,
        var description: String,
        var image: PassiveImage
)
