package com.legue.axel.lolsummonertool.network.response.match;

public class ParticipantIdentityDto {

    public PlayerDto player;
    public int participantId;

    public ParticipantIdentityDto() {
    }

    public ParticipantIdentityDto(PlayerDto player, int participantId) {
        this.player = player;
        this.participantId = participantId;
    }
}
