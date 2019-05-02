package com.legue.axel.lolsummonertool.network.response.match;

public class MasteryDto {
    public int masteryId;
    public int rank;

    public MasteryDto() {
    }

    public MasteryDto(int masteryId, int rank) {
        this.masteryId = masteryId;
        this.rank = rank;
    }
}
