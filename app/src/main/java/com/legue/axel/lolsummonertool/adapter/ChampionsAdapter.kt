package com.legue.axel.lolsummonertool.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
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
import com.legue.axel.lolsummonertool.database.model.champion.Champion
import com.legue.axel.lolsummonertool.database.viewmodel.ChampionViewModel
import com.legue.axel.lolsummonertool.utils.ImageUtils
import com.legue.axel.lolsummonertool.wiki.fragment.WikiChampionFragment
import kotlinx.android.synthetic.main.adapter_champion.view.*
import java.util.*

open class ChampionsAdapter// Constructor
(private val mContext: Context, private val mChampions: MutableList<Champion>, private val mChampionListener: ChampionListener, private val mFragment: WikiChampionFragment) : RecyclerView.Adapter<ChampionsAdapter.ChampionHolder>(), Filterable {
    private var mChampionFiltered: List<Champion>

    companion object {
        private val TAG = ChampionsAdapter::class.java.name
    }

    interface ChampionListener {
        fun championSelected(position: Int, champion: Champion?)
    }

    init {
        mChampionFiltered = mChampions
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence): FilterResults {
                var filteredResults: MutableList<Champion> = ArrayList()
                val reset = "all"
                if (constraint.isEmpty()) {
                    filteredResults = mChampions
                } else {
                    val championSet = getFilteredResults(constraint)
                    if (championSet.isNotEmpty()) {
                        filteredResults.addAll(championSet)
                        filteredResults.sortWith(kotlin.Comparator { o1, o2 -> o1.name!!.compareTo(o2.name!!) })
                    }
                }

                val results = FilterResults()
                results.values = filteredResults
                return results
            }

            override fun publishResults(constraint: CharSequence, results: FilterResults?) {
                mChampionFiltered = ArrayList()
                mChampionFiltered = if (results?.values != null) {
                    results.values as List<Champion>
                } else {
                    mChampions
                }
                notifyDataSetChanged()
            }
        }
    }


    protected fun getFilteredResults(constraint: CharSequence): Set<Champion> {
        val results = HashSet<Champion>()

        for (champion in mChampions) {
            for (tag in champion.tags!!) {
                if (tag.toLowerCase().contains(constraint)) {
                    results.add(champion)
                }
                if (constraint.toString().equals("all", ignoreCase = true)) {
                    results.add(champion)
                }
            }
        }
        return results
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChampionHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.adapter_champion, parent, false)
        return ChampionHolder(v)
    }

    override fun onBindViewHolder(holder: ChampionHolder, position: Int) {
        val champion = mChampionFiltered[position]

        val championViewModel = ViewModelProviders.of(mFragment).get(ChampionViewModel::class.java)
        championViewModel.getChampionImage(champion.key).observe(mFragment.viewLifecycleOwner, Observer { championImage ->
            if (championImage != null) {
                championImage.full?.let { displayImage(it, holder.ivIcon, holder.pbChampion) }
            }
        })

        holder.tvName.text = champion.name ?: ""
        holder.llWrapper.setOnClickListener { mChampionListener.championSelected(position, champion) }

    }

    override fun getItemCount(): Int {
        return mChampionFiltered.size
    }

    private fun displayImage(url: String, imageView: ImageView, progressBar: ProgressBar) {
        Glide.with(mContext)
                .load(ImageUtils.buildChampionIconUrl(url))
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
                .circleCrop()
                .error(R.drawable.ic_placeholder_black_24dp)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(imageView)

    }

    inner class ChampionHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivIcon: ImageView = itemView.iv_champion
        var tvName: TextView = itemView.tv_name
        var pbChampion: ProgressBar = itemView.pb_champion
        var llWrapper: LinearLayout = itemView.ll_wrapper_champion

    }
}
