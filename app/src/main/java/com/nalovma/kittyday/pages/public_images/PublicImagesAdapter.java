package com.nalovma.kittyday.pages.public_images;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class PublicImagesAdapter extends RecyclerView.Adapter<PublicImagesAdapter.ViewHolder> {

    private final List<PublicImage> data = new ArrayList<>();
    private Context context;

    PublicImagesAdapter(PublicImagesViewModel viewModel, LifecycleOwner lifecycleOwner) {

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

    public String getImageId(int position) {
        return data.get(position).getId();
    }

    static final class ViewHolder extends RecyclerView.ViewHolder {

        PublicImage publicImage;

        @BindView(R.id.cat_image)
        RoundedImageView catImageView;

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
        }
    }
}
