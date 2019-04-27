package com.legue.axel.lolsummonertool;

import android.content.SearchRecentSuggestionsProvider;

public class ProfilSuggestionProvider extends SearchRecentSuggestionsProvider {
    public final static String AUTHORITY = "com.legue.axel.lolsummonertool.ProfilSuggestionProvider";
    public final static int MODE = DATABASE_MODE_QUERIES | DATABASE_MODE_2LINES;

    public ProfilSuggestionProvider() {
        setupSuggestions(AUTHORITY, MODE);
    }
}
