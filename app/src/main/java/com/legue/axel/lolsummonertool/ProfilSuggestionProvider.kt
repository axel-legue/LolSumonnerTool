package com.legue.axel.lolsummonertool

import android.content.SearchRecentSuggestionsProvider

class ProfilSuggestionProvider : SearchRecentSuggestionsProvider() {
    init {
        setupSuggestions(AUTHORITY, MODE)
    }

    companion object {
        val AUTHORITY = "com.legue.axel.lolsummonertool.ProfilSuggestionProvider"
        val MODE = DATABASE_MODE_QUERIES or DATABASE_MODE_2LINES
    }
}
