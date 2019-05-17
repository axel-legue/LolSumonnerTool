package com.legue.axel.lolsummonertool.adapter;

import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
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
import com.legue.axel.lolsummonertool.database.model.mastery.Mastery;
import com.legue.axel.lolsummonertool.database.model.mastery.MasteryImage;
import com.legue.axel.lolsummonertool.database.viewmodel.MasteryViewModel;
import com.legue.axel.lolsummonertool.utils.ImageUtils;
import com.legue.axel.lolsummonertool.wiki.fragment.WikiMasteryFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MasteriesAdapter extends RecyclerView.Adapter<MasteriesAdapter.MasteryHolder> {

    private static final String TAG = MasteriesAdapter.class.getName();

    public interface MasteryListener {
        void masterySelected(int position, Mastery mastery);
    }

    private Context mContext;
    private List<Mastery> mMasterys;
    private MasteriesAdapter.MasteryListener mMasteryListener;
    private WikiMasteryFragment mFragment;
    private MasteryImage mMasteryImage;

    public MasteriesAdapter(Context context, List<Mastery> masterys, MasteryListener masteryListener, WikiMasteryFragment fragment) {
        mContext = context;
        mMasterys = masterys;
        mMasteryListener = masteryListener;
        mFragment = fragment;
    }

    @NonNull
    @Override
    public MasteryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_mastery, parent, false);
        return new MasteryHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MasteryHolder holder, int position) {
        final Mastery mastery = mMasterys.get(position);

        if (mastery != null) {
            MasteryViewModel masteryViewModel = ViewModelProviders.of(mFragment).get(MasteryViewModel.class);
            masteryViewModel.getMasteryImage(mastery.id).observe(mFragment, masteryImage -> {
                if (masteryImage != null) {
                    mMasteryImage = masteryImage;
                    displayImage(mMasteryImage.full, holder.ivIcon, holder.pbMastery);
                }
            });

            if (mastery.name != null && !TextUtils.isEmpty(mastery.name)) {
                holder.tvName.setText(mastery.name);
            }

            holder.llWrapper.setOnClickListener(v -> {
                mMasteryListener.masterySelected(position, mastery);
            });
        } else {
            Log.i(TAG, "onBindViewHolder: mastery at position " + position + " is null");
        }

    }

    @Override
    public int getItemCount() {
        return mMasterys.size();
    }

    private void displayImage(String url, ImageView imageView, ProgressBar progressBar) {
        Glide.with(mContext)
                .load(ImageUtils.BuildMasteryIconUrl(url))
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
                .circleCrop()
                .error(R.drawable.ic_placeholder_black_24dp)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(imageView);
    }

    class MasteryHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_mastery)
        ImageView ivIcon;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.pb_mastery)
        ProgressBar pbMastery;
        @BindView(R.id.ll_wrapper_mastery)
        LinearLayout llWrapper;

        MasteryHolder(@NonNull View masteryView) {
            super(masteryView);
            ButterKnife.bind(this, masteryView);
        }
    }
}
