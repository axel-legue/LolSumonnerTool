package com.legue.axel.lolsummonertool.adapter;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;

import com.legue.axel.lolsummonertool.Constants;
import com.legue.axel.lolsummonertool.wiki.fragment.WikiChampionFragment;
import com.legue.axel.lolsummonertool.wiki.fragment.WikiItemFragment;
import com.legue.axel.lolsummonertool.wiki.fragment.WikiMasteryFragment;
import com.legue.axel.lolsummonertool.wiki.fragment.WikiSummonerSpellFragment;

public class WikiFragmentAdapter extends PagerAdapter {
    private final static String TAG = "WikiFragmentAdapter";

    FragmentManager fragmentManager;
    Fragment[] fragments;

    private static int NUM_ITEMS = 4;

    public WikiFragmentAdapter(FragmentManager fm) {
        fragmentManager = fm;
        fragments = new Fragment[NUM_ITEMS];
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        assert (0 <= position && position < fragments.length);
        FragmentTransaction trans = fragmentManager.beginTransaction();
        trans.remove(fragments[position]);
        trans.commit();
        fragments[position] = null;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Fragment fragment = getItem(position);
        FragmentTransaction trans = fragmentManager.beginTransaction();
        trans.add(container.getId(), fragment, "fragment:" + position);
        trans.commit();
        return fragment;
    }

    //    @Override
//    public Fragment getItem(int position) {
//        switch (position) {
//            case 0:
//                return WikiChampionFragment.newInstance(0, Constants.WIKI_PAGE_CHAMPIONS);
//            case 1:
//                Log.i(TAG, "getItem: 1");
//                return WikiItemFragment.newInstance(1, Constants.WIKI_PAGE_ITEMS);
//            case 2:
//                Log.i(TAG, "getItem: 2");
//                return WikiMasteryFragment.newInstance(2, Constants.WIKI_PAGE_MASTERY);
//            case 3:
//                Log.i(TAG, "getItem: 3");
//                return WikiSummonerSpellFragment.newInstance(3, Constants.WIKI_PAGE_SPELL);
//            default:
//                return null;
//        }
//    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object fragment) {
        return ((Fragment) fragment).getView() == view;
    }

    public Fragment getItem(int position) {
        assert (0 <= position && position < fragments.length);
        if (fragments[position] == null) {
            switch (position) {
                case 0:
                    fragments[position] = WikiChampionFragment.newInstance(0, Constants.WIKI_PAGE_CHAMPIONS);
                    break;
                case 1:
                    Log.i(TAG, "getItem: 1");
                    fragments[position] = WikiItemFragment.newInstance(1, Constants.WIKI_PAGE_ITEMS);
                    break;
                case 2:
                    Log.i(TAG, "getItem: 2");
                    fragments[position] = WikiMasteryFragment.newInstance(2, Constants.WIKI_PAGE_MASTERY);
                    break;
                case 3:
                    Log.i(TAG, "getItem: 3");
                    fragments[position] = WikiSummonerSpellFragment.newInstance(3, Constants.WIKI_PAGE_SPELL);
                    break;
                default:
                    return null;
            }
        }
        return fragments[position];
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
