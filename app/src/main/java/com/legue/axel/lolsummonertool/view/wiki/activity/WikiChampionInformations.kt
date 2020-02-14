package com.legue.axel.lolsummonertool.view.wiki.activity

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.text.Html
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.legue.axel.lolsummonertool.Constants
import com.legue.axel.lolsummonertool.R
import com.legue.axel.lolsummonertool.SuperApplication
import com.legue.axel.lolsummonertool.database.model.champion.*
import com.legue.axel.lolsummonertool.network.retrofit.RetrofitConstants
import com.legue.axel.lolsummonertool.network.retrofit.RetrofitHelper
import com.legue.axel.lolsummonertool.utils.ImageUtils
import com.legue.axel.lolsummonertool.view.adapter.ChampionSpellAdapter
import com.legue.axel.lolsummonertool.viewmodel.ChampionViewModel
import kotlinx.android.synthetic.main.layout_champion_difficulty.*
import kotlinx.android.synthetic.main.layout_champion_global_info.*
import kotlinx.android.synthetic.main.layout_champion_lore.*
import kotlinx.android.synthetic.main.layout_champion_passive.*
import kotlinx.android.synthetic.main.layout_champion_spells.*
import kotlinx.android.synthetic.main.layout_champion_stats.*

class WikiChampionInformations : AppCompatActivity() {


