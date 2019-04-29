package com.legue.axel.lolsummonertool.network.response.match;

import java.util.List;

public class MatchlistDto {

    public List<MatchReferenceDto> matches;
    public int totalGames;
    public int startIndex;
    public int endIndex;

    public MatchlistDto() {
    }


    public MatchlistDto(List<MatchReferenceDto> matches, int totalGames, int startIndex, int endIndex) {
        this.matches = matches;
        this.totalGames = totalGames;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }
}
