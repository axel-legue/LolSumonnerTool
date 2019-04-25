package com.legue.axel.lolsummonertool.profil;


import android.app.AlertDialog;
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
import android.widget.Toast;

import com.legue.axel.lolsummonertool.R;
import com.legue.axel.lolsummonertool.network.retrofit.RetrofitConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class ProfilFragment extends Fragment {
    private final static String TAG = ProfilFragment.class.getName();

    private String mRegionSelected;

    public ProfilFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ProfilFragment newInstance(String param1, String param2) {
        return new ProfilFragment();
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
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_region:
                displayRegion();
                return true;
            default:
                break;
        }
        return false;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Log.i(TAG, "onCreate: ");


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
    }



    private void displayRegion() {
        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Choose Regions");

        // add a checkbox list
        String[] regions = getRegionName(RetrofitConstants.API_SERVICE_PROXIES);
        boolean[] checkedItems = new boolean[regions.length];
        for (int i = 0; i < checkedItems.length; i++) {
            if (i == 0) {
                checkedItems[i] = true;
            } else {
                checkedItems[i] = false;
            }
        }
        builder.setMultiChoiceItems(regions, checkedItems, (dialog, which, isChecked) -> {
            // user checked or unchecked a box
            mRegionSelected = regions[which];
            Toast.makeText(getContext(), "mRegionSelected :" + mRegionSelected, Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });


        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private static String[] getRegionName(HashMap hashMap) {
        List<String> regionList = new ArrayList<>();

        Iterator it = hashMap.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) it.next();
            String key = (String) pair.getKey();
            regionList.add((String) pair.getValue());
            it.remove();
        }

        return regionList.toArray(new String[0]);
    }

}
