package com.nalovma.kittyday.pages.favorite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.makeramen.roundedimageview.RoundedImageView;
import com.nalovma.kittyday.R;
import com.nalovma.kittyday.data.model.PublicImage;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {

    private final List<PublicImage> data = new ArrayList<>();
    private Context context;

    FavoriteAdapter(FavoriteViewModel viewModel, LifecycleOwner lifecycleOwner) {

        viewModel.getFavoriteImagesLiveData().observe(lifecycleOwner, catImageList -> {
            data.clear();
            if (catImageList != null) {
                data.addAll(catImageList);
                notifyDataSetChanged();
            }
        });
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_list_item, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(data.get(position), context);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static final class ViewHolder extends RecyclerView.ViewHolder {

        PublicImage publicImage;

        @BindView(R.id.iv_fav_image)
        ImageView favoriteImage;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(PublicImage publicImage, Context context) {
            this.publicImage = publicImage;
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .error(R.drawable.cat);

            Glide.with(context)
                    .load(publicImage.getUrl())
                    .apply(options)
                    .into(favoriteImage);
        }
    }
}
