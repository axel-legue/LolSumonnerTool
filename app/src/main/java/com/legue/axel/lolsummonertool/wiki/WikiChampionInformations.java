package com.legue.axel.lolsummonertool.wiki;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.legue.axel.lolsummonertool.R;
import com.legue.axel.lolsummonertool.SuperApplication;
import com.legue.axel.lolsummonertool.adapter.ChampionSpellAdapter;
import com.legue.axel.lolsummonertool.database.model.champion.Champion;
import com.legue.axel.lolsummonertool.database.model.champion.ChampionInfo;
import com.legue.axel.lolsummonertool.database.model.champion.ChampionStats;
import com.legue.axel.lolsummonertool.database.viewmodel.ChampionViewModel;
import com.legue.axel.lolsummonertool.network.retrofit.RetrofitConstants;
import com.legue.axel.lolsummonertool.network.retrofit.RetrofitHelper;
import com.legue.axel.lolsummonertool.utils.Constants;

import butterknife.BindView;

public class WikiChampionInformations extends AppCompatActivity {
    private final static String TAG = WikiChampionInformations.class.getName();

    @BindView(R.id.iv_champion)
    ImageView ivChampion;
    @BindView(R.id.tv_name)
    TextView tvChampionName;
    @BindView(R.id.tv_nickname)
    TextView tvChampionNickname;
    @BindView(R.id.tv_role)
    TextView tvChampionRole;
    @BindView(R.id.tv_lore)
    TextView tvChampionLore;
    @BindView(R.id.tv_range_value)
    TextView tvRange;
    @BindView(R.id.tv_mana_regen_value)
    TextView tvManaRegen;
    @BindView(R.id.tv_mana_value)
    TextView tvMana;
    @BindView(R.id.tv_health_value)
    TextView tvHealth;
    @BindView(R.id.tv_health_regen_value)
    TextView tvHealthRegen;
    @BindView(R.id.tv_attack_damage_value)
    TextView tvAttackDamage;
    @BindView(R.id.tv_attack_speed_value)
    TextView tvAttackSpeed;
    @BindView(R.id.tv_armor_value)
    TextView tvArmor;
    @BindView(R.id.tv_magic_resist_value)
    TextView tvMagicResist;
    @BindView(R.id.tv_move_speed_value)
    TextView tvMovementSpeed;
    @BindView(R.id.pb_attack)
    ProgressBar pbAttack;
    @BindView(R.id.pb_defense)
    ProgressBar pbDefense;
    @BindView(R.id.pb_magic)
    ProgressBar pbMagic;
    @BindView(R.id.pb_difficulty)
    ProgressBar pbDifficulty;
    @BindView(R.id.rv_spells)
    RecyclerView rvSpells;


    private ChampionViewModel mChampionViewModel;

    private Champion mChampion;
    private ChampionInfo mChampionInfos;
    private ChampionStats mChampionStats;


    private int championKey;
    private String championId;
    private SuperApplication application;
    private ChampionSpellAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wiki_champion_informations);
        Intent intent = getIntent();
        if (intent.hasExtra(Constants.WIKI_CHAMPION_KEY)) {
            championKey = intent.getIntExtra(Constants.WIKI_CHAMPION_KEY, 0);
        }
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
                mChampionViewModel = ViewModelProviders.of(this).get(ChampionViewModel.class);
                mChampionViewModel.getChampionByKey(championKey).observe(this, champion -> {
                    if (champion != null) {
                        mChampion = champion;
                        adapter.notifyDataSetChanged();
                        getChampionInfo(championKey);
                        getChampionStat(championKey);
                    }
                });
                break;

            case RetrofitConstants.ACTION_ERROR:
                break;
        }
        return true;
    });

    private void getChampionInfo(int championKey) {
        mChampionViewModel.getChampionInfo(championKey).observe(this, championInfo -> {
            if (championInfo != null) {
                mChampionInfos = championInfo;
            }
        });
    }

    private void getChampionStat(int championKey) {
        mChampionViewModel.getChampionStat(championKey).observe(this, championStats -> {
            if (championStats != null) {
                mChampionStats = championStats;
            }
        });
    }

}
