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
import com.legue.axel.lolsummonertool.database.model.item.Item
import com.legue.axel.lolsummonertool.database.model.item.ItemImage
import com.legue.axel.lolsummonertool.viewmodel.ItemViewModel
import com.legue.axel.lolsummonertool.utils.ImageUtils
import com.legue.axel.lolsummonertool.view.wiki.fragment.WikiItemFragment
import kotlinx.android.synthetic.main.adapter_item.view.*

class ItemAdapter(private val mContext: Context, private val mItems: List<Item>, itemListener: ItemListener, private val mFragment: WikiItemFragment) : RecyclerView.Adapter<ItemAdapter.ItemHolder>() {
    private val mItemListener: ItemListener = itemListener

    interface ItemListener {
        fun itemSelected(position: Int, item: Item)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.adapter_item, parent, false)
        return ItemHolder(v)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = mItems[position]

        val itemViewModel = ViewModelProviders.of(mFragment).get(ItemViewModel::class.java)

        itemViewModel.getItemImage(item.id).observe(mFragment.viewLifecycleOwner, Observer { itemImage: ItemImage? ->
            displayImage(itemImage?.full, holder.ivIcon, holder.pbItem)
        })

        if (item.name != null && !TextUtils.isEmpty(item.name)) {
            holder.tvName.text = item.name
        }

        holder.llWrapper.setOnClickListener { mItemListener.itemSelected(position, item) }


    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    private fun displayImage(url: String?, imageView: ImageView?, progressBar: ProgressBar?) {
        Glide.with(mContext)
                .load(ImageUtils.buildItemIconUrl(url))
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable>, isFirstResource: Boolean): Boolean {
                        progressBar!!.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(resource: Drawable, model: Any, target: Target<Drawable>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                        progressBar!!.visibility = View.GONE
                        imageView!!.visibility = View.VISIBLE
                        return false
                    }
                })
                .error(R.drawable.ic_placeholder_black_24dp)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(imageView!!)

    }

    inner class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivIcon: ImageView = itemView.iv_item
        var tvName: TextView = itemView.tv_name
        var pbItem: ProgressBar = itemView.pb_item
        var llWrapper: LinearLayout = itemView.ll_wrapper_item

    }

    companion object {
        private val TAG = ItemAdapter::class.java.name
    }
}
