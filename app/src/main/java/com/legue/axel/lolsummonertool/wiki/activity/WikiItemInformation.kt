package com.legue.axel.lolsummonertool.wiki.activity

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
import com.legue.axel.lolsummonertool.SuperApplication
import com.legue.axel.lolsummonertool.adapter.FromItemAdapter
import com.legue.axel.lolsummonertool.database.model.item.Item
import com.legue.axel.lolsummonertool.database.model.item.ItemGold
import com.legue.axel.lolsummonertool.database.model.item.ItemImage
import com.legue.axel.lolsummonertool.database.model.item.ItemStat
import com.legue.axel.lolsummonertool.database.viewmodel.ItemGoldViewModel
import com.legue.axel.lolsummonertool.database.viewmodel.ItemViewModel
import com.legue.axel.lolsummonertool.utils.ImageUtils
import kotlinx.android.synthetic.main.activity_wiki_item_information.*
import java.util.*

class WikiItemInformation : AppCompatActivity() {
    companion object {
        private val TAG = WikiItemInformation::class.java.name
    }

    private var mItemId = 0
    private var mItem: Item? = null
    private val mItemStat: ItemStat? = null
    private var mItemGold: ItemGold? = null
    private var mFromAdapter: FromItemAdapter? = null
    private var mIntoAdapter: FromItemAdapter? = null
    private var mFromItemIds: MutableList<String>? = null
    private var mIntoItemIds: MutableList<String>? = null
    private var mItemViewModel: ItemViewModel? = null
    private var mItemImage: ItemImage? = null

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
        val mApplication = this.application as SuperApplication
        if (mFromItemIds == null) {
            mFromItemIds = ArrayList()
        }
        if (mIntoItemIds == null) {
            mIntoItemIds = ArrayList()
        }
        loadItemImage()
        loadTreeItems()
        mFromAdapter = FromItemAdapter(mFromItemIds, this)
        setRecyclerViewParameter(rv_from, mFromAdapter!!)
        mIntoAdapter = FromItemAdapter(mIntoItemIds, this)
        setRecyclerViewParameter(rv_into, mIntoAdapter!!)
    }

    private fun loadItemImage() {
        mItemViewModel = ViewModelProviders.of(this).get(ItemViewModel::class.java)
        mItemViewModel!!.getItemImage(mItemId).observe(this, Observer { itemImage: ItemImage? ->
            if (itemImage != null) {
                mItemImage = itemImage
                displayImage()
            }
        })
    }

    private fun displayImage() {
        Glide.with(this)
                .load(ImageUtils.buildItemIconUrl(mItemImage!!.full))
                .error(R.drawable.ic_placeholder_black_24dp)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(iv_item)
    }

    private fun loadTreeItems() {
        Log.i(TAG, "loadTreeItems")
        mItemViewModel!!.getItemById(mItemId).observe(this, Observer { item: Item? ->
            if (item != null) {
                mItem = item
                updateItemUi()
                loadItemGold()
            }
        })
    }

    private fun loadItemGold() {
        val itemGoldViewModel = ViewModelProviders.of(this).get(ItemGoldViewModel::class.java)
        itemGoldViewModel.getItemGoldByItemId(mItemId).observe(this, Observer { itemGold: ItemGold? ->
            if (itemGold != null) {
                mItemGold = itemGold
                updateItemGoldUi()
            }
        })
    }

    private fun updateItemUi() {
        Log.i(TAG, "updateUi")
        tv_name.text = mItem!!.name
        val itemDescription = mItem!!.description
        tv_passive.text = Html.fromHtml(itemDescription, Html.FROM_HTML_MODE_LEGACY)
        mFromItemIds!!.clear()
        mIntoItemIds!!.clear()
        if (mItem!!.from != null && mItem!!.from.size > 0) {
            tv_title_recipe.visibility = View.VISIBLE
            mFromItemIds!!.addAll(mItem!!.from)
            mFromAdapter!!.notifyDataSetChanged()
        } else {
            tv_title_recipe!!.visibility = View.GONE
        }
        if (mItem!!.into != null && mItem!!.into.size > 0) {
            tv_title_recipe.visibility = View.VISIBLE
            mIntoItemIds!!.addAll(mItem!!.into)
            mIntoAdapter!!.notifyDataSetChanged()
        } else {
            tv_title_recipe!!.visibility = View.GONE
        }
    }

    private fun updateItemGoldUi() {
        val itemCost = getString(R.string.item_cost, mItemGold!!.total, mItemGold!!.base, mItemGold!!.sell)
        tv_cost.text = itemCost
    }

    private fun setRecyclerViewParameter(recyclerView: RecyclerView?, adapter: FromItemAdapter) {
        val linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView!!.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
    }

}