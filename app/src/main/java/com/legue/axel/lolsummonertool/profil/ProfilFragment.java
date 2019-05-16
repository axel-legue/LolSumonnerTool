package com.legue.axel.lolsummonertool.profil;


import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.legue.axel.lolsummonertool.Constants;
import com.legue.axel.lolsummonertool.R;
import com.legue.axel.lolsummonertool.network.retrofit.RetrofitConstants;


public class ProfilFragment extends Fragment {
    private final static String TAG = ProfilFragment.class.getName();

    private String mRegionSelected;
    private String mRegionPrefix;
    private String[] regions;
    private String[] prefixRegions;
    private int mIndexRegionSelected = 0;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    private SearchView.OnQueryTextListener mOnQueryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String s) {
            if (s.matches(RetrofitConstants.REGEX_VALIDATION_NAME)) {
                return false;
            } else {
                // TODO: 27/04/2019 Change waring text  and icons
                new AlertDialog.Builder(getContext())
                        .setTitle(R.string.search_error_title)
                        .setMessage(R.string.search_error_message)
                        .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                        })
                        .setIcon(R.drawable.ic_warning_red_24dp)
                        .show();
                return true;
            }
        }

        @Override
        public boolean onQueryTextChange(String s) {
            return false;
        }
    };

    public ProfilFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ProfilFragment newInstance(String param1, String param2) {
        return new ProfilFragment();
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
        actionChooseRegion.setVisible(true);
        actionChooseRegion.setEnabled(true);

        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(mOnQueryTextListener);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_region) {
            displayRegion();
            return true;
        }
        return false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profil, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreated: ");
        getActivity().invalidateOptionsMenu();

        mSharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        if (mSharedPreferences.contains(Constants.KEY_INDEX_SELECTED_REGION)) {
            mIndexRegionSelected = mSharedPreferences.getInt(Constants.KEY_INDEX_SELECTED_REGION, 0);
        } else {
            mEditor.putInt(Constants.KEY_INDEX_SELECTED_REGION, 1); // Euw region
            mEditor.apply();
        }

        if (mSharedPreferences.contains(Constants.KEY_PREFIX_SELECTED_REGION)) {
            mRegionPrefix = mSharedPreferences.getString(Constants.KEY_PREFIX_SELECTED_REGION, null);
        } else {
            mEditor.putString(Constants.KEY_PREFIX_SELECTED_REGION, "EUW1");
            mEditor.apply();
        }

    }


    private void displayRegion() {
        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        // TODO: 5/7/19 Put text to String files
        builder.setTitle("Choose Regions");

        // add a checkbox list
        if (regions == null) {
            regions = getActivity().getResources().getStringArray(R.array.regions_array);
            mRegionSelected = regions[mIndexRegionSelected];
        }

        if (prefixRegions == null) {
            prefixRegions = getActivity().getResources().getStringArray(R.array.regions_index_array);
        }

        builder.setSingleChoiceItems(regions, mIndexRegionSelected, (dialogInterface, i) -> {

            if (mSharedPreferences == null) return;
            mEditor = mSharedPreferences.edit();
            mEditor.putInt(Constants.KEY_INDEX_SELECTED_REGION, i);
            mEditor.putString(Constants.KEY_PREFIX_SELECTED_REGION, prefixRegions[i]);
            mEditor.apply();

            mIndexRegionSelected = i;
            mRegionSelected = regions[mIndexRegionSelected];
            mRegionPrefix = prefixRegions[i];
            dialogInterface.dismiss();
        });

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
