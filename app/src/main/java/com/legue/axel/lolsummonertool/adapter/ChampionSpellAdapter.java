package com.legue.axel.lolsummonertool.adapter;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.legue.axel.lolsummonertool.R;
import com.legue.axel.lolsummonertool.database.model.champion.Spell;
import com.legue.axel.lolsummonertool.database.model.champion.SpellImage;
import com.legue.axel.lolsummonertool.database.viewmodel.SpellViewModel;
import com.legue.axel.lolsummonertool.utils.ImageUtils;
import com.legue.axel.lolsummonertool.utils.Utils;
import com.legue.axel.lolsummonertool.wiki.activity.WikiChampionInformations;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChampionSpellAdapter extends RecyclerView.Adapter<ChampionSpellAdapter.ChampionSpellHolder> {
    private static final String TAG = ChampionSpellAdapter.class.getName();

    private Context mContext;
    private List<Spell> mSpells;
    private WikiChampionInformations mActivity;
    private SpellImage mSpellImage;

    public ChampionSpellAdapter(Context mContext, List<Spell> mSpells, WikiChampionInformations mActivity) {
        this.mContext = mContext;
        this.mSpells = mSpells;
        this.mActivity = mActivity;
    }

    @NonNull
    @Override
    public ChampionSpellHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_champion_spell, parent, false);
        return new ChampionSpellAdapter.ChampionSpellHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ChampionSpellHolder holder, int i) {
        final Spell spell = mSpells.get(i);

        if (spell != null) {

            SpellViewModel spellViewModel = ViewModelProviders.of(mActivity).get(SpellViewModel.class);
            spellViewModel.getSpellImage(spell.id).observe(mActivity, spellImage -> {
                if (spellImage != null) {
                    mSpellImage = spellImage;
                    displayImage(mSpellImage.full, holder.ivSpell, holder.pbSpell);
                }
            });

            //TODO complete info


            if (spell.name != null && !TextUtils.isEmpty(spell.name)) {
                holder.tvName.setText(spell.name);

                if (shouldConvertListToString(spell.cost)) {
                    holder.tvCost.setText(Utils.converFloatListToString(spell.cost));
                } else {
                    holder.tvCost.setText(String.valueOf(Math.round(spell.cost.get(0))));
                }

                if (shouldConvertListToString(spell.range)) {
                    holder.tvRange.setText(Utils.converFloatListToString(spell.range));
                } else {
                    holder.tvRange.setText(String.valueOf(Math.round(spell.range.get(0))));
                }
                holder.tvCooldown.setText(Utils.converFloatListToString(spell.cooldown));
                holder.tvLore.setText(Html.fromHtml(spell.description, Html.FROM_HTML_MODE_COMPACT));
            }


        } else {
            Log.i(TAG, "onBindViewHolder: spell is null");
        }

    }


    @Override
    public int getItemCount() {
        return mSpells.size();
    }

    private boolean shouldConvertListToString(List<Float> list) {
        Float value = list.get(0);
        for (int i = 0; i < list.size(); i++) {
            if (value.equals(list.get(i))) {
                //do nothing
            } else {
                return true;
            }
        }
        return false;
    }


    private void displayImage(String url, ImageView imageView, ProgressBar progressBar) {
        if (mSpellImage != null) {
            Glide.with(mContext)
                    .load(ImageUtils.BuildSpellIconUrl(url))
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
        } else {
            Log.i(TAG, "displayImage: null");
        }

    }

    class ChampionSpellHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_spell)
        ImageView ivSpell;

        @BindView(R.id.tv_spell_name)
        TextView tvName;
        @BindView(R.id.tv_range)
        TextView tvRange;
        @BindView(R.id.tv_cost)
        TextView tvCost;
        @BindView(R.id.tv_cooldown)
        TextView tvCooldown;
        @BindView(R.id.tv_description)
        TextView tvLore;
        @BindView(R.id.pb_spell)
        ProgressBar pbSpell;

        ChampionSpellHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