    private lateinit var mChampionSpells: MutableList<Spell>
    private var mChampionKey = 0
    private var mChampionId: String? = null
    private lateinit var application: SuperApplication
    private lateinit var adapter: ChampionSpellAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wiki_champion_informations)
        val intent = intent
        if (intent.hasExtra(Constants.WIKI_CHAMPION_KEY)) {
            mChampionKey = intent.getIntExtra(Constants.WIKI_CHAMPION_KEY, 0)
        }
        if (intent.hasExtra(Constants.WIKI_CHAMPION_ID)) {
            mChampionId = intent.getStringExtra(Constants.WIKI_CHAMPION_ID)
        }
        initData()
    }

    private fun initData() {
        mChampionSpells = arrayListOf()
        application = getApplication() as SuperApplication
        adapter = ChampionSpellAdapter(application, mChampionSpells, this)
        loadChampion(mChampionId, mChampionKey)
        rv_spells.layoutManager = LinearLayoutManager(this)
        rv_spells.adapter = adapter
        rv_spells.setHasFixedSize(true)
    }

    private fun updateChampion(championViewModel: ChampionViewModel) {
        championViewModel.getName().observe(this, Observer {
            tv_name.text = it
        })
        championViewModel.getNickName().observe(this, Observer {
            tv_nickname.text = it
        })
        championViewModel.getRole().observe(this, Observer {
            tv_role.text = it
        })
        championViewModel.getDescription().observe(this, Observer {
            tv_description.text = it
        })
    }

    private fun updateChampionInfo(championInfo: ChampionInfo) {
        pb_attack.max = 10
        pb_attack.progress = championInfo.attack ?: 0
        pb_defense.max = 10
        pb_defense.progress = championInfo.defense ?: 0
        pb_difficulty.max = 10
        pb_difficulty.progress = championInfo.difficulty ?: 0
        pb_magic.max = 10
        pb_magic.progress = championInfo.magic ?: 0

    }

    private fun updateChampionPassive(passive: Passive) {
        tv_passive_description.text = Html.fromHtml(passive.description, Html.FROM_HTML_MODE_COMPACT)
        tv_passive_name.text = passive.name
        displayPassiveImage(passive.image)

    }

    private fun displayPassiveImage(passiveImageEndPoint: String?) {
        if (passiveImageEndPoint != null) {
            Glide.with(this)
                    .load(ImageUtils.buildPassiveIconUrl(passiveImageEndPoint))
                    .error(R.drawable.ic_placeholder_black_24dp)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(iv_passive)
        }
    }

    private fun updateChampionImage(championImage: ChampionImage) {
        Glide.with(this)
                .load(ImageUtils.buildChampionIconUrl(championImage.full))
                .error(R.drawable.ic_placeholder_black_24dp)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(iv_champion)
    }

    private fun updateChampionSpells() {
        adapter.notifyDataSetChanged()
    }

    private fun loadChampion(championId: String?, championKey: Int) {
        if (championId != null) {
            RetrofitHelper.getChampionByName(
                    RetrofitConstants.ACTION_COMPLETE,
                    championId,
                    championKey,
                    championhandler,
                    application)
        } else {
            Log.w(TAG, "Champion id is null")
        }
    }

    private val championhandler = Handler(Handler.Callback { msg: Message ->
        when (msg.what) {
            RetrofitConstants.ACTION_COMPLETE -> {
                Log.i(TAG, "ACTION_COMPLETE ")
                val mChampionViewModel = ViewModelProviders.of(this).get(ChampionViewModel::class.java)
                mChampionViewModel.start(mChampionKey)
                mChampionViewModel.getChampionByKey().observe(this, Observer { champion: Champion ->
                    updateChampion(mChampionViewModel)
                    // adapter.notifyDataSetChanged();
                    updateChampionInfo(mChampionViewModel)
                    updateChampionStat(mChampionViewModel)
                    getChampionImage(mChampionViewModel)
                    getChampionPassive(mChampionViewModel)
                    getChampionSpells(mChampionViewModel)
                })
            }
            RetrofitConstants.ACTION_ERROR -> {
                Log.w(TAG, "ACTION_ERROR")
            }
        }
        true
    })

    private fun updateChampionInfo(championViewModel: ChampionViewModel) {
        championViewModel.getChampionInfo().observe(this, Observer { championInfo: ChampionInfo ->
            updateChampionInfo(championInfo)

        })
    }

    private fun updateChampionStat(championViewModel: ChampionViewModel) {
        championViewModel.getRange().observe(this, Observer {
            tv_range_value.text = it
        })
        championViewModel.getMpRegen().observe(this, Observer {
            tv_mana_regen_value.text = it
        })
        championViewModel.getMp().observe(this, Observer {
            tv_mana_value.text = it
        })
        championViewModel.getHp().observe(this, Observer {
            tv_health_value.text = it
        })
        championViewModel.getHpRegen().observe(this, Observer {
            tv_health_regen_value.text = it
        })
        championViewModel.getAttackSpeed().observe(this, Observer {
            tv_attack_speed_value.text = it
        })
        championViewModel.getArmor().observe(this, Observer {
            tv_armor_value.text = it
        })
        championViewModel.getMagicResist().observe(this, Observer {
            tv_magic_resist_value.text = it
        })
        championViewModel.getAttackDamage().observe(this, Observer {
            tv_attack_damage_value.text = it
        })
        championViewModel.getMovementSpeed().observe(this, Observer {
            tv_move_speed_value.text = it
        })
    }

    private fun getChampionImage(championViewModel: ChampionViewModel) {
        championViewModel.getChampionImage().observe(this, Observer { championImage: ChampionImage ->
            updateChampionImage(championImage)
        })
    }

    private fun getChampionPassive(championViewModel: ChampionViewModel) {
        championViewModel.getChampionPassive().observe(this, Observer { passive: Passive? ->
            if (passive != null) {
                updateChampionPassive(passive)
            }
        })
    }

    private fun getChampionSpells(championViewModel: ChampionViewModel) {
        championViewModel.getChampionSpells().observe(this, Observer { championSpells: List<Spell> ->
            if (championSpells.isNotEmpty()) {
                mChampionSpells.clear()
                mChampionSpells.addAll(championSpells as Collection<Spell>)
                updateChampionSpells()
            }
        })
    }

    companion object {
        // TODO : do method for display stats level 1 and stats level-18 if enough time
        private val TAG = WikiChampionInformations::class.java.name
    }
}