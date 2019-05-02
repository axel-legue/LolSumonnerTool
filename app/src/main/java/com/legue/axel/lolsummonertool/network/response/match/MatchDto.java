package com.legue.axel.lolsummonertool.network.response.match;

import java.util.List;

public class MatchDto {

    public int seasonId;
    public int queueId;
    public long gameId;
    public List<ParticipantIdentityDto> participantIdentities;
    public String gameVersion;
    public String platformId;
    public String gameMode;
    public int mapId;
    public String gameType;
    List<TeamStatsDto> teams;
    List<ParticipantDto> participants;
    public long gameDuration;
    public long gameCreation;

}
