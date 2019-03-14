package com.legue.axel.lolsummonertool;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.legue.axel.lolsummonertool.adapter.ChampionBuildAdapter;
import com.legue.axel.lolsummonertool.network.ChampionDetailResponse;
import com.legue.axel.lolsummonertool.network.ChampionsResponse;
import com.legue.axel.lolsummonertool.retrofit.Constants;
import com.legue.axel.lolsummonertool.retrofit.RetrofitHelper;

import java.util.HashMap;
import java.util.Iterator;

import butterknife.BindView;


public class BuildsFragment extends Fragment {
    private final static String TAG = BuildsFragment.class.getName();

    @BindView(R.id.rv_champions_build)
    RecyclerView rvChampionsBuild;
    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;

    private ChampionBuildAdapter adapter;
    private SuperApplication application;

    private ChampionsResponse championsResponse;
    private ChampionDetailResponse championDetailResponse;


    public BuildsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_build_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //pbLoading.setVisibility(View.GONE);

        //TODO : testing purpose => update code and move it at a better place
        application = (SuperApplication) getActivity().getApplication();
        loadChampions();
    }

    private void loadChampions() {
        //TODO : testing purpose => update code and move it at a better place
        RetrofitHelper.getChampions(
                Constants.ACTION_COMPLETE,
                championhandler,
                application);
    }

    private Handler championhandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            switch (msg.what) {
                case Constants.ACTION_COMPLETE:

                    if (application.getChampionsResponse() != null) {
                        championsResponse = application.getChampionsResponse();
                        Log.i(TAG, "handleMessage: " + championsResponse);
                        printMap(championsResponse.getChampionList());
                    }
                    break;

                case Constants.ACTION_ERROR:
                    break;
            }
            return true;
        }
    });


    // TODO : clean code : Test iterate LinkedHashMap
    private void printMap(HashMap hashMap) {
        Iterator it = hashMap.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) it.next();
            String key = (String) pair.getKey();
            championDetailResponse = (ChampionDetailResponse) pair.getValue();
            it.remove();
            return;
        }
    }
}
