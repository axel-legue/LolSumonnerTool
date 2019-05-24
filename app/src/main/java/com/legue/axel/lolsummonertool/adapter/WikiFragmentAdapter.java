package com.legue.axel.lolsummonertool.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.legue.axel.lolsummonertool.Constants;
import com.legue.axel.lolsummonertool.wiki.fragment.WikiChampionFragment;
import com.legue.axel.lolsummonertool.wiki.fragment.WikiItemFragment;
import com.legue.axel.lolsummonertool.wiki.fragment.WikiMasteryFragment;
import com.legue.axel.lolsummonertool.wiki.fragment.WikiSummonerSpellFragment;

public class WikiFragmentAdapter extends FragmentStatePagerAdapter {
    private final static String TAG = "WikiFragmentAdapter";

    public WikiFragmentAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return WikiChampionFragment.newInstance(0, Constants.WIKI_PAGE_CHAMPIONS);
            case 1:
                Log.i(TAG, "getItem: 1");
                return WikiItemFragment.newInstance(1, Constants.WIKI_PAGE_ITEMS);
            case 2:
                Log.i(TAG, "getItem: 2");
                return WikiMasteryFragment.newInstance(2, Constants.WIKI_PAGE_MASTERY);
            case 3:
                Log.i(TAG, "getItem: 3");
                return WikiSummonerSpellFragment.newInstance(3, Constants.WIKI_PAGE_SPELL);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        int NUM_ITEMS = 4;
        return NUM_ITEMS;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return Constants.WIKI_PAGE_CHAMPIONS;
            case 1:
                Log.i(TAG, "getItem: 1");
                return Constants.WIKI_PAGE_ITEMS;
            case 2:
                Log.i(TAG, "getItem: 3");
                return Constants.WIKI_PAGE_MASTERY;
            case 3:
                Log.i(TAG, "getItem: 2");
                return Constants.WIKI_PAGE_SPELL;
            default:
                return null;
        }
    }
}
