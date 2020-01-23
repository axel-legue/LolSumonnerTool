package com.legue.axel.lolsummonertool.network.response.match

data class ParticipantIdentityDto(
        var player: PlayerDto? = null,
        var participantId: Int = 0
)
