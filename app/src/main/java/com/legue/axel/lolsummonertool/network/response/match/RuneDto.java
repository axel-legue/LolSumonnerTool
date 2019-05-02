package com.legue.axel.lolsummonertool.network.response.match;

public class RuneDto {

    public int runeId;
    public int rank;

    public RuneDto() {
    }

    public RuneDto(int runeId, int rank) {
        this.runeId = runeId;
        this.rank = rank;
    }
}
