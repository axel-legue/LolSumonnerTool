package com.legue.axel.lolsummonertool.network.retrofit

object RetrofitConstants {

    // Content Delivery Network
    const val API_DRAGON_BASE_CDN = "https://ddragon.leagueoflegends.com/cdn/"

    const val API_ITEM_VERSION = "9.10.1"
    const val API_RUNE_VERSION = "7.23.1"
    const val API_MASTERY_VERSION = "7.23.1"
    const val API_SUMMONER_VERSION = "7.23.1"
    const val API_CHAMPION_VERSION = "10.2.1"
    const val API_SPELL_VERSION = "10.2.1"
    const val API_PASSIVE_VERSION = "10.2.1"
    const val API_PROFIL_ICON_VERSION = "10.2.1"
    const val API_MAP_VERSION = "10.2.1"
    const val API_LANGUAGE_VERSION = "10.2.1"
    const val API_STICKER_VERSION = "10.2.1"
    const val API_TYPE_DATA = "data"
    const val API_TYPE_IMAGE = "img"
    const val API_TYPE_CHAMPION = "champion"
    const val API_TYPE_ITEM = "item"
    const val API_TYPE_MASTERY = "mastery"
    const val API_TYPE_SPELL = "spell"
    const val API_TYPE_PASSIVE = "passive"
    const val LANGUAGE_KEY = "en_GB"

    const val API_HTTPS = "https://"
    const val API_RIOTGAMES_BASE = ".api.riotgames.com"
    const val API_SUMMONER_NAME_V4_BY_NAME = "/lol/summoner/v4/summoners/by-name/"
    const val API_MATCH_V4_BY_ENCRYTPED_ACCOUNT_ID = "/lol/match/v4/matchlists/by-account/"
    const val API_MATCH_V4_MATCH_ID = "/lol/match/v4/matches/"
    const val API_KEY_VALUE_LOL_SUMMONER_TOOL = "RGAPI-eb5aef1b-1501-409d-a9f4-ee50b5f192d1"

    const val API_KEY_PARAMETER = "api_key"
    const val API_KEY_BEGIN_INDEX = "beginIndex"
    const val API_KEY_END_INDEX = "endIndex"


    // Regex from Riot Developer : ^[0-9\\p{L} _\\.]+$
    const val REGEX_VALIDATION_NAME = "^[0-9\\p{L} _\\.]+$"

    // TODO: 23/05/2019 API for futur features
    // COMMUNITY DRAGON API
    //Runes reforged
    const val API_REFORGED_RUNES = "http://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perks.json"
    const val API_REFORGED_RUNES_ORDERED = "http://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perkstyles.json"
    const val API_COMMUNITY_DRAGON_BASE = "https://raw.communitydragon.org/9.6/game/assets/perks/styles/"


    // CHAMPION GG API
    const val API_CHAMPION_GG = "http://www.api.champion.gg/v2/champions?elo=SILVER&champData=kda,damage,averageGames,totalHeal,killingSpree,minions,gold,positions,normalized,groupedWins,trinkets,runes,firstitems,summoners,skills,finalitems,masteries,maxMins,matchups&limit=200&api_key=<API_KEY>"


    const val ACTION_ERROR = 200
    const val ACTION_COMPLETE = 201
    const val ACTION_GET_SUMMONER = 202
    const val ACTION_GET_SUMMONER_MACTHES = 203
    const val ACTION_GET_MATCH_INFORMATIONS = 204


    const val ERROR = "Error"


}
