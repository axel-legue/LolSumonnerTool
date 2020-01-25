package com.legue.axel.lolsummonertool.wiki.activity

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
import com.legue.axel.lolsummonertool.adapter.ChampionSpellAdapter
import com.legue.axel.lolsummonertool.database.model.champion.*
import com.legue.axel.lolsummonertool.database.viewmodel.ChampionViewModel
import com.legue.axel.lolsummonertool.network.retrofit.RetrofitConstants
import com.legue.axel.lolsummonertool.network.retrofit.RetrofitHelper
import com.legue.axel.lolsummonertool.utils.ImageUtils
import kotlinx.android.synthetic.main.layout_champion_difficulty.*
import kotlinx.android.synthetic.main.layout_champion_global_info.*
import kotlinx.android.synthetic.main.layout_champion_lore.*
import kotlinx.android.synthetic.main.layout_champion_passive.*
import kotlinx.android.synthetic.main.layout_champion_spells.*
import kotlinx.android.synthetic.main.layout_champion_stats.*
import kotlin.math.roundToInt

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

    private fun updateChampion(champion: Champion) {
        tv_name.text = champion.name ?: ""
        tv_nickname.text = champion.title ?: ""
        tv_role.text = champion.tags?.get(0) ?: ""
        tv_description.text = champion.lore ?: ""

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

    private fun updateChampionStat(championStats: ChampionStats) {
        tv_range_value.text = championStats.attackRange?.let { it.roundToInt().toString() }
        tv_armor_value.text = championStats.armor?.let { it.roundToInt().toString() }
        tv_attack_damage_value.text = championStats.attackDamage?.let { it.roundToInt().toString() }
        tv_attack_speed_value.text = championStats.run { attackSpeed.toString() }
        tv_mana_value.text = championStats.mp?.let { it.roundToInt().toString() }
        tv_mana_regen_value.text = championStats.mpRegen?.let { it.roundToInt().toString() }
        tv_health_value.text = championStats.hp?.let { it.roundToInt().toString() }
        tv_health_regen_value.text = championStats.hpRegen.toString()
        tv_magic_resist_value.text = championStats.spellBlock?.let { it.roundToInt().toString() }
        tv_move_speed_value.text = championStats.moveSpeed?.let { it.roundToInt().toString() }
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
                mChampionViewModel.getChampionByKey(msg.arg1).observe(this, Observer { champion: Champion ->
                    updateChampion(champion)
                    // adapter.notifyDataSetChanged();
                    getChampionInfo(mChampionViewModel, champion.key)
                    getChampionStat(mChampionViewModel, champion.key)
                    getChampionImage(mChampionViewModel, champion.key)
                    getChampionPassive(mChampionViewModel, champion.key)
                    getChampionSpells(mChampionViewModel, champion.key)
                })
            }
            RetrofitConstants.ACTION_ERROR -> {
                Log.w(TAG, "ACTION_ERROR")
            }
        }
        true
    })

    private fun getChampionInfo(championViewModel: ChampionViewModel, championKey: Int) {
        championViewModel.getChampionInfo(championKey).observe(this, Observer { championInfo: ChampionInfo ->
            updateChampionInfo(championInfo)

        })
    }

    private fun getChampionStat(championViewModel: ChampionViewModel, championKey: Int) {
        championViewModel.getChampionStat(championKey).observe(this, Observer { championStats: ChampionStats ->
            updateChampionStat(championStats)

        })
    }

    private fun getChampionImage(championViewModel: ChampionViewModel, championKey: Int) {
        championViewModel.getChampionImage(championKey).observe(this, Observer { championImage: ChampionImage ->
            updateChampionImage(championImage)
        })
    }

    private fun getChampionPassive(championViewModel: ChampionViewModel, championKey: Int) {
        championViewModel.getChampionPassive(championKey).observe(this, Observer { passive: Passive? ->
            if (passive != null) {
                updateChampionPassive(passive)
            }
        })
    }

    private fun getChampionSpells(championViewModel: ChampionViewModel, championKey: Int) {
        championViewModel.getChampionSpells(championKey).observe(this, Observer { championSpells: List<Spell> ->
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