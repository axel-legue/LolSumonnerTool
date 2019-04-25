package com.legue.axel.lolsummonertool.network.retrofit;

import java.util.HashMap;

public class RetrofitConstants {

    // Content Delivery Network
    public static final String API_DRAGON_BASE_CDN = "https://ddragon.leagueoflegends.com/cdn/";

    public static final String API_ITEM_VERSION = "9.6.1";
    public static final String API_RUNE_VERSION = "7.23.1";
    public static final String API_MASTERY_VERSION = "7.23.1";
    public static final String API_SUMMONER_VERSION = "9.6.1";
    public static final String API_CHAMPION_VERSION = "9.6.1";
    public static final String API_SPELL_VERSION = "9.6.1";
    public static final String API_PASSIVE_VERSION = "9.6.1";
    public static final String API_PROFIL_ICON_VERSION = "9.6.1";
    public static final String API_MAP_VERSION = "9.6.1";
    public static final String API_LANGUAGE_VERSION = "9.6.1";
    public static final String API_STICKER_VERSION = "9.6.1";
    public static final String API_TYPE_DATA = "data";
    public static final String API_TYPE_IMAGE = "img";
    public static final String API_TYPE_CHAMPION = "champion";
    public static final String API_TYPE_ITEM = "item";
    public static final String API_TYPE_MASTERY = "mastery";
    public static final String API_TYPE_SPELL = "spell";
    public static final String API_TYPE_PASSIVE = "passive";
    public static final String LANGUAGE_KEY = "en_GB";


    public static final HashMap<String, String> API_SERVICE_PROXIES = new HashMap<String, String>() {{
        put("NA1", "North America");
        put("EUW1", "Europe West");
        put("BR1", "Brasil");
        put("EUN1", "Europe Nordic & East");
        put("JP1", "Japan");
        put("KR", "Korea");
        put("LA1", "LAN");
        put("LA2", "LAS");
        put("OC1", "Oceania");
        put("TR1", "Turkey");
        put("RU", "Russia");
        put("PBE1", "Public Beta");
    }};
    public static final String API_HTTPS = "https://";
    public static final String API_RIOTGAMES_BASE = ".api.riotgames.com";
    public static final String API_SUMMONER_NAME_V4_BY_NAME = "/lol/summoner/v4/summoners/by-name/";
    public static final String API_SUMMONER_NAME_V4_BY_ACCOUNT = "/lol/summoner/v4/summoners/by-account/";
    public static final String API_SUMMONER_NAME_V4_BY_PUUID = "/lol/summoner/v4/summoners/by-puuid/";
    public static final String API_SUMMONER_NAME_V4_ENCRYPTED = "/lol/summoner/v4/summoners/";
    public static final String API_KEY_PARAMETER = "api_key";


    // Regex from Riot Developer : ^[0-9\\p{L} _\\.]+$
    public static final String REGEX_VALIDATION_NAME = "^[0-9\\\\p{L} _\\\\.]+$";


    // COMMUNITY DRAGON API
    //Runes reforged
    public static final String API_REFORGED_RUNES = "http://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perks.json";
    public static final String API_REFORGED_RUNES_ORDERED = "http://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/perkstyles.json";
    public static final String API_COMMUNITY_DRAGON_BASE = "https://raw.communitydragon.org/9.6/game/assets/perks/styles/";


    // CHAMPION GG API
    public static final String API_CHAMPION_GG = "http://www.api.champion.gg/v2/champions?elo=SILVER&champData=kda,damage,averageGames,totalHeal,killingSpree,minions,gold,positions,normalized,groupedWins,trinkets,runes,firstitems,summoners,skills,finalitems,masteries,maxMins,matchups&limit=200&api_key=<API_KEY>";


    public static final int ACTION_ERROR = 200;
    public static final int ACTION_COMPLETE = 201;


    public static final String ERROR = "Error";


}
