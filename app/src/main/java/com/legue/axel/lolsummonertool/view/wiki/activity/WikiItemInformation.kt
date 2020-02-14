package com.legue.axel.lolsummonertool.view.wiki.activity

import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.legue.axel.lolsummonertool.Constants
import com.legue.axel.lolsummonertool.R
import com.legue.axel.lolsummonertool.view.adapter.FromItemAdapter
import com.legue.axel.lolsummonertool.database.model.item.Item
import com.legue.axel.lolsummonertool.database.model.item.ItemGold
import com.legue.axel.lolsummonertool.database.model.item.ItemImage
import com.legue.axel.lolsummonertool.database.model.item.ItemStat
import com.legue.axel.lolsummonertool.viewmodel.ItemGoldViewModel
import com.legue.axel.lolsummonertool.viewmodel.ItemViewModel
import com.legue.axel.lolsummonertool.utils.ImageUtils
import kotlinx.android.synthetic.main.activity_wiki_item_information.*

class WikiItemInformation : AppCompatActivity() {
    companion object {
        private val TAG = WikiItemInformation::class.java.name
    }

    private var mItemId = 0
    private val mItemStat: ItemStat? = null
    private lateinit var mFromAdapter: FromItemAdapter
    private lateinit var mIntoAdapter: FromItemAdapter
    private lateinit var mFromItemIds: MutableList<String>
    private lateinit var mIntoItemIds: MutableList<String>
    private lateinit var mItemViewModel: ItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wiki_item_information)
        val intent = intent
        if (intent.hasExtra(Constants.WIKI_ITEM_ID)) {
            mItemId = intent.getIntExtra(Constants.WIKI_ITEM_ID, 0)
            Log.i(TAG, "onCreate: itemId$mItemId")
        }
        initData()
    }

    private fun initData() {
        Log.i(TAG, "initData")
        mFromItemIds = arrayListOf()
        mIntoItemIds = arrayListOf()
        loadItemImage()
        loadTreeItems()
        mFromAdapter = FromItemAdapter(mFromItemIds, this)
        setRecyclerViewParameter(rv_from, mFromAdapter)
        mIntoAdapter = FromItemAdapter(mIntoItemIds, this)
        setRecyclerViewParameter(rv_into, mIntoAdapter)
    }

    private fun loadItemImage() {
        mItemViewModel = ViewModelProviders.of(this).get(ItemViewModel::class.java)
        mItemViewModel.getItemImage(mItemId).observe(this, Observer { itemImage: ItemImage ->
            displayImage(itemImage)
        })
    }

    private fun displayImage(itemImage: ItemImage) {
        Glide.with(this)
                .load(ImageUtils.buildItemIconUrl(itemImage.full))
                .error(R.drawable.ic_placeholder_black_24dp)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(iv_item)
    }

    private fun loadTreeItems() {
        Log.i(TAG, "loadTreeItems")
        mItemViewModel.getItemById(mItemId).observe(this, Observer { item: Item ->
            updateItemUi(item)
            loadItemGold(item)
        })
    }

    private fun loadItemGold(item: Item) {
        val itemGoldViewModel = ViewModelProviders.of(this).get(ItemGoldViewModel::class.java)
        itemGoldViewModel.getItemGoldByItemId(item.id).observe(this, Observer { itemGold: ItemGold ->
            updateItemGoldUi(itemGold)

        })
    }

    private fun updateItemUi(item: Item) {
        Log.i(TAG, "updateUi")
        tv_name.text = item.name
        tv_passive.text = Html.fromHtml(item.description, Html.FROM_HTML_MODE_LEGACY)
        mFromItemIds.clear()
        mIntoItemIds.clear()

        if (item.from != null && item.from?.size > 0) {
            tv_title_recipe.visibility = View.VISIBLE
            mFromItemIds.addAll(item.from)
            mFromAdapter.notifyDataSetChanged()
        } else {
            tv_title_recipe.visibility = View.GONE
        }

        if (item.into != null && item.into.size > 0) {
            tv_title_recipe.visibility = View.VISIBLE
            mIntoItemIds.addAll(item.into)
            mIntoAdapter.notifyDataSetChanged()
        } else {
            tv_title_recipe.visibility = View.GONE
        }
    }

    private fun updateItemGoldUi(itemGold: ItemGold) {
        val itemCost = getString(R.string.item_cost, itemGold.total, itemGold.base, itemGold.sell)
        tv_cost.text = itemCost
    }

    private fun setRecyclerViewParameter(recyclerView: RecyclerView, adapter: FromItemAdapter) {
        val linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
    }

}