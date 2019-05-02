package com.legue.axel.lolsummonertool.network.response.match;

public class TeamBansDto {

    public int pickTurn;
    public int championId;

    public TeamBansDto() {
    }

    public TeamBansDto(int pickTurn, int championId) {
        this.pickTurn = pickTurn;
        this.championId = championId;
    }
}
