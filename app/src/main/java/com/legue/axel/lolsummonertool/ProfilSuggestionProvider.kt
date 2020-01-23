package com.legue.axel.lolsummonertool

import android.content.SearchRecentSuggestionsProvider

class ProfilSuggestionProvider : SearchRecentSuggestionsProvider() {

    companion object {
        const val AUTHORITY = "com.legue.axel.lolsummonertool.ProfilSuggestionProvider"
        const val MODE = DATABASE_MODE_QUERIES or DATABASE_MODE_2LINES
    }

    init {
        setupSuggestions(AUTHORITY, MODE)
    }


}
