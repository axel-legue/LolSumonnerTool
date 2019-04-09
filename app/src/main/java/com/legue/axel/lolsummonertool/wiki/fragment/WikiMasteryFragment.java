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
import com.legue.axel.lolsummonertool.adapter.MasteriesAdapter;
import com.legue.axel.lolsummonertool.database.model.mastery.Mastery;
import com.legue.axel.lolsummonertool.database.viewmodel.MasteryViewModel;
import com.legue.axel.lolsummonertool.network.retrofit.RetrofitConstants;
import com.legue.axel.lolsummonertool.network.retrofit.RetrofitHelper;
import com.legue.axel.lolsummonertool.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WikiMasteryFragment extends Fragment {

    private final static String TAG = WikiMasteryFragment.class.getName();

    @BindView(R.id.rv_wiki_data)
    RecyclerView rvWikiData;
    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;

    private MasteriesAdapter adapter;
    private SuperApplication application;
    private WikiMasteryFragment fragment;
    private List<Mastery> masteryList;


    MasteriesAdapter.MasteryListener masteryListener = new MasteriesAdapter.MasteryListener() {
        @Override
        public void masterySelected(int position, Mastery mastery) {
            Toast.makeText(application, "Mastery a la position : " + position + " sélectionné", Toast.LENGTH_SHORT).show();

        }
    };

    public static WikiMasteryFragment newInstance(int page, String title) {
        WikiMasteryFragment masteryFragment = new WikiMasteryFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.KEY_WIKI_PAGE_NUMBER, page);
        args.putString(Constants.KEY_WIKI_PAGE_NAME, title);
        masteryFragment.setArguments(args);
        return masteryFragment;
    }

    public WikiMasteryFragment() {
        // Required empty public constructor
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
        if (masteryList == null) {
            masteryList = new ArrayList<>();
        }
        loadData();

        adapter = new MasteriesAdapter(application, masteryList, masteryListener, fragment);
        rvWikiData.setLayoutManager(new GridLayoutManager(application, 4));
        rvWikiData.setAdapter(adapter);
        rvWikiData.setHasFixedSize(true);
    }

    private void loadData() {
        RetrofitHelper.getItems(
                RetrofitConstants.ACTION_COMPLETE,
                masteryHandler,
                application);
    }

    private Handler masteryHandler = new Handler(msg -> {
        switch (msg.what) {
            case RetrofitConstants.ACTION_COMPLETE:
                Log.i(TAG, "ACTION_COMPLETE ");

                MasteryViewModel masteryViewModel = ViewModelProviders.of(fragment).get(MasteryViewModel.class);
                masteryViewModel.getMasteries().observe(fragment, masteries -> {
                    if (masteries != null && masteries.size() > 0) {
                        masteryList.clear();
                        masteryList.addAll(masteries);
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
