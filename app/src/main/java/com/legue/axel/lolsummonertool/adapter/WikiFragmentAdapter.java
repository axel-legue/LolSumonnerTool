package com.legue.axel.lolsummonertool.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.legue.axel.lolsummonertool.utils.Constants;
import com.legue.axel.lolsummonertool.wiki.BlankFragment;
import com.legue.axel.lolsummonertool.wiki.WikiItemFragment;
import com.legue.axel.lolsummonertool.wiki.WikiChampionFragment;

public class WikiFragmentAdapter extends FragmentStatePagerAdapter {
    private final static String TAG = "WikiFragmentAdapter";

    private static int NUM_ITEMS = 4;


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
                return BlankFragment.newInstance("", Constants.WIKI_PAGE_CHAMPIONS);
            case 3:
                Log.i(TAG, "getItem: 3");
                return BlankFragment.newInstance("", Constants.WIKI_PAGE_CHAMPIONS);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
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
                Log.i(TAG, "getItem: 2");
                return Constants.WIKI_PAGE_SPELL;
            case 3:
                Log.i(TAG, "getItem: 3");
                return Constants.WIKI_PAGE_TREE;
            default:
                return null;
        }
    }
}
