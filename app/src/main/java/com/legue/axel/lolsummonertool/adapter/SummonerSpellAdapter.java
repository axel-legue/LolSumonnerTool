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
import com.legue.axel.lolsummonertool.database.model.summonerspell.SummonerSpell;
import com.legue.axel.lolsummonertool.database.model.summonerspell.SummonerSpellImage;
import com.legue.axel.lolsummonertool.database.viewmodel.SummonerSpellViewModel;
import com.legue.axel.lolsummonertool.utils.ImageUtils;
import com.legue.axel.lolsummonertool.wiki.fragment.WikiSummonerSpellFragment;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SummonerSpellAdapter extends RecyclerView.Adapter<SummonerSpellAdapter.SummonerSpellHolder> {
    private static final String TAG = SummonerSpellAdapter.class.getName();

    public interface SummonerSpellListener {
        void SummonerSpellSelected(int position, SummonerSpell summonerSpell);
    }

    private Context mContext;
    private List<SummonerSpell> mSummonerSpells;
    private SummonerSpellAdapter.SummonerSpellListener mSummonerSpellListener;
    private WikiSummonerSpellFragment mFragment;
    private SummonerSpellImage mSummonerSpellImage;

    public SummonerSpellAdapter(Context context, List<SummonerSpell> summonerSpells, SummonerSpellListener summonerSpellListener, WikiSummonerSpellFragment fragment) {
        mContext = context;
        mSummonerSpells = summonerSpells;
        mSummonerSpellListener = summonerSpellListener;
        mFragment = fragment;
    }

    @NonNull
    @Override
    public SummonerSpellHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_summoner_spell, parent, false);
        return new SummonerSpellAdapter.SummonerSpellHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SummonerSpellHolder holder, int position) {

        SummonerSpell summonerSpell = mSummonerSpells.get(position);

        if (summonerSpell != null) {
            SummonerSpellViewModel summonerSpellViewModel = ViewModelProviders.of(mFragment).get(SummonerSpellViewModel.class);
            summonerSpellViewModel.getSummonerSpellImage(summonerSpell.key).observe(mFragment, image -> {
                if (image != null) {
                    mSummonerSpellImage = image;
                    displayImage(mSummonerSpellImage.full, holder.ivIcon, holder.pbItem);
                }
            });

            if (summonerSpell.name != null && !TextUtils.isEmpty(summonerSpell.name)) {
                holder.tvName.setText(summonerSpell.name);
            }

            holder.llWrapper.setOnClickListener(v -> {
                mSummonerSpellListener.SummonerSpellSelected(position, summonerSpell);
            });


        } else {
            Log.i(TAG, "onBindViewHolder: summonerSpell is null");
        }

    }

    @Override
    public int getItemCount() {
        return mSummonerSpells.size();
    }

    private void displayImage(String url, ImageView imageView, ProgressBar progressBar) {
        Glide.with(mContext)
                .load(ImageUtils.BuildSummonerSpellIconUrl(url))
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


    class SummonerSpellHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_summoner_spell)
        ImageView ivIcon;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.pb_summoner_spell)
        ProgressBar pbItem;
        @BindView(R.id.ll_wrapper_summoner_spell)
        LinearLayout llWrapper;

        SummonerSpellHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
