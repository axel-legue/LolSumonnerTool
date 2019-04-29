package com.legue.axel.lolsummonertool.network.response.match;

import java.util.List;

public class ParticipantDto {
    public ParticipantStatsDto stats;
    public int participantId;
    public List<RuneDto> runes; // List of legacy Rune information. Not included for matches played with Runes Reforged.
    public ParticipantTimelineDto timeline;
    public int teamId;
    public int spell2Id; //Second Summoner Spell id.
    public List<MasteryDto> masteries; //List of legacy Mastery information. Not included for matches played with Runes Reforged.
    public String highestAchievedSeasonTier; // Legal values: CHALLENGER, MASTER, DIAMOND, PLATINUM, GOLD, SILVER, BRONZE, UNRANKED.
    public int spell1Id;
    public int championId;

    public ParticipantDto() {
    }

    public ParticipantDto(ParticipantStatsDto stats, int participantId, List<RuneDto> runes,
                          ParticipantTimelineDto timeline, int teamId, int spell2Id,
                          List<MasteryDto> masteries, String highestAchievedSeasonTier,
                          int spell1Id, int championId) {
        this.stats = stats;
        this.participantId = participantId;
        this.runes = runes;
        this.timeline = timeline;
        this.teamId = teamId;
        this.spell2Id = spell2Id;
        this.masteries = masteries;
        this.highestAchievedSeasonTier = highestAchievedSeasonTier;
        this.spell1Id = spell1Id;
        this.championId = championId;
    }
}
