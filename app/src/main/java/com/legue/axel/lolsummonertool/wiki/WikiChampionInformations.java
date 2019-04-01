package com.legue.axel.lolsummonertool.wiki;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.legue.axel.lolsummonertool.R;
import com.legue.axel.lolsummonertool.SuperApplication;
import com.legue.axel.lolsummonertool.database.model.champion.Champion;
import com.legue.axel.lolsummonertool.network.retrofit.RetrofitConstants;
import com.legue.axel.lolsummonertool.network.retrofit.RetrofitHelper;
import com.legue.axel.lolsummonertool.utils.Constants;

public class WikiChampionInformations extends AppCompatActivity {
    private final static String TAG = WikiChampionInformations.class.getName();

    private Champion champion;
    private String championId;
    private SuperApplication application;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wiki_champion_informations);
        Intent intent = getIntent();
        if (intent.hasExtra(Constants.WIKI_CHAMPION_ID)) {
            championId = intent.getStringExtra(Constants.WIKI_CHAMPION_ID);
        }

        initData();
    }

    private void initData() {
        application = (SuperApplication) this.getApplication();
        loadChampion(championId);

    }

    private void loadChampion(String championId) {

        RetrofitHelper.getChampionByName(
                RetrofitConstants.ACTION_COMPLETE,
                championId,
                championhandler,
                application);

    }

    private Handler championhandler = new Handler(msg -> {

        switch (msg.what) {
            case RetrofitConstants.ACTION_COMPLETE:
                Log.i(TAG, "ACTION_COMPLETE ");
//                ChampionViewModel championViewModel = ViewModelProviders.of(this).get(ChampionViewModel.class);
//                championViewModel.getChampions().observe(this, champions -> {
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
