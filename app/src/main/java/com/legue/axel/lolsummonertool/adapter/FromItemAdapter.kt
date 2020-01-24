package com.legue.axel.lolsummonertool.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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
import com.legue.axel.lolsummonertool.database.viewmodel.ItemViewModel
import com.legue.axel.lolsummonertool.utils.ImageUtils
import com.legue.axel.lolsummonertool.wiki.activity.WikiItemInformation
import kotlinx.android.synthetic.main.node.view.*

class FromItemAdapter(private val ListId: List<String>, private val mActivity: WikiItemInformation) : RecyclerView.Adapter<FromItemAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.node, parent, false)
        return ItemViewHolder(v)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, i: Int) {
        val itemId = ListId[i]
        val itemViewModel = ViewModelProviders.of(mActivity).get(ItemViewModel::class.java)

        itemViewModel.getItemById(Integer.valueOf(itemId)).observe(mActivity, Observer { item: Item? ->

            holder.tvItemName.text = item?.name ?: ""

            getItemImage(holder, itemViewModel, item?.id)
            // TODO: 23/04/2019 replace with gold item
            holder.tvItemCost.text = getPrice(item?.id)


        })

    }

    private fun getItemImage(holder: ItemViewHolder, itemViewModel: ItemViewModel, itemId: Int?) {
        if (itemId != null) {
            itemViewModel.getItemImage(itemId).observe(mActivity, Observer { itemImage: ItemImage? ->
                displayImage(itemImage?.full, holder.ivItem)

            })
        }
    }

    private fun getPrice(itemId: Int?): String {
        // TODO: 17/04/2019 Create ItemGoldViewModel and retrieve this information
        return "300g"
    }

    private fun displayImage(url: String?, imageView: ImageView?) {
        Glide.with(mActivity)
                .load(ImageUtils.buildItemIconUrl(url))
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable>, isFirstResource: Boolean): Boolean {
                        return false
                    }

                    override fun onResourceReady(resource: Drawable, model: Any, target: Target<Drawable>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                        imageView!!.visibility = View.VISIBLE
                        return false
                    }
                })
                .error(R.drawable.ic_placeholder_black_24dp)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(imageView!!)

    }

    override fun getItemCount(): Int {
        return ListId.size
    }


    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivItem: ImageView = itemView.iv_item
        var tvItemName: TextView = itemView.tv_item_name
        var tvItemCost: TextView = itemView.tv_item_cost
    }
}
