package com.legue.axel.lolsummonertool.wiki.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
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
import com.legue.axel.lolsummonertool.adapter.ItemAdapter;
import com.legue.axel.lolsummonertool.database.model.item.Item;
import com.legue.axel.lolsummonertool.database.viewmodel.ItemViewModel;
import com.legue.axel.lolsummonertool.network.retrofit.RetrofitConstants;
import com.legue.axel.lolsummonertool.network.retrofit.RetrofitHelper;
import com.legue.axel.lolsummonertool.Constants;
import com.legue.axel.lolsummonertool.wiki.activity.WikiChampionInformations;
import com.legue.axel.lolsummonertool.wiki.activity.WikiItemInformation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WikiItemFragment extends Fragment {

    private final static String TAG = WikiItemFragment.class.getName();

    @BindView(R.id.rv_wiki_data)
    RecyclerView rvItemWiki;
    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;

    private ItemAdapter adapter;
    private SuperApplication application;
    private WikiItemFragment fragment;
    private List<Item> itemList;


    ItemAdapter.ItemListener itemListener = (position, item) -> {
        Intent intent = new Intent(getActivity(), WikiItemInformation.class);
        intent.putExtra(Constants.WIKI_ITEM_ID, item.id);
        startActivity(intent);

    };

    public static WikiItemFragment newInstance(int page, String title) {
        WikiItemFragment itemsFragment = new WikiItemFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.KEY_WIKI_PAGE_NUMBER, page);
        args.putString(Constants.KEY_WIKI_PAGE_NAME, title);
        itemsFragment.setArguments(args);
        return itemsFragment;
    }

    public WikiItemFragment() {
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
        if (itemList == null) {
            itemList = new ArrayList<>();
        }
        loadItems();

        adapter = new ItemAdapter(application, itemList, itemListener, fragment);
        rvItemWiki.setLayoutManager(new GridLayoutManager(application, 4));
        rvItemWiki.setAdapter(adapter);
        rvItemWiki.setHasFixedSize(true);
    }

    private void loadItems() {
        RetrofitHelper.getItems(
                RetrofitConstants.ACTION_COMPLETE,
                itemHandler,
                application);
    }

    private Handler itemHandler = new Handler(msg -> {
        switch (msg.what) {
            case RetrofitConstants.ACTION_COMPLETE:
                Log.i(TAG, "ACTION_COMPLETE ");
                ItemViewModel itemViewModel = ViewModelProviders.of(fragment).get(ItemViewModel.class);
                itemViewModel.getItems().observe(fragment, items -> {
                    if (items != null && items.size() > 0) {
                        itemList.clear();
                        itemList.addAll(items);
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
