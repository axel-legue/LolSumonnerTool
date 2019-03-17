package com.legue.axel.lolsummonertool.adapter;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.legue.axel.lolsummonertool.R;
import com.legue.axel.lolsummonertool.database.model.RiotImage;
import com.legue.axel.lolsummonertool.database.model.champion.Champion;
import com.legue.axel.lolsummonertool.database.viewmodel.ChampionViewModel;
import com.legue.axel.lolsummonertool.utils.ImageUtils;
import com.legue.axel.lolsummonertool.wiki.WikiChampionFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChampionsAdapter extends RecyclerView.Adapter<ChampionsAdapter.ChampionHolder> {

    private static final String TAG = ChampionsAdapter.class.getName();

    public interface ChampionListener {
        void championSelected(int position, Champion champion);
    }

    private Context mContext;
    private List<Champion> mChampions;
    private ChampionListener mChampionListener;
    private WikiChampionFragment mFragment;
    private RiotImage mRiotImage;

    // Constructor
    public ChampionsAdapter(Context context, List<Champion> champions, ChampionListener championListener, WikiChampionFragment fragment) {
        mContext = context;
        mChampions = champions;
        mChampionListener = championListener;
        mFragment = fragment;
    }

    @NonNull
    @Override
    public ChampionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.champion_adapter, parent, false);
        return new ChampionHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ChampionHolder holder, int position) {
        final Champion champion = mChampions.get(position);

        if (champion != null) {
            ChampionViewModel championViewModel = ViewModelProviders.of(mFragment).get(ChampionViewModel.class);
            championViewModel.getChampionImage(champion.key).observe(mFragment, riotImage -> {
                if (riotImage != null) {
                    mRiotImage = riotImage;
                    displayImage(mRiotImage.full, holder.ivIcon);
                }
            });


            if (champion.name != null && !TextUtils.isEmpty(champion.name)) {
                holder.tvName.setText(champion.name);
            }

            holder.llWrapper.setOnClickListener(v -> {
                mChampionListener.championSelected(position, champion);
            });

        } else {
            Log.i(TAG, "onBindViewHolder: champion is null");
        }
    }

    @Override
    public int getItemCount() {
        return mChampions.size();
    }

    private void displayImage(String url, ImageView imageView) {
        if (mRiotImage != null) {
            Glide.with(mContext)
                    .load(ImageUtils.BuildChampionIconUrl(url))
                    .circleCrop()
                    .error(R.drawable.ic_placeholder_black_24dp)
                    .placeholder(R.drawable.ic_placeholder_black_24dp)
                    .into(imageView);
        } else {
            Log.i(TAG, "displayImage: null");
        }

    }

    class ChampionHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_champion)
        ImageView ivIcon;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.ll_wrapper_champion)
        LinearLayout llWrapper;

        ChampionHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
