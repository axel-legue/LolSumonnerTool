package com.legue.axel.lolsummonertool.wiki.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.legue.axel.lolsummonertool.Constants;
import com.legue.axel.lolsummonertool.R;
import com.legue.axel.lolsummonertool.SuperApplication;
import com.legue.axel.lolsummonertool.adapter.SummonerSpellAdapter;
import com.legue.axel.lolsummonertool.database.model.summonerspell.SummonerSpell;
import com.legue.axel.lolsummonertool.database.viewmodel.SummonerSpellViewModel;
import com.legue.axel.lolsummonertool.network.retrofit.RetrofitConstants;
import com.legue.axel.lolsummonertool.network.retrofit.RetrofitHelper;

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
    private Parcelable savedRecyclerLayoutState;

    SummonerSpellAdapter.SummonerSpellListener summonerSpellListener = new SummonerSpellAdapter.SummonerSpellListener() {
        @Override
        public void SummonerSpellSelected(int position, SummonerSpell summonerSpell) {
            Toast.makeText(application, getString(R.string.toast_spell_details, position), Toast.LENGTH_SHORT).show();
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Log.i(TAG, "onCreate: ");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        MenuItem actionSettings = menu.findItem(R.id.action_settings);
        actionSettings.setEnabled(false);
        actionSettings.setVisible(false);

        MenuItem actionFilter = menu.findItem(R.id.action_filter);
        actionFilter.setEnabled(false);
        actionFilter.setVisible(false);

        MenuItem actionChooseRegion = menu.findItem(R.id.action_region);
        actionChooseRegion.setVisible(false);
        actionChooseRegion.setEnabled(false);

        MenuItem actionSearch = menu.findItem(R.id.action_search);
        actionSearch.setVisible(false);
        actionSearch.setEnabled(false);

        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setVisibility(View.GONE);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_filter:
                return true;
            default:
                break;
        }
        return false;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wiki_data, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(Constants.KEY_SPELL_GRID_LAYOUT_MANAGER, rvWikiData.getLayoutManager().onSaveInstanceState());
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.containsKey(Constants.KEY_SPELL_GRID_LAYOUT_MANAGER)) {
            savedRecyclerLayoutState = savedInstanceState.getParcelable(Constants.KEY_SPELL_GRID_LAYOUT_MANAGER);
            rvWikiData.getLayoutManager().onRestoreInstanceState(savedRecyclerLayoutState);
        }

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
                        rvWikiData.getLayoutManager().onRestoreInstanceState(savedRecyclerLayoutState);
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
