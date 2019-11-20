package com.nalovma.kittyday.pages.public_images;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.makeramen.roundedimageview.RoundedImageView;
import com.nalovma.kittyday.R;
import com.nalovma.kittyday.data.model.PublicImage;
import com.nalovma.kittyday.pages.favorite.FavoriteViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PublicImagesAdapter extends RecyclerView.Adapter<PublicImagesAdapter.ViewHolder> {

    private final List<PublicImage> data = new ArrayList<>();
    private Context context;
    private FavoriteViewModel favoriteViewModel;

    PublicImagesAdapter(PublicImagesViewModel viewModel, FavoriteViewModel favoriteViewModel, LifecycleOwner lifecycleOwner) {
        this.favoriteViewModel = favoriteViewModel;
        viewModel.getPublicImagesLivedata().observe(lifecycleOwner, catImageList -> {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
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

    public PublicImage getPublicImageItem(int position) {
        return data.get(position);
    }

    public final class ViewHolder extends RecyclerView.ViewHolder {

        PublicImage publicImage;

        @BindView(R.id.cat_image)
        RoundedImageView catImageView;

        @BindView(R.id.iv_fav_button)
        ToggleButton favoriteButton;

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
                    .into(catImageView);

            favoriteButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        favoriteViewModel.insertFavoriteImage(publicImage);
                        Log.d("PublicImagesAdapter","added to favorite: "+publicImage.getId());
                    } else {
                        favoriteViewModel.deleteFavoriteImage(publicImage);
                        Log.d("PublicImagesAdapter","removed to favorite: "+publicImage.getId());
                    }
                }
            });
        }
    }
}
