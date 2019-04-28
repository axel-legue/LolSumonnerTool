package com.legue.axel.lolsummonertool.network.response.match;

import java.util.List;

public class MatcheResponse {

    public List<Match> matchList;

    public MatcheResponse() {
    }

    public MatcheResponse(List<Match> matchList) {
        this.matchList = matchList;
    }
}
