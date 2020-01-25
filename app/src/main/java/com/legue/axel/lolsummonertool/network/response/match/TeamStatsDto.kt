package com.legue.axel.lolsummonertool.network.response.match

data class TeamStatsDto(
        var firstDragon: Boolean = false,
        var firstInhibitor: Boolean = false,
        var bans: List<TeamBansDto>,
        var baronKills: Int = 0,
        var firstRiftHerald: Boolean = false,
        var firstBaron: Boolean = false,
        var riftHeraldKills: Int = 0,
        var firstBlood: Boolean = false,
        var teamId: Int = 0, // 100 for blue side - 200 for red side
        var firstTower: Boolean = false,
        var vilemawKills: Int = 0,
        var inhibitorKills: Int = 0,
        var towerKills: Int = 0,
        var dominionVictoryScore: Int = 0,
        var win: String? = null, // String indicating whether or not the team won. There are only two values visible in public Match history. (Legal values: Fail, Win)
        var dragonKills: Int = 0
)