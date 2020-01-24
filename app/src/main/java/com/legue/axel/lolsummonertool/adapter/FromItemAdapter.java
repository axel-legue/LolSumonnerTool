package com.legue.axel.lolsummonertool.adapter;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.legue.axel.lolsummonertool.R;
import com.legue.axel.lolsummonertool.database.model.item.Item;
import com.legue.axel.lolsummonertool.database.model.item.ItemImage;
import com.legue.axel.lolsummonertool.database.viewmodel.ItemViewModel;
import com.legue.axel.lolsummonertool.utils.ImageUtils;
import com.legue.axel.lolsummonertool.wiki.activity.WikiItemInformation;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FromItemAdapter extends RecyclerView.Adapter<FromItemAdapter.ItemViewHolder> {

    private List<String> mItemListId;
    private Item mItem;
    private WikiItemInformation mActivity;
    private ItemImage mItemImage;

    public FromItemAdapter(List<String> itemList, WikiItemInformation activity) {
        mItemListId = itemList;
        mActivity = activity;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.node, parent, false);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int i) {
        final String itemId = mItemListId.get(i);
        if (itemId != null) {
            ItemViewModel itemViewModel = ViewModelProviders.of(mActivity).get(ItemViewModel.class);

            itemViewModel.getItemById(Integer.valueOf(itemId)).observe(mActivity, item -> {
                if (item != null) {
                    mItem = item;
                    if (item.getName() != null && !TextUtils.isEmpty(item.getName())) {
                        holder.tvItemName.setText(item.getName());
                    }

                    getItemImage(holder, itemViewModel, mItem.getId());
                    // TODO: 23/04/2019 replace with gold item
                    holder.tvItemCost.setText(getPrice(item.getId()));

                }
            });


        }
    }

    private void getItemImage(ItemViewHolder holder, ItemViewModel itemViewModel, int itemId) {
        itemViewModel.getItemImage(itemId).observe(mActivity, itemImage -> {
            if (itemImage != null) {
                mItemImage = itemImage;
                displayImage(mItemImage.getFull(), holder.ivItem);
            }
        });
    }

    private String getPrice(int itemId) {
        // TODO: 17/04/2019 Create ItemGoldViewModel and retrieve this information
        return "300g";
    }

    private void displayImage(String url, ImageView imageView) {
        Glide.with(mActivity)
                .load(ImageUtils.INSTANCE.buildItemIconUrl(url))
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        imageView.setVisibility(View.VISIBLE);
                        return false;
                    }
                })
                .error(R.drawable.ic_placeholder_black_24dp)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(imageView);

    }

    @Override
    public int getItemCount() {
        return mItemListId.size();
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
