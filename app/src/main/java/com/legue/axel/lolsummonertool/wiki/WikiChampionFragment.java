package com.legue.axel.lolsummonertool.wiki;

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
import com.legue.axel.lolsummonertool.adapter.ChampionsAdapter;
import com.legue.axel.lolsummonertool.database.model.champion.Champion;
import com.legue.axel.lolsummonertool.database.viewmodel.ChampionViewModel;
import com.legue.axel.lolsummonertool.retrofit.RetrofitConstants;
import com.legue.axel.lolsummonertool.retrofit.RetrofitHelper;
import com.legue.axel.lolsummonertool.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WikiChampionFragment extends Fragment {

    private final static String TAG = WikiChampionFragment.class.getName();

    @BindView(R.id.rrv_wiki_champions)
    RecyclerView rvChampionsBuild;
    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;

    private ChampionsAdapter adapter;
    private SuperApplication application;
    private WikiChampionFragment fragment;
    private List<Champion> championList;

    ChampionsAdapter.ChampionListener championListener = new ChampionsAdapter.ChampionListener() {
        @Override
        public void championSelected(int position, Champion champion) {
            Toast.makeText(application, "Champion a la position : " + position + " sélectionné", Toast.LENGTH_SHORT).show();
        }
    };

    public static WikiChampionFragment newInstance(int page, String title) {
        WikiChampionFragment wikiChampionFragment = new WikiChampionFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.KEY_WIKI_PAGE_NUMBER, page);
        args.putString(Constants.KEY_WIKI_PAGE_NAME, title);
        wikiChampionFragment.setArguments(args);
        return wikiChampionFragment;
    }

    public WikiChampionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wiki_champions, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //pbLoading.setVisibility(View.GONE);

        //TODO : testing purpose => update code and move it at a better place
        application = (SuperApplication) getActivity().getApplication();
        fragment = this;

        initData();
    }

    private void initData() {
        if (championList == null) {
            championList = new ArrayList<>();
        }

        loadChampions();

        adapter = new ChampionsAdapter(application, championList, championListener, fragment);
        rvChampionsBuild.setLayoutManager(new GridLayoutManager(application, 4));
        rvChampionsBuild.setAdapter(adapter);
        rvChampionsBuild.setHasFixedSize(true);
    }

    private void loadChampions() {
        //TODO : testing purpose => update code and move it at a better place
        RetrofitHelper.getChampions(
                RetrofitConstants.ACTION_COMPLETE,
                championhandler,
                application);
    }

    private Handler championhandler = new Handler(msg -> {

        switch (msg.what) {
            case RetrofitConstants.ACTION_COMPLETE:
                Log.i(TAG, "ACTION_COMPLETE ");
                ChampionViewModel championViewModel = ViewModelProviders.of(fragment).get(ChampionViewModel.class);
                championViewModel.getChampions().observe(fragment, champions -> {
                    if (champions != null && champions.size() > 0) {
                        championList.clear();
                        championList.addAll(champions);
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
