package com.legue.axel.lolsummonertool.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.legue.axel.lolsummonertool.utils.Constants;
import com.legue.axel.lolsummonertool.wiki.WikiChampionFragment;

public class WikiFragmentAdapter extends FragmentPagerAdapter {

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
                return null;
            case 2:
                return null;
            case 3:
                return null;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }


}
