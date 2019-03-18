package com.legue.axel.lolsummonertool.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
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
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.legue.axel.lolsummonertool.R;
import com.legue.axel.lolsummonertool.database.model.champion.Champion;
import com.legue.axel.lolsummonertool.database.model.champion.ChampionImage;
import com.legue.axel.lolsummonertool.database.model.item.ItemImage;
import com.legue.axel.lolsummonertool.utils.ImageUtils;
import com.legue.axel.lolsummonertool.wiki.WikiChampionFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder> {
    private static final String TAG = ItemAdapter.class.getName();

    public interface ItemListener {
        //  void itemSelected(int position, Item item);
    }

//    private Context mContext;
//    private List<Item> mitems;
//    private ItemAdapter.ItemListener mItemListener;
//    private WikiItemFragment mFragment;
//    private ItemImage mItemImage;
//
//
//    public ItemAdapter(Context context, List<Item> items, ItemListener itemListener, WikiItemFragment fragment, ItemImage itemImage) {
//        mContext = context;
//        mitems = items;
//        mItemListener = itemListener;
//        mFragment = fragment;
//        mItemImage = itemImage;
//    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item, parent, false);
        return new ItemHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder itemHolder, int i) {

    }

    @Override
    public int getItemCount() {
       // return mitems.size();
        return 0;
    }

    private void displayImage(String url, ImageView imageView, ProgressBar progressBar) {
      //  if (mItemImage != null) {
      //      Glide.with(mContext)
      //              .load(ImageUtils.BuildItemIconUrl(url))
      //              .listener(new RequestListener<Drawable>() {
      //                  @Override
      //                  public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
      //                      progressBar.setVisibility(View.GONE);
      //                      return false;
      //                  }
//
      //                  @Override
      //                  public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
      //                      progressBar.setVisibility(View.GONE);
      //                      imageView.setVisibility(View.VISIBLE);
      //                      return false;
      //                  }
      //              })
      //              .error(R.drawable.ic_placeholder_black_24dp)
      //              .into(imageView);
      //  } else {
      //      Log.i(TAG, "displayImage: null");
      //  }
//
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_item)
        ImageView ivIcon;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.pb_item)
        ProgressBar pbChampion;
        @BindView(R.id.ll_wrapper_item)
        LinearLayout llWrapper;

        ItemHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
