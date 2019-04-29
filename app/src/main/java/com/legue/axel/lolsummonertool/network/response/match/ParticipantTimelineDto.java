package com.legue.axel.lolsummonertool.network.response.match;

import java.util.HashMap;

public class ParticipantTimelineDto {

    public String lane; //(Legal values: MID, MIDDLE, TOP, JUNGLE, BOT, BOTTOM)
    public int participantId;
    public HashMap<String,Double> csDiffPerMinDeltas;
    public HashMap<String,Double> goldPerMinDeltas;
    public HashMap<String,Double> xpDiffPerMinDeltas;
    public HashMap<String,Double> creepsPerMinDeltas;
    public HashMap<String,Double> xpPerMinDeltas;
    public String role;
    public HashMap<String,Double> damageTakenDiffPerMinDeltas;
    public HashMap<String,Double> damageTakenPerMinDeltas;


    public ParticipantTimelineDto() {
    }

    public ParticipantTimelineDto(String lane, int participantId,
                                  HashMap<String, Double> csDiffPerMinDeltas,
                                  HashMap<String, Double> goldPerMinDeltas,
                                  HashMap<String, Double> xpDiffPerMinDeltas,
                                  HashMap<String, Double> creepsPerMinDeltas,
                                  HashMap<String, Double> xpPerMinDeltas,
                                  String role,
                                  HashMap<String, Double> damageTakenDiffPerMinDeltas,
                                  HashMap<String, Double> damageTakenPerMinDeltas) {
        this.lane = lane;
        this.participantId = participantId;
        this.csDiffPerMinDeltas = csDiffPerMinDeltas;
        this.goldPerMinDeltas = goldPerMinDeltas;
        this.xpDiffPerMinDeltas = xpDiffPerMinDeltas;
        this.creepsPerMinDeltas = creepsPerMinDeltas;
        this.xpPerMinDeltas = xpPerMinDeltas;
        this.role = role;
        this.damageTakenDiffPerMinDeltas = damageTakenDiffPerMinDeltas;
        this.damageTakenPerMinDeltas = damageTakenPerMinDeltas;
    }
}
