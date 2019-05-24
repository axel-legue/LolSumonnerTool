package com.legue.axel.lolsummonertool.wiki.activity;

import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.legue.axel.lolsummonertool.Constants;
import com.legue.axel.lolsummonertool.R;
import com.legue.axel.lolsummonertool.SuperApplication;
import com.legue.axel.lolsummonertool.adapter.FromItemAdapter;
import com.legue.axel.lolsummonertool.database.model.item.Item;
import com.legue.axel.lolsummonertool.database.model.item.ItemGold;
import com.legue.axel.lolsummonertool.database.model.item.ItemImage;
import com.legue.axel.lolsummonertool.database.model.item.ItemStat;
import com.legue.axel.lolsummonertool.database.viewmodel.ItemGoldViewModel;
import com.legue.axel.lolsummonertool.database.viewmodel.ItemViewModel;
import com.legue.axel.lolsummonertool.utils.ImageUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WikiItemInformation extends AppCompatActivity {

    private final static String TAG = WikiItemInformation.class.getName();


    private int mItemId;
    private Item mItem;
    private ItemStat mItemStat;
    private ItemGold mItemGold;
    private FromItemAdapter mFromAdapter;
    private FromItemAdapter mIntoAdapter;
    private List<String> mFromItemIds;
    private List<String> mIntoItemIds;
    private ItemViewModel mItemViewModel;
    private ItemImage mItemImage;

    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_cost)
    TextView mTvCost;
    @BindView(R.id.tv_passive)
    TextView mTvPassive;
    @BindView(R.id.rv_from)
    RecyclerView mRvFrom;
    @BindView(R.id.rv_into)
    RecyclerView mRvInto;
    @BindView(R.id.iv_item)
    ImageView mIvItem;
    @BindView(R.id.tv_title_into)
    TextView mTvTitleInto;
    @BindView(R.id.tv_title_recipe)
    TextView mTvTitleFrom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wiki_item_information);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent.hasExtra(Constants.WIKI_ITEM_ID)) {
            mItemId = intent.getIntExtra(Constants.WIKI_ITEM_ID, 0);
            Log.i(TAG, "onCreate: itemId" + mItemId);
        }

        initData();
    }


    private void initData() {
        Log.i(TAG, "initData");
        SuperApplication mApplication = (SuperApplication) this.getApplication();
        if (mFromItemIds == null) {
            mFromItemIds = new ArrayList<>();
        }
        if (mIntoItemIds == null) {
            mIntoItemIds = new ArrayList<>();
        }

        loadItemImage();
        loadTreeItems();

        mFromAdapter = new FromItemAdapter(mFromItemIds, this);
        setRecyclerViewParameter(mRvFrom, mFromAdapter);
        mIntoAdapter = new FromItemAdapter(mIntoItemIds, this);
        setRecyclerViewParameter(mRvInto, mIntoAdapter);

    }

    private void loadItemImage() {
        mItemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);
        mItemViewModel.getItemImage(mItemId).observe(this, itemImage -> {
            if (itemImage != null) {
                mItemImage = itemImage;
                displayImage();
            }
        });
    }

    private void displayImage() {
        Glide.with(this)
                .load(ImageUtils.BuildItemIconUrl(mItemImage.full))
                .error(R.drawable.ic_placeholder_black_24dp)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(mIvItem);
    }

    private void loadTreeItems() {
        Log.i(TAG, "loadTreeItems");
        mItemViewModel.getItemById(mItemId).observe(this, item -> {
            if (item != null) {
                mItem = item;
                updateItemUi();
                loadItemGold();
            }
        });
    }

    private void loadItemGold() {
        ItemGoldViewModel itemGoldViewModel = ViewModelProviders.of(this).get(ItemGoldViewModel.class);
        itemGoldViewModel.getItemGoldByItemId(mItemId).observe(this, itemGold -> {
            if (itemGold != null) {
                mItemGold = itemGold;
                updateItemGoldUi();
            }
        });
    }


    private void updateItemUi() {
        Log.i(TAG, "updateUi");
        mTvName.setText(mItem.name);
        String itemDescription = mItem.description;
        mTvPassive.setText(Html.fromHtml(itemDescription, Html.FROM_HTML_MODE_LEGACY));
        mFromItemIds.clear();
        mIntoItemIds.clear();
        if (mItem.from != null && mItem.from.size() > 0) {
            mTvTitleFrom.setVisibility(View.VISIBLE);
            mFromItemIds.addAll(mItem.from);
            mFromAdapter.notifyDataSetChanged();
        } else {
            mTvTitleFrom.setVisibility(View.GONE);
        }
        if (mItem.into != null && mItem.into.size() > 0) {
            mTvTitleFrom.setVisibility(View.VISIBLE);
            mIntoItemIds.addAll(mItem.into);
            mIntoAdapter.notifyDataSetChanged();
        } else {
            mTvTitleFrom.setVisibility(View.GONE);
        }
    }


    private void updateItemGoldUi() {
        String itemCost = getString(R.string.item_cost, mItemGold.total, mItemGold.base, mItemGold.sell);
        mTvCost.setText(itemCost);
    }

    private void setRecyclerViewParameter(RecyclerView recyclerView, FromItemAdapter adapter) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }

}
