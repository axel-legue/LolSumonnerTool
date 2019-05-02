package com.legue.axel.lolsummonertool.network.response.match;

public class PlayerDto {
    public String currentPlatformId;
    public String summonerName;
    public String matchHistoryUri;
    public String platformId;
    public String currentAccountId;
    public int profileIcon;
    public String summonerId;
    public String accountId;

    public PlayerDto() {
    }

    public PlayerDto(String currentPlatformId, String summonerName, String matchHistoryUri, String platformId, String currentAccountId, int profileIcon, String summonerId, String accountId) {
        this.currentPlatformId = currentPlatformId;
        this.summonerName = summonerName;
        this.matchHistoryUri = matchHistoryUri;
        this.platformId = platformId;
        this.currentAccountId = currentAccountId;
        this.profileIcon = profileIcon;
        this.summonerId = summonerId;
        this.accountId = accountId;
    }
}
