package com.legue.axel.lolsummonertool.adapter;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

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
import com.legue.axel.lolsummonertool.wiki.fragment.WikiItemFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder> {
    private static final String TAG = ItemAdapter.class.getName();

    public interface ItemListener {
        void itemSelected(int position, Item item);
    }

    private Context mContext;
    private List<Item> mItems;
    private ItemAdapter.ItemListener mItemListener;
    private WikiItemFragment mFragment;
    private ItemImage mItemImage;


    public ItemAdapter(Context context, List<Item> items, ItemListener itemListener, WikiItemFragment fragment) {
        mContext = context;
        mItems = items;
        mItemListener = itemListener;
        mFragment = fragment;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item, parent, false);
        return new ItemHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        final Item item = mItems.get(position);

        if (item != null) {
            ItemViewModel itemViewModel = ViewModelProviders.of(mFragment).get(ItemViewModel.class);
            itemViewModel.getItemImage(item.id).observe(mFragment, itemImage -> {
                if (itemImage != null) {
                    mItemImage = itemImage;
                    displayImage(mItemImage.full, holder.ivIcon, holder.pbItem);
                }
            });

            if (item.name != null && !TextUtils.isEmpty(item.name)) {
                holder.tvName.setText(item.name);
            }

            holder.llWrapper.setOnClickListener(v -> {
                mItemListener.itemSelected(position, item);
            });


        } else {
            Log.i(TAG, "onBindViewHolder: item is null");
        }

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    private void displayImage(String url, ImageView imageView, ProgressBar progressBar) {
        Glide.with(mContext)
                .load(ImageUtils.BuildItemIconUrl(url))
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        imageView.setVisibility(View.VISIBLE);
                        return false;
                    }
                })
                .error(R.drawable.ic_placeholder_black_24dp)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(imageView);

    }

    class ItemHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_item)
        ImageView ivIcon;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.pb_item)
        ProgressBar pbItem;
        @BindView(R.id.ll_wrapper_item)
        LinearLayout llWrapper;

        ItemHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
