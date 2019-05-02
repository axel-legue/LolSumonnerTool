package com.legue.axel.lolsummonertool.wiki.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.legue.axel.lolsummonertool.R;
import com.legue.axel.lolsummonertool.SuperApplication;
import com.legue.axel.lolsummonertool.adapter.SummonerSpellAdapter;
import com.legue.axel.lolsummonertool.database.model.summonerspell.SummonerSpell;
import com.legue.axel.lolsummonertool.database.viewmodel.SummonerSpellViewModel;
import com.legue.axel.lolsummonertool.network.retrofit.RetrofitConstants;
import com.legue.axel.lolsummonertool.network.retrofit.RetrofitHelper;
import com.legue.axel.lolsummonertool.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WikiSummonerSpellFragment extends Fragment {

    private final static String TAG = WikiSummonerSpellFragment.class.getName();

    @BindView(R.id.rv_wiki_data)
    RecyclerView rvWikiData;
    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;

    private SummonerSpellAdapter adapter;
    private SuperApplication application;
    private WikiSummonerSpellFragment fragment;
    private List<SummonerSpell> summonerSpellList;

    SummonerSpellAdapter.SummonerSpellListener summonerSpellListener = new SummonerSpellAdapter.SummonerSpellListener() {
        @Override
        public void SummonerSpellSelected(int position, SummonerSpell summonerSpell) {
            Toast.makeText(application, "SummonerSpell a la position : " + position + " sélectionné", Toast.LENGTH_SHORT).show();
        }
    };

    public static WikiSummonerSpellFragment newInstance(int page, String title) {
        WikiSummonerSpellFragment wikiMasteryFragment = new WikiSummonerSpellFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.KEY_WIKI_PAGE_NUMBER, page);
        args.putString(Constants.KEY_WIKI_PAGE_NAME, title);
        wikiMasteryFragment.setArguments(args);
        return wikiMasteryFragment;
    }

    public WikiSummonerSpellFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wiki_data, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        application = (SuperApplication) getActivity().getApplication();
        fragment = this;

        initData();
    }

    private void initData() {
        if (summonerSpellList == null) {
            summonerSpellList = new ArrayList<>();
        }
        loadData();

        adapter = new SummonerSpellAdapter(application, summonerSpellList, summonerSpellListener, fragment);
        rvWikiData.setLayoutManager(new GridLayoutManager(application, 4));
        rvWikiData.setAdapter(adapter);
        rvWikiData.setHasFixedSize(true);
    }


    private void loadData() {
        RetrofitHelper.getSummonerSpells(
                RetrofitConstants.ACTION_COMPLETE,
                summonerHandler,
                application);
    }

    private Handler summonerHandler = new Handler(msg -> {
        switch (msg.what) {
            case RetrofitConstants.ACTION_COMPLETE:
                Log.i(TAG, "ACTION_COMPLETE ");

                SummonerSpellViewModel summonerSpellViewModel = ViewModelProviders.of(fragment).get(SummonerSpellViewModel.class);
                summonerSpellViewModel.getSummonerSpells().observe(fragment, summonerSpells -> {
                    if (summonerSpells != null && summonerSpells.size() > 0) {
                        summonerSpellList.clear();
                        summonerSpellList.addAll(summonerSpells);
                        adapter.notifyDataSetChanged();
                    }
                });
                break;

            case RetrofitConstants.ACTION_ERROR:
                break;
        }
        return true;
    });

}
