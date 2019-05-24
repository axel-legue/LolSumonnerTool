package com.legue.axel.lolsummonertool.network.response.mastery;

import com.google.gson.annotations.SerializedName;

import java.util.LinkedHashMap;

public class MasteryResponse {
    public String type;
    public String version;
    @SerializedName("data")
    public LinkedHashMap<String, MasteryDetailResponse> masteryDetailResponse;

    public MasteryResponse() {
    }

    public MasteryResponse(String type, String version, LinkedHashMap<String, MasteryDetailResponse> masteryDetailResponse) {
        this.type = type;
        this.version = version;
        this.masteryDetailResponse = masteryDetailResponse;
    }


}
