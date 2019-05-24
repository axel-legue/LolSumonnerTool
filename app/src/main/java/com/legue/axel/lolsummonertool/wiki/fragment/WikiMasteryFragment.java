package com.legue.axel.lolsummonertool.wiki.fragment;

import android.os.Bundle;
import android.os.Handler;
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
import com.legue.axel.lolsummonertool.adapter.MasteriesAdapter;
import com.legue.axel.lolsummonertool.database.model.mastery.Mastery;
import com.legue.axel.lolsummonertool.database.viewmodel.MasteryViewModel;
import com.legue.axel.lolsummonertool.network.retrofit.RetrofitConstants;
import com.legue.axel.lolsummonertool.network.retrofit.RetrofitHelper;

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
            Toast.makeText(application, getString(R.string.toast_mastery_details, position), Toast.LENGTH_SHORT).show();
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
        RetrofitHelper.getMasteries(
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
