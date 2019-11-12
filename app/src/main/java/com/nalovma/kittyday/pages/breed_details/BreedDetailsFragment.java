package com.nalovma.kittyday.pages.breed_details;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

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

    @Inject
    ViewModelFactory viewModelFactory;
    private BreedDetailsViewModel detailsViewModel;

    @Override
    protected int layoutRes() {
        return R.layout.breed_details_fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        detailsViewModel = ViewModelProviders.of(getBaseActivity(), viewModelFactory).get(BreedDetailsViewModel.class);
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
            breedNameTextView.setText(breed.getName());
            breedOrigionTextView.setText(breed.getOrigin());
            breedTemperamentTextView.setText(breed.getTemperament());
            breedLifeSpanTextView.setText(breed.getLifeSpan());
            breedMetricTextView.setText(breed.getWeight().getMetric());
            breedDescriptionTextView.setText(breed.getDescription());
        }
    }

}
