package com.legue.axel.lolsummonertool.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.legue.axel.lolsummonertool.R
import com.legue.axel.lolsummonertool.database.model.champion.Spell
import com.legue.axel.lolsummonertool.database.model.champion.SpellImage
import com.legue.axel.lolsummonertool.database.viewmodel.SpellViewModel
import com.legue.axel.lolsummonertool.utils.ImageUtils
import com.legue.axel.lolsummonertool.utils.Utils
import com.legue.axel.lolsummonertool.wiki.activity.WikiChampionInformations
import kotlinx.android.synthetic.main.adapter_champion_spell.view.*
import kotlin.math.roundToInt

class ChampionSpellAdapter(private val mContext: Context, private val mSpells: List<Spell>, private val mActivity: WikiChampionInformations) : RecyclerView.Adapter<ChampionSpellAdapter.ChampionSpellHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ChampionSpellHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.adapter_champion_spell, parent, false)
        return ChampionSpellHolder(v)
    }

    override fun onBindViewHolder(holder: ChampionSpellHolder, i: Int) {
        val spell = mSpells[i]


        val spellViewModel = ViewModelProviders.of(mActivity).get(SpellViewModel::class.java)
        spellViewModel.getSpellImage(spell.id).observe(mActivity, Observer { spellImage: SpellImage? ->
            displayImage(spellImage?.full, holder.ivSpell, holder.pbSpell)

        })

        //TODO complete info
        holder.tvName.text = spell.name

        if (shouldConvertListToString(spell.cost!!)) {
            holder.tvCost.text = Utils.convertFloatListToString(spell.cost)
        } else {
            holder.tvCost.text = Math.round(spell.cost[0]).toString()
        }

        if (shouldConvertListToString(spell.range!!)) {
            holder.tvRange.text = Utils.convertFloatListToString(spell.range)
        } else {
            holder.tvRange.text = spell.range[0].roundToInt().toString()
        }
        holder.tvCooldown.text = Utils.convertFloatListToString(spell.cooldown!!)
        holder.tvLore.text = Html.fromHtml(spell.description, Html.FROM_HTML_MODE_COMPACT)


    }


    override fun getItemCount(): Int {
        return mSpells.size
    }

    private fun shouldConvertListToString(list: List<Float>): Boolean {
        val value = list[0]
        for (i in list.indices) {
            if (value == list[i]) {
                //do nothing
            } else {
                return true
            }
        }
        return false
    }


    private fun displayImage(url: String?, imageView: ImageView, progressBar: ProgressBar) {

        Glide.with(mContext)
                .load(ImageUtils.buildSpellIconUrl(url))
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable>, isFirstResource: Boolean): Boolean {
                        progressBar.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(resource: Drawable, model: Any, target: Target<Drawable>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                        progressBar.visibility = View.GONE
                        imageView.visibility = View.VISIBLE
                        return false
                    }
                })
                .error(R.drawable.ic_placeholder_black_24dp)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(imageView)
    }

    inner class ChampionSpellHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivSpell: ImageView = itemView.iv_spell
        var tvName: TextView = itemView.tv_spell_name
        var tvRange: TextView = itemView.tv_range
        var tvCost: TextView = itemView.tv_cost
        var tvCooldown: TextView = itemView.tv_cooldown
        var tvLore: TextView = itemView.tv_description
        var pbSpell: ProgressBar = itemView.pb_spell
    }

    companion object {
        private val TAG = ChampionSpellAdapter::class.java.name
    }
}
