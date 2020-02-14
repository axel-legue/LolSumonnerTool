package com.legue.axel.lolsummonertool.view.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
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
import com.legue.axel.lolsummonertool.database.model.summonerspell.SummonerSpell
import com.legue.axel.lolsummonertool.database.model.summonerspell.SummonerSpellImage
import com.legue.axel.lolsummonertool.viewmodel.SummonerSpellViewModel
import com.legue.axel.lolsummonertool.utils.ImageUtils
import com.legue.axel.lolsummonertool.view.wiki.fragment.WikiSummonerSpellFragment
import kotlinx.android.synthetic.main.adapter_summoner_spell.view.*

class SummonerSpellAdapter(private val mContext: Context, private val mSummonerSpells: List<SummonerSpell>, summonerSpellListener: SummonerSpellListener, private val mFragment: WikiSummonerSpellFragment) : RecyclerView.Adapter<SummonerSpellAdapter.SummonerSpellHolder>() {
    private val mSummonerSpellListener: SummonerSpellListener = summonerSpellListener

    interface SummonerSpellListener {
        fun SummonerSpellSelected(position: Int, summonerSpell: SummonerSpell?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): SummonerSpellHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.adapter_summoner_spell, parent, false)
        return SummonerSpellHolder(v)
    }

    override fun onBindViewHolder(holder: SummonerSpellHolder, position: Int) {

        val summonerSpell = mSummonerSpells[position]

        val summonerSpellViewModel = ViewModelProviders.of(mFragment).get(SummonerSpellViewModel::class.java)



        summonerSpellViewModel.getSummonerSpellImage(summonerSpell.key).observe(mFragment.viewLifecycleOwner, Observer { summonerSpellImage: SummonerSpellImage? ->
            displayImage(summonerSpellImage?.full, holder.ivIcon, holder.pbItem)
        })

        if (summonerSpell.name != null && !TextUtils.isEmpty(summonerSpell.name)) {
            holder.tvName.text = summonerSpell.name
        }

        holder.llWrapper.setOnClickListener { mSummonerSpellListener.SummonerSpellSelected(position, summonerSpell) }
    }

    override fun getItemCount(): Int {
        return mSummonerSpells.size
    }

    private fun displayImage(url: String?, imageView: ImageView, progressBar: ProgressBar) {
        Glide.with(mContext)
                .load(ImageUtils.buildSummonerSpellIconUrl(url))
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


    inner class SummonerSpellHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivIcon: ImageView = itemView.iv_summoner_spell
        var tvName: TextView = itemView.tv_name
        var pbItem: ProgressBar = itemView.pb_summoner_spell
        var llWrapper: LinearLayout = itemView.ll_wrapper_summoner_spell
    }

    companion object {
        private val TAG = SummonerSpellAdapter::class.java.name
    }
}
