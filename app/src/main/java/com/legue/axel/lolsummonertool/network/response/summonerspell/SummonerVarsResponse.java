package com.legue.axel.lolsummonertool.network.response.summonerspell;

public class SummonerVarsResponse {
    public String link;
    public int coeff;
    public String key;

    public SummonerVarsResponse() {
    }

    public SummonerVarsResponse(String link, int coeff, String key) {
        this.link = link;
        this.coeff = coeff;
        this.key = key;
    }
}
