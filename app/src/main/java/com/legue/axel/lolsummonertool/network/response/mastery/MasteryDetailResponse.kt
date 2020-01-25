package com.legue.axel.lolsummonertool.network.response.mastery

import com.legue.axel.lolsummonertool.database.model.mastery.MasteryImage

class MasteryDetailResponse(
        var id: Int,
        var name: String,
        var description: List<String>,
        var image: MasteryImage,
        var ranks: Int,
        var prereq: String
)