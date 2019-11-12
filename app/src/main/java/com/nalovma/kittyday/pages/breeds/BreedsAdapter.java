package com.nalovma.kittyday.pages.breeds;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.nalovma.kittyday.R;
import com.nalovma.kittyday.data.model.Breed;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BreedsAdapter extends RecyclerView.Adapter<BreedsAdapter.BreedViewHolder> {

    private BreedsListener breedsListener;
    private final List<Breed> data = new ArrayList<>();

    BreedsAdapter(BreedsViewModel viewModel, LifecycleOwner lifecycleOwner, BreedsListener breedsListener) {
        this.breedsListener = breedsListener;
        viewModel.getBreedsLivedata().observe(lifecycleOwner, breeds -> {
            data.clear();
            if (breeds != null) {
                data.addAll(breeds);
                notifyDataSetChanged();
            }
        });
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public BreedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.breed_list_item, parent, false);
        return new BreedViewHolder(view, breedsListener);
    }

    @Override
    public void onBindViewHolder(@NonNull BreedViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static final class BreedViewHolder extends RecyclerView.ViewHolder {

        private Breed breed;

        @BindView(R.id.tv_name)
        TextView breedNameTextView;
        @BindView(R.id.tv_origin)
        TextView breedOrigionTextView;

        BreedViewHolder(View itemView, BreedsListener breedsListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
                if (breed != null) {
                    breedsListener.onBreedSelected(breed);
                }
            });
        }

        void bind(Breed breed) {
            this.breed = breed;
            breedNameTextView.setText(breed.getName());
            breedOrigionTextView.setText(breed.getOrigin());
        }
    }
}
