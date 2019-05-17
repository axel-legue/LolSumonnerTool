package com.legue.axel.lolsummonertool.wiki.fragment;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.legue.axel.lolsummonertool.R;
import com.legue.axel.lolsummonertool.SuperApplication;
import com.legue.axel.lolsummonertool.adapter.WikiFragmentAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;


public class WikiFragment extends Fragment {

    private final static String TAG = WikiFragment.class.getName();
    private SuperApplication application;

    @BindView(R.id.vp_wiki)
    ViewPager vpWiki;
    @BindView(R.id.tl_wiki)
    TabLayout tlWiki;

    private WikiFragmentAdapter mWikiFragmentAdapter;

    public WikiFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wiki_main, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //pbLoading.setVisibility(View.GONE);

        //TODO : testing purpose => update code and move it at a better place
        application = (SuperApplication) getActivity().getApplication();

        mWikiFragmentAdapter = new WikiFragmentAdapter(getChildFragmentManager());
        vpWiki.setAdapter(mWikiFragmentAdapter);
        tlWiki.setupWithViewPager(vpWiki);

        vpWiki.setCurrentItem(0);
    }


}
