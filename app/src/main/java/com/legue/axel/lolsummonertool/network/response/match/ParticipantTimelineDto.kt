package com.legue.axel.lolsummonertool.network.response.match

import java.util.*

data class ParticipantTimelineDto(
        var lane: String? = null, //(Legal values: MID, MIDDLE, TOP, JUNGLE, BOT, BOTTOM)
        var participantId: Int = 0,
        var csDiffPerMinDeltas: HashMap<String, Double>? = null,
        var goldPerMinDeltas: HashMap<String, Double>? = null,
        var xpDiffPerMinDeltas: HashMap<String, Double>? = null,
        var creepsPerMinDeltas: HashMap<String, Double>? = null,
        var xpPerMinDeltas: HashMap<String, Double>? = null,
        var role: String? = null,
        var damageTakenDiffPerMinDeltas: HashMap<String, Double>? = null,
        var damageTakenPerMinDeltas: HashMap<String, Double>? = null
)