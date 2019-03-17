package com.legue.axel.lolsummonertool.wiki;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.legue.axel.lolsummonertool.R;
import com.legue.axel.lolsummonertool.SuperApplication;
import com.legue.axel.lolsummonertool.retrofit.RetrofitConstants;
import com.legue.axel.lolsummonertool.utils.Constants;

import butterknife.ButterKnife;

public class ItemsFragment extends Fragment {

    private final static String TAG = ItemsFragment.class.getName();

    private SuperApplication application;
    private ItemsFragment fragment;

    public static ItemsFragment newInstance(int page, String title) {
        ItemsFragment itemsFragment = new ItemsFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.KEY_WIKI_PAGE_NUMBER, page);
        args.putString(Constants.KEY_WIKI_PAGE_NAME, title);
        itemsFragment.setArguments(args);
        return itemsFragment;
    }

    public ItemsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wiki_items, container, false);
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

        loadItems();

    }

    private void loadItems() {

    }

    private Handler itemsHandler = new Handler(msg -> {
        switch (msg.what) {
            case RetrofitConstants.ACTION_COMPLETE:
                Log.i(TAG, "ACTION_COMPLETE ");
//                ChampionViewModel championViewModel = ViewModelProviders.of(fragment).get(ChampionViewModel.class);
//                championViewModel.getChampions().observe(fragment, champions -> {
//                    if (champions != null && champions.size() > 0) {
//                        championList.clear();
//                        championList.addAll(champions);
//                        adapter.notifyDataSetChanged();
//                    }
//                });
                break;

            case RetrofitConstants.ACTION_ERROR:
                break;
        }
        return true;
    });
}
