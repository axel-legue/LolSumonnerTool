package com.legue.axel.lolsummonertool.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.legue.axel.lolsummonertool.R;
import com.legue.axel.lolsummonertool.database.model.item.Item;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GraphAdapter extends RecyclerView.Adapter<GraphAdapter.ItemViewHolder> {

    List<Item> mItemList;
    Context mContext;

    public GraphAdapter(List<Item> mItemList, Context mContext) {
        this.mItemList = mItemList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.node, parent, false);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {
        final Item item = mItemList.get(i);
        if (item != null) {
            itemViewHolder.tvItemName.setText(item.name);
            itemViewHolder.tvItemCost.setText("300g");
        }

    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_item)
        ImageView ivItem;
        @BindView(R.id.tv_item_name)
        TextView tvItemName;
        @BindView(R.id.tv_item_cost)
        TextView tvItemCost;

        ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
