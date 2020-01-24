package com.legue.axel.lolsummonertool.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import android.util.Log

import com.legue.axel.lolsummonertool.Constants
import com.legue.axel.lolsummonertool.wiki.fragment.WikiChampionFragment
import com.legue.axel.lolsummonertool.wiki.fragment.WikiItemFragment
import com.legue.axel.lolsummonertool.wiki.fragment.WikiMasteryFragment
import com.legue.axel.lolsummonertool.wiki.fragment.WikiSummonerSpellFragment

class WikiFragmentAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return WikiChampionFragment.newInstance(0, Constants.WIKI_PAGE_CHAMPIONS)
            1 -> {
                Log.i(TAG, "getItem: 1")
                return WikiItemFragment.newInstance(1, Constants.WIKI_PAGE_ITEMS)
            }
            2 -> {
                Log.i(TAG, "getItem: 2")
                return WikiMasteryFragment.newInstance(2, Constants.WIKI_PAGE_MASTERY)
            }
            3 -> {
                Log.i(TAG, "getItem: 3")
                return WikiSummonerSpellFragment.newInstance(3, Constants.WIKI_PAGE_SPELL)
            }
            else -> return null
        }
    }

    override fun getCount(): Int {
        return 4
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return Constants.WIKI_PAGE_CHAMPIONS
            1 -> {
                Log.i(TAG, "getItem: 1")
                return Constants.WIKI_PAGE_ITEMS
            }
            2 -> {
                Log.i(TAG, "getItem: 3")
                return Constants.WIKI_PAGE_MASTERY
            }
            3 -> {
                Log.i(TAG, "getItem: 2")
                return Constants.WIKI_PAGE_SPELL
            }
            else -> return null
        }
    }

    companion object {
        private const val TAG = "WikiFragmentAdapter"
    }
}
