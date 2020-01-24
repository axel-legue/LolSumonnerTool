package com.legue.axel.lolsummonertool.database.model.champion

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.legue.axel.lolsummonertool.database.converter.StringListConverters
import io.reactivex.annotations.NonNull

//TODO :Add recommended Object and relation if enough time.

@TypeConverters(StringListConverters::class)
@Entity(tableName = "champions")
data class Champion(
        @PrimaryKey
        @NonNull
        var key: Int = 0,
        var id: String? = null,
        var name: String? = null,
        var title: String? = null,
        var lore: String? = null,
        var blurb: String? = null,
        var tags: List<String>? = null,
        var enemytips: List<String>? = null,
        var allytips: List<String>? = null,
        var partype: String? = null
)