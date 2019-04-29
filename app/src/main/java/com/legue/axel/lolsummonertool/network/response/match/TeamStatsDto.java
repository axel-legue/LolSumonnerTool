package com.legue.axel.lolsummonertool.network.response.match;

import java.util.List;

public class TeamStatsDto {

    public boolean firstDragon;
    public boolean firstInhibitor;
    public List<TeamBansDto> bans;
    public int baronKills;
    public boolean firstRiftHerald;
    public boolean firstBaron;
    public int riftHeraldKills;
    public boolean firstBlood;
    public int teamId; // 100 for blue side - 200 for red side
    public boolean firstTower;
    public int vilemawKills;
    public int inhibitorKills;
    public int towerKills;
    public int dominionVictoryScore;
    public String win; // String indicating whether or not the team won. There are only two values visibile in public Match history. (Legal values: Fail, Win)
    public int dragonKills;

    public TeamStatsDto() {
    }

    public TeamStatsDto(boolean firstDragon, boolean firstInhibitor, List<TeamBansDto> bans, int baronKills, boolean firstRiftHerald, boolean firstBaron, int riftHeraldKills, boolean firstBlood, int teamId, boolean firstTower, int vilemawKills, int inhibitorKills, int towerKills, int dominionVictoryScore, String win, int dragonKills) {
        this.firstDragon = firstDragon;
        this.firstInhibitor = firstInhibitor;
        this.bans = bans;
        this.baronKills = baronKills;
        this.firstRiftHerald = firstRiftHerald;
        this.firstBaron = firstBaron;
        this.riftHeraldKills = riftHeraldKills;
        this.firstBlood = firstBlood;
        this.teamId = teamId;
        this.firstTower = firstTower;
        this.vilemawKills = vilemawKills;
        this.inhibitorKills = inhibitorKills;
        this.towerKills = towerKills;
        this.dominionVictoryScore = dominionVictoryScore;
        this.win = win;
        this.dragonKills = dragonKills;
    }
}
