package com.legue.axel.lolsummonertool.network.response.match;


public class MatchReferenceDto {

    public long gameId;
    public String lane;
    public int champion;
    public String platformId;
    public long timestamp;
    public int queue;
    public String role;
    public int season;

    public MatchReferenceDto() {
    }

    public MatchReferenceDto(long gameId, String lane, int champion, String platformId, long timestamp, int queue, String role, int season) {
        this.gameId = gameId;
        this.lane = lane;
        this.champion = champion;
        this.platformId = platformId;
        this.timestamp = timestamp;
        this.queue = queue;
        this.role = role;
        this.season = season;
    }
}
