package com.legue.axel.lolsummonertool.view.adapter

import android.content.Context
import android.graphics.drawable.Drawable
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
import com.legue.axel.lolsummonertool.database.model.mastery.Mastery
import com.legue.axel.lolsummonertool.database.model.mastery.MasteryImage
import com.legue.axel.lolsummonertool.viewmodel.MasteryViewModel
import com.legue.axel.lolsummonertool.utils.ImageUtils
import com.legue.axel.lolsummonertool.view.wiki.fragment.WikiMasteryFragment
import kotlinx.android.synthetic.main.adapter_mastery.view.*

class MasteriesAdapter(private val mContext: Context, private val mMasterys: List<Mastery>, masteryListener: MasteryListener, private val mFragment: WikiMasteryFragment) : RecyclerView.Adapter<MasteriesAdapter.MasteryHolder>() {
    private val mMasteryListener: MasteryListener = masteryListener

    interface MasteryListener {
        fun masterySelected(position: Int, mastery: Mastery?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MasteryHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.adapter_mastery, parent, false)
        return MasteryHolder(v)
    }

    override fun onBindViewHolder(holder: MasteryHolder, position: Int) {
        val mastery = mMasterys[position]

        val masteryViewModel = ViewModelProviders.of(mFragment).get(MasteryViewModel::class.java)
        masteryViewModel.getMasteryImage(mastery.id).observe(mFragment, Observer { masteryImage: MasteryImage? ->
            displayImage(masteryImage?.full, holder.ivIcon, holder.pbMastery)
        })


        holder.tvName.text = mastery.name
        holder.llWrapper.setOnClickListener { mMasteryListener.masterySelected(position, mastery) }


    }

    override fun getItemCount(): Int {
        return mMasterys.size
    }

    private fun displayImage(url: String?, imageView: ImageView, progressBar: ProgressBar) {
        Glide.with(mContext)
                .load(ImageUtils.buildMasteryIconUrl(url))
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

    inner class MasteryHolder(masteryView: View) : RecyclerView.ViewHolder(masteryView) {
        var ivIcon: ImageView = masteryView.iv_mastery
        var tvName: TextView = masteryView.tv_name
        var pbMastery: ProgressBar = masteryView.pb_mastery
        var llWrapper: LinearLayout = masteryView.ll_wrapper_mastery

    }

    companion object {

        private val TAG = MasteriesAdapter::class.java.name
    }
}
