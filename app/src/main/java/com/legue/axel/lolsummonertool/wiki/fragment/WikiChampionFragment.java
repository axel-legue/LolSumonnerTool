package com.legue.axel.lolsummonertool.wiki.fragment;

import android.app.AlertDialog;
import androidx.lifecycle.ViewModelProviders;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.legue.axel.lolsummonertool.Constants;
import com.legue.axel.lolsummonertool.R;
import com.legue.axel.lolsummonertool.SuperApplication;
import com.legue.axel.lolsummonertool.adapter.ChampionsAdapter;
import com.legue.axel.lolsummonertool.database.model.champion.Champion;
import com.legue.axel.lolsummonertool.database.viewmodel.ChampionViewModel;
import com.legue.axel.lolsummonertool.network.retrofit.RetrofitConstants;
import com.legue.axel.lolsummonertool.network.retrofit.RetrofitHelper;
import com.legue.axel.lolsummonertool.widget.ChampionWidget;
import com.legue.axel.lolsummonertool.wiki.activity.WikiChampionInformations;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WikiChampionFragment extends Fragment {

    private final static String TAG = WikiChampionFragment.class.getName();

    @BindView(R.id.rv_wiki_data)
    RecyclerView rvChampionWiki;
    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;

    private ChampionsAdapter adapter;
    private SuperApplication application;
    private WikiChampionFragment fragment;
    private List<Champion> championList;

    private String[] filterOptions;
    private int mIndexfilterOptionSelected = 0;
    private String mfilterOptionSelected;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    ChampionsAdapter.ChampionListener championListener = new ChampionsAdapter.ChampionListener() {
        @Override
        public void championSelected(int position, Champion champion) {
            Intent intent = new Intent(getActivity(), WikiChampionInformations.class);
            intent.putExtra(Constants.WIKI_CHAMPION_KEY, champion.key);
            intent.putExtra(Constants.WIKI_CHAMPION_ID, champion.id);
            startActivity(intent);
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
        actionFilter.setEnabled(true);
        actionFilter.setVisible(true);

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
                displayFilterDialog();
                return true;
            default:
                break;
        }
        return false;
    }

    public WikiChampionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wiki_data, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //TODO : testing purpose => update code and move it at a better place
        application = (SuperApplication) getActivity().getApplication();
        fragment = this;


        mSharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        if (mSharedPreferences.contains(Constants.KEY_INDEX_SELECTED_OPTION_FILTER)) {
            mIndexfilterOptionSelected = mSharedPreferences.getInt(Constants.KEY_INDEX_SELECTED_OPTION_FILTER, 0);
            if (filterOptions == null) {
                filterOptions = getActivity().getResources().getStringArray(R.array.filter_options_array);
                mfilterOptionSelected = filterOptions[mIndexfilterOptionSelected];
            }
        } else {
            mEditor.putInt(Constants.KEY_INDEX_SELECTED_OPTION_FILTER, 0); // All
            mEditor.apply();
        }

        initData();
    }
    
    private void initData() {
        if (championList == null) {
            championList = new ArrayList<>();
        }

        loadChampions();

        adapter = new ChampionsAdapter(application, championList, championListener, fragment);
        rvChampionWiki.setLayoutManager(new GridLayoutManager(application, 4));
        rvChampionWiki.setAdapter(adapter);
        rvChampionWiki.setHasFixedSize(true);
    }


    private void loadChampions() {
        //TODO : testing purpose => update code and move it at a better place
        RetrofitHelper.getChampions(
                RetrofitConstants.ACTION_COMPLETE,
                championhandler,
                application);

    }

    private void displayFilterDialog() {
        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Filter");

        //add a checkbox list
        if (filterOptions == null) {
            filterOptions = getActivity().getResources().getStringArray(R.array.filter_options_array);
            mfilterOptionSelected = filterOptions[mIndexfilterOptionSelected];
        }

        builder.setSingleChoiceItems(filterOptions, mIndexfilterOptionSelected, (dialogInterface, i) -> {

            if (mSharedPreferences == null) return;
            mEditor = mSharedPreferences.edit();
            mEditor.putInt(Constants.KEY_INDEX_SELECTED_OPTION_FILTER, i);
            mEditor.apply();

            mIndexfilterOptionSelected = i;
            mfilterOptionSelected = filterOptions[mIndexfilterOptionSelected];

            // Send Filter option
            adapter.getFilter().filter(mfilterOptionSelected.toLowerCase());
            dialogInterface.dismiss();
        });

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
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
                        if (mfilterOptionSelected != null) {
                            adapter.getFilter().filter(mfilterOptionSelected.toLowerCase());
                        }
                        adapter.notifyDataSetChanged();
                        updateWidget();
                    }
                });
                break;

            case RetrofitConstants.ACTION_ERROR:
                break;
        }
        return true;
    });

    private void updateWidget() {
        Log.i(TAG, "updateWidget: ");
        Intent intent = new Intent(application.getApplicationContext(), ChampionWidget.class);
        intent.setAction(Constants.ACTION_UPDATE_WIDGET);
        int ids[] = AppWidgetManager.getInstance(application).getAppWidgetIds(new ComponentName(application,ChampionWidget.class));
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,ids);
        getActivity().sendBroadcast(intent);
    }

}
