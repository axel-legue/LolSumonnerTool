package com.legue.axel.lolsummonertool.network.response.match

data class MatchlistDto(
        var matches: List<MatchReferenceDto>? = null,
        var totalGames: Int = 0,
        var startIndex: Int = 0,
        var endIndex: Int = 0)
