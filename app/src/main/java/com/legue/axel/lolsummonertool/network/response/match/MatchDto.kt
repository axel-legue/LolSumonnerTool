package com.legue.axel.lolsummonertool.network.response.match

data class MatchDto(
        var seasonId: Int = 0,
        var queueId: Int = 0,
        var gameId: Long = 0,
        var participantIdentities: List<ParticipantIdentityDto>? = null,
        var gameVersion: String? = null,
        var platformId: String? = null,
        var gameMode: String? = null,
        var mapId: Int = 0,
        var gameType: String? = null,
        var teams: List<TeamStatsDto>? = null,
        var participants: List<ParticipantDto>? = null,
        var gameDuration: Long = 0,
        var gameCreation: Long = 0
)
