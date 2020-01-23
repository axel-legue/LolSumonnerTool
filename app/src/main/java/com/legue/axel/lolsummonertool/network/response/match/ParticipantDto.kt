package com.legue.axel.lolsummonertool.network.response.match

data class ParticipantDto(
        var stats: ParticipantStatsDto? = null,
        var participantId: Int = 0,
        var runes: List<RuneDto>? = null, // List of legacy Rune information. Not included for matches played with Runes Reforged.
        var timeline: ParticipantTimelineDto? = null,
        var teamId: Int = 0,
        var spell2Id: Int = 0,//Second Summoner Spell id.
        var masteries: List<MasteryDto>? = null, //List of legacy Mastery information. Not included for matches played with Runes Reforged.
        var highestAchievedSeasonTier: String? = null, // Legal values: CHALLENGER, MASTER, DIAMOND, PLATINUM, GOLD, SILVER, BRONZE, UNRANKED.
        var spell1Id: Int = 0,
        var championId: Int = 0
)