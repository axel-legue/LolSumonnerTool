package com.legue.axel.lolsummonertool.wiki;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.legue.axel.lolsummonertool.R;
import com.legue.axel.lolsummonertool.SuperApplication;
import com.legue.axel.lolsummonertool.adapter.ChampionSpellAdapter;
import com.legue.axel.lolsummonertool.database.model.champion.Champion;
import com.legue.axel.lolsummonertool.database.model.champion.ChampionImage;
import com.legue.axel.lolsummonertool.database.model.champion.ChampionInfo;
import com.legue.axel.lolsummonertool.database.model.champion.ChampionStats;
import com.legue.axel.lolsummonertool.database.viewmodel.ChampionViewModel;
import com.legue.axel.lolsummonertool.network.retrofit.RetrofitConstants;
import com.legue.axel.lolsummonertool.network.retrofit.RetrofitHelper;
import com.legue.axel.lolsummonertool.utils.Constants;
import com.legue.axel.lolsummonertool.utils.ImageUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WikiChampionInformations extends AppCompatActivity {
    // TODO : do method for display stats level 1 and stats level-18 if enough time

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

    private Champion mChampion;
    private ChampionInfo mChampionInfos;
    private ChampionStats mChampionStats;
    private ChampionImage mChampionImage;


    private int mChampionKey;
    private String mChampionId;
    private SuperApplication application;
    private ChampionSpellAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wiki_champion_informations);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        if (intent.hasExtra(Constants.WIKI_CHAMPION_KEY)) {
            mChampionKey = intent.getIntExtra(Constants.WIKI_CHAMPION_KEY, 0);
        }
        if (intent.hasExtra(Constants.WIKI_CHAMPION_ID)) {
            mChampionId = intent.getStringExtra(Constants.WIKI_CHAMPION_ID);
        }

        initData();
    }

    private void initData() {
        application = (SuperApplication) this.getApplication();
        loadChampion(mChampionId, mChampionKey);
    }


    private void updateChampionInfo() {
        if (mChampionInfos != null) {
            pbAttack.setMax(10);
            pbAttack.setProgress(mChampionInfos.attack);

            pbDefense.setMax(10);
            pbDefense.setProgress(mChampionInfos.defense);

            pbDifficulty.setMax(10);
            pbDifficulty.setProgress(mChampionInfos.difficulty);

            pbMagic.setMax(10);
            pbMagic.setProgress(mChampionInfos.magic);
        }
    }

    private void updateChampionStat() {
        if (mChampionStats != null) {
            tvRange.setText(String.valueOf(mChampionStats.attackRange));
            tvArmor.setText(String.valueOf(mChampionStats.armor));
            tvAttackDamage.setText(String.valueOf(mChampionStats.attackDamage));
            tvAttackSpeed.setText(String.valueOf(mChampionStats.attackSpeed));
            tvMana.setText(String.valueOf(mChampionStats.mp));
            tvManaRegen.setText(String.valueOf(mChampionStats.mpRegen));
            tvHealth.setText(String.valueOf(mChampionStats.hp));
            tvHealthRegen.setText(String.valueOf(mChampionStats.hpRegen));
            tvMagicResist.setText(String.valueOf(mChampionStats.spellBlock));
            tvMovementSpeed.setText(String.valueOf(mChampionStats.moveSpeed));
        }
    }

    private void updateChampionImage() {
        if (mChampionImage != null) {
            Glide.with(this)
                    .load(ImageUtils.BuildChampionIconUrl(mChampionImage.full))
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            //  progressBar.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            //   progressBar.setVisibility(View.GONE);
                            //    imageView.setVisibility(View.VISIBLE);
                            return false;
                        }
                    })
                    .error(R.drawable.ic_placeholder_black_24dp)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(ivChampion);
        }

    }

    private void updateChampion() {
        if (mChampion != null) {
            tvChampionName.setText(mChampion.name);
            tvChampionNickname.setText(mChampion.title);
            tvChampionRole.setText(mChampion.tags.get(0));
            tvChampionLore.setText(mChampion.lore);
        }


    }

    private void loadChampion(String championId, int championKey) {

        RetrofitHelper.getChampionByName(
                RetrofitConstants.ACTION_COMPLETE,
                championId,
                championKey,
                championhandler,
                application);

    }

    private Handler championhandler = new Handler(msg -> {

        switch (msg.what) {
            case RetrofitConstants.ACTION_COMPLETE:
                Log.i(TAG, "ACTION_COMPLETE ");
                ChampionViewModel mChampionViewModel = ViewModelProviders.of(this).get(ChampionViewModel.class);
                mChampionViewModel.getChampionByKey(msg.arg1).observe(this, champion -> {
                    if (champion != null) {
                        mChampion = champion;
                        updateChampion();
                        // adapter.notifyDataSetChanged();
                        getChampionInfo(mChampionViewModel, mChampion.key);
                        getChampionStat(mChampionViewModel, mChampion.key);
                        getChampionImage(mChampionViewModel, mChampion.key);
                    }
                });
                break;

            case RetrofitConstants.ACTION_ERROR:
                break;
        }
        return true;
    });

    private void getChampionInfo(ChampionViewModel championViewModel, int championKey) {
        championViewModel.getChampionInfo(championKey).observe(this, championInfo -> {
            if (championInfo != null) {
                mChampionInfos = championInfo;
                updateChampionInfo();
            }
        });
    }

    private void getChampionStat(ChampionViewModel championViewModel, int championKey) {
        championViewModel.getChampionStat(championKey).observe(this, championStats -> {
            if (championStats != null) {
                mChampionStats = championStats;
                updateChampionStat();
            }
        });
    }

    private void getChampionImage(ChampionViewModel championViewModel, int championKey) {
        championViewModel.getChampionImage(championKey).observe(this, championImage -> {
            if (championImage != null) {
                mChampionImage = championImage;
                updateChampionImage();
            }
        });
    }


}
