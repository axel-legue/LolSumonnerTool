package com.legue.axel.lolsummonertool.network.response.match

data class PlayerDto(
        var currentPlatformId: String? = null,
        var summonerName: String? = null,
        var matchHistoryUri: String? = null,
        var platformId: String? = null,
        var currentAccountId: String? = null,
        var profileIcon: Int = 0,
        var summonerId: String? = null,
        var accountId: String? = null
)