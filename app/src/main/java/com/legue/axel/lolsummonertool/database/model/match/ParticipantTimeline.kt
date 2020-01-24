package com.legue.axel.lolsummonertool.database.model.match

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import com.legue.axel.lolsummonertool.database.converter.DoubleMapConverters

@TypeConverters(DoubleMapConverters::class)
@Entity(tableName = "participantTimelines", foreignKeys = [ForeignKey(entity = Participant::class, parentColumns = ["id"], childColumns = ["participantKey"], onDelete = CASCADE)])
data class ParticipantTimeline(
        @PrimaryKey(autoGenerate = true)
        val id: Int? = null,
        val lane: String,//(Legal values: MID, MIDDLE, TOP, JUNGLE, BOT, BOTTOM)
        val participantId: Int? = null,
        val csDiffPerMinDeltas: Map<String, Double>? = null,
        val goldPerMinDeltas: Map<String, Double>? = null,
        val xpDiffPerMinDeltas: Map<String, Double>? = null,
        val creepsPerMinDeltas: Map<String, Double>? = null,
        val xpPerMinDeltas: Map<String, Double>? = null,
        val role: String? = null,
        val damageTakenDiffPerMinDeltas: Map<String, Double>? = null,
        val damageTakenPerMinDeltas: Map<String, Double>? = null,
        @ColumnInfo(index = true)
        var participantKey: Int? = null
)