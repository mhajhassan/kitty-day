package com.nalovma.kittyday.pages.breed_details;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nalovma.kittyday.R;
import com.nalovma.kittyday.base.BaseFragment;
import com.nalovma.kittyday.data.model.Breed;
import com.nalovma.kittyday.utils.ViewModelFactory;

import javax.inject.Inject;

import butterknife.BindView;

public class BreedDetailsFragment extends BaseFragment {

    @BindView(R.id.tv_name)
    TextView breedNameTextView;
    @BindView(R.id.tv_origin)
    TextView breedOrigionTextView;
    @BindView(R.id.tv_temperament)
    TextView breedTemperamentTextView;
    @BindView(R.id.tv_life_span)
    TextView breedLifeSpanTextView;
    @BindView(R.id.tv_metric)
    TextView breedMetricTextView;
    @BindView(R.id.tv_description)
    TextView breedDescriptionTextView;

    @BindView(R.id.iv_breed_image)
    ImageView breedImageView;

    @Inject
    ViewModelFactory detailsViewModelFactory;
    private BreedDetailsViewModel detailsViewModel;

    @Override
    protected int layoutRes() {
        return R.layout.breed_details_fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        detailsViewModel = ViewModelProviders.of(getBaseActivity(), detailsViewModelFactory).get(BreedDetailsViewModel.class);
        showDetails();
    }

    private void showDetails() {
        detailsViewModel.getSelectedBreed().observe(this, new Observer<Breed>() {
            @Override
            public void onChanged(Breed breed) {
                showUi(breed);
            }
        });
    }

    private void showUi(Breed breed) {
        if (breed != null) {
            setToolbarTitle(breed.getName());
            breedNameTextView.setText(breed.getName());
            breedOrigionTextView.setText(String.format("Origin: %s", breed.getOrigin()));
            breedTemperamentTextView.setText(String.format("Temperament: %s", breed.getTemperament()));
            breedLifeSpanTextView.setText(String.format("average life span: %s", breed.getLifeSpan()));
            breedMetricTextView.setText(String.format("Weight: %s", breed.getWeight().getMetric()));
            breedDescriptionTextView.setText(breed.getDescription());
            fetchBreedImage(breed.getId(), "0", "3");
        }
    }

    private void fetchBreedImage(String id, String page, String limit) {
        detailsViewModel.fetchCatImage(id, page, limit);
        detailsViewModel.getCatImageLivedata().observe(this, catImageList -> {
            if (catImageList != null) {
                RequestOptions options = new RequestOptions()
                        .centerCrop()
                        .error(R.drawable.cat);
                ;

                Glide.with(this)
                        .load(catImageList.get(0).getUrl())
                        .apply(options)
                        .into(breedImageView);
            }

        });
    }

}
