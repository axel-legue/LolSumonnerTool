package com.legue.axel.lolsummonertool.network;

import com.google.gson.annotations.SerializedName;

import java.util.LinkedHashMap;

public class ChampionsResponse {

    private String type;
    private String format;
    private String version;
    @SerializedName("data")
    private LinkedHashMap<String, ChampionDetailResponse> championList;

    public ChampionsResponse(String type, String format, String version, LinkedHashMap<String, ChampionDetailResponse> championList) {
        this.type = type;
        this.format = format;
        this.version = version;
        this.championList = championList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public LinkedHashMap<String, ChampionDetailResponse> getChampionList() {
        return championList;
    }

    public void setChampionList(LinkedHashMap<String, ChampionDetailResponse> championList) {
        this.championList = championList;
    }
}
