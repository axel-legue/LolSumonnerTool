package com.legue.axel.lolsummonertool.wiki.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.legue.axel.lolsummonertool.Constants;
import com.legue.axel.lolsummonertool.R;
import com.legue.axel.lolsummonertool.SuperApplication;
import com.legue.axel.lolsummonertool.database.model.item.Item;
import com.legue.axel.lolsummonertool.database.viewmodel.ItemViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.blox.graphview.GraphAdapter;

public class WikiItemInformation extends AppCompatActivity {

    private final static String TAG = WikiItemInformation.class.getName();


    private int nodeCount = 1;
    private int mItemId;
    private Item mItem;
    private SuperApplication application;
    private GraphAdapter graphAdapter;

    @BindView(R.id.graph)
    RecyclerView mGraphView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wiki_item_information);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent.hasExtra(Constants.WIKI_ITEM_ID)) {
            mItemId = intent.getIntExtra(Constants.WIKI_ITEM_ID, 0);
        }

        initData();
    }


    private void initData() {
        application = (SuperApplication) this.getApplication();
        ItemViewModel mItemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);
        mItemViewModel.getItemById(mItemId).observe(this, item -> {
            if (item != null) {
                mItem = item;
                updateUi();
            }
        });

    }

    private void updateUi() {
    }

}
