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
import butterknife.ButterKnife
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
import java.util.*

class WikiChampionInformations : AppCompatActivity() {

    private var mChampion: Champion? = null
    private var mChampionInfos: ChampionInfo? = null
    private var mChampionStats: ChampionStats? = null
    private var mChampionImage: ChampionImage? = null
    private var mChampionPassive: Passive? = null
    private var mChampionSpells: MutableList<Spell>? = null
    private var mChampionKey = 0
    private var mChampionId: String? = null
    private lateinit var application: SuperApplication
    private var adapter: ChampionSpellAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wiki_champion_informations)
        ButterKnife.bind(this)
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
        if (mChampionSpells == null) {
            mChampionSpells = ArrayList()
        }
        application = getApplication() as SuperApplication
        loadChampion(mChampionId, mChampionKey)
        adapter = ChampionSpellAdapter(application, mChampionSpells, this)
        rv_spells.layoutManager = LinearLayoutManager(this)
        rv_spells.adapter = adapter
        rv_spells.setHasFixedSize(true)
    }

    private fun updateChampion() {
        if (mChampion != null) {
            tv_name.text = mChampion!!.name
            tv_nickname.text = mChampion!!.title
            tv_role.text = mChampion!!.tags[0]
            tv_description.text = mChampion!!.lore
        }
    }

    private fun updateChampionInfo() {
        if (mChampionInfos != null) {
            pb_attack.max = 10
            pb_attack.progress = mChampionInfos!!.attack
            pb_defense.max = 10
            pb_defense.progress = mChampionInfos!!.defense
            pb_difficulty.max = 10
            pb_difficulty.progress = mChampionInfos!!.difficulty
            pb_magic.max = 10
            pb_magic.progress = mChampionInfos!!.magic
        }
    }

    private fun updateChampionStat() {
        if (mChampionStats != null) {
            tv_range_value.text = Math.round(mChampionStats!!.attackRange).toString()
            tv_armor_value.text = Math.round(mChampionStats!!.armor).toString()
            tv_attack_damage_value.text = Math.round(mChampionStats!!.attackDamage).toString()
            tv_attack_speed_value.text = mChampionStats!!.attackSpeed.toString()
            tv_mana_value.text = Math.round(mChampionStats!!.mp).toString()
            tv_mana_regen_value.text = Math.round(mChampionStats!!.mpRegen).toString()
            tv_health_value.text = Math.round(mChampionStats!!.hp).toString()
            tv_health_regen_value.text = mChampionStats!!.hpRegen.toString()
            tv_magic_resist_value.text = Math.round(mChampionStats!!.spellBlock).toString()
            tv_move_speed_value.text = Math.round(mChampionStats!!.moveSpeed).toString()
        }
    }

    private fun updateChampionPassive() {
        if (mChampionPassive != null) {
            tv_passive_description.text = Html.fromHtml(mChampionPassive!!.description, Html.FROM_HTML_MODE_COMPACT)
            tv_passive_name.text = mChampionPassive!!.name
            displayPassiveImage(mChampionPassive!!.image)
        }
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

    private fun updateChampionImage() {
        if (mChampionImage != null) {
            Glide.with(this)
                    .load(ImageUtils.buildChampionIconUrl(mChampionImage!!.full))
                    .error(R.drawable.ic_placeholder_black_24dp)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(iv_champion)
        }
    }

    private fun updateChampionSpells() {
        adapter!!.notifyDataSetChanged()
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
                mChampionViewModel.getChampionByKey(msg.arg1).observe(this, Observer { champion: Champion? ->
                    if (champion != null) {
                        mChampion = champion
                        updateChampion()
                        // adapter.notifyDataSetChanged();
                        getChampionInfo(mChampionViewModel, mChampion!!.key)
                        getChampionStat(mChampionViewModel, mChampion!!.key)
                        getChampionImage(mChampionViewModel, mChampion!!.key)
                        getChampionPassive(mChampionViewModel, mChampion!!.key)
                        getChampionSpells(mChampionViewModel, mChampion!!.key)
                    }
                })
            }
            RetrofitConstants.ACTION_ERROR -> {
            }
        }
        true
    })

    private fun getChampionInfo(championViewModel: ChampionViewModel, championKey: Int) {
        championViewModel.getChampionInfo(championKey).observe(this, Observer { championInfo: ChampionInfo? ->
            if (championInfo != null) {
                mChampionInfos = championInfo
                updateChampionInfo()
            }
        })
    }

    private fun getChampionStat(championViewModel: ChampionViewModel, championKey: Int) {
        championViewModel.getChampionStat(championKey).observe(this, Observer { championStats: ChampionStats? ->
            if (championStats != null) {
                mChampionStats = championStats
                updateChampionStat()
            }
        })
    }

    private fun getChampionImage(championViewModel: ChampionViewModel, championKey: Int) {
        championViewModel.getChampionImage(championKey).observe(this, Observer { championImage: ChampionImage? ->
            if (championImage != null) {
                mChampionImage = championImage
                updateChampionImage()
            }
        })
    }

    private fun getChampionPassive(championViewModel: ChampionViewModel, championKey: Int) {
        championViewModel.getChampionPassive(championKey).observe(this, Observer { passive: Passive? ->
            if (passive != null) {
                mChampionPassive = passive
                updateChampionPassive()
            }
        })
    }

    private fun getChampionSpells(championViewModel: ChampionViewModel, championKey: Int) {
        championViewModel.getChampionSpells(championKey).observe(this, Observer { championSpells: List<Spell?>? ->
            if (championSpells != null && championSpells.isNotEmpty()) {
                mChampionSpells!!.clear()
                mChampionSpells!!.addAll(championSpells as Collection<Spell>)
                updateChampionSpells()
            }
        })
    }

    companion object {
        // TODO : do method for display stats level 1 and stats level-18 if enough time
        private val TAG = WikiChampionInformations::class.java.name
    }
}