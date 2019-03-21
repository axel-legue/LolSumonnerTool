package com.legue.axel.lolsummonertool.network.response.summonerspell;

import com.google.gson.annotations.SerializedName;

import java.util.LinkedHashMap;

public class SummonerSpellsResponse {

    public String type;
    public String version;
    @SerializedName("data")
    public LinkedHashMap<String, SummonerSpellDetailsResponse> summonerSpellList;

    public SummonerSpellsResponse() {
    }

    public SummonerSpellsResponse(String type, String version,
                                  LinkedHashMap<String, SummonerSpellDetailsResponse> summonerSpellList) {
        this.type = type;
        this.version = version;
        this.summonerSpellList = summonerSpellList;
    }
}
