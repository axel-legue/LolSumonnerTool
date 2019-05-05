package com.legue.axel.lolsummonertool.database.model.match;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.legue.axel.lolsummonertool.database.converter.DoubleMapConverters;


import java.util.Map;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "participantTimelines",
        foreignKeys = @ForeignKey(
                entity = Participant.class,
                parentColumns = "id",
                childColumns = "participantKey",
                onDelete = CASCADE
        ))
public class ParticipantTimeline {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String lane; //(Legal values: MID, MIDDLE, TOP, JUNGLE, BOT, BOTTOM)
    public int participantId;
    @TypeConverters(DoubleMapConverters.class)
    public Map<String, Double> csDiffPerMinDeltas;
    @TypeConverters(DoubleMapConverters.class)
    public Map<String, Double> goldPerMinDeltas;
    @TypeConverters(DoubleMapConverters.class)
    public Map<String, Double> xpDiffPerMinDeltas;
    @TypeConverters(DoubleMapConverters.class)
    public Map<String, Double> creepsPerMinDeltas;
    @TypeConverters(DoubleMapConverters.class)
    public Map<String, Double> xpPerMinDeltas;
    public String role;
    @TypeConverters(DoubleMapConverters.class)
    public Map<String, Double> damageTakenDiffPerMinDeltas;
    @TypeConverters(DoubleMapConverters.class)
    public Map<String, Double> damageTakenPerMinDeltas;

    @ColumnInfo(index = true)
    public int participantKey;

    @Ignore
    public ParticipantTimeline() {
    }

    public ParticipantTimeline(int id, String lane,
                               int participantId,
                               Map<String, Double> csDiffPerMinDeltas,
                               Map<String, Double> goldPerMinDeltas,
                               Map<String, Double> xpDiffPerMinDeltas,
                               Map<String, Double> creepsPerMinDeltas,
                               Map<String, Double> xpPerMinDeltas,
                               String role,
                               Map<String, Double> damageTakenDiffPerMinDeltas,
                               Map<String, Double> damageTakenPerMinDeltas,
                               int participantKey) {
        this.id = id;
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
        this.participantKey = participantKey;
    }
}
