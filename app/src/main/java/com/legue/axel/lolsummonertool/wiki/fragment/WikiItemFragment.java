package com.legue.axel.lolsummonertool.wiki.fragment;

import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.legue.axel.lolsummonertool.R;
import com.legue.axel.lolsummonertool.SuperApplication;
import com.legue.axel.lolsummonertool.adapter.ItemAdapter;
import com.legue.axel.lolsummonertool.database.model.item.Item;
import com.legue.axel.lolsummonertool.database.viewmodel.ItemViewModel;
import com.legue.axel.lolsummonertool.network.retrofit.RetrofitConstants;
import com.legue.axel.lolsummonertool.network.retrofit.RetrofitHelper;
import com.legue.axel.lolsummonertool.Constants;
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
                // TODO: 16/05/2019 Implement filter Item by type of stats
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        application = (SuperApplication) getActivity().getApplication();
        fragment = this;

        initData();
    }

    private void initData() {
        Log.i(TAG, "initData: ");
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
        Log.i(TAG, "loadItems: ");
        RetrofitHelper.getItems(
                RetrofitConstants.ACTION_COMPLETE,
                itemHandler,
                application);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
        itemList.clear();
        itemList = null;
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
