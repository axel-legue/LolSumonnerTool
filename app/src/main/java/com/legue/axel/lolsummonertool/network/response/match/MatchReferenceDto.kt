package com.legue.axel.lolsummonertool.network.response.match


data class MatchReferenceDto(
        var gameId: Long? = 0,
        var lane: String? = null,
        var champion: Int? = 0,
        var platformId: String? = null,
        var timestamp: Long? = 0,
        var queue: Int? = 0,
        var role: String? = null,
        var season: Int? = 0
)