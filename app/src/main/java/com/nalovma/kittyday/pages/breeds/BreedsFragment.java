package com.nalovma.kittyday.pages.breeds;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nalovma.kittyday.R;
import com.nalovma.kittyday.base.BaseFragment;
import com.nalovma.kittyday.data.model.Breed;
import com.nalovma.kittyday.pages.breed_details.BreedDetailsFragment;
import com.nalovma.kittyday.pages.breed_details.BreedDetailsViewModel;
import com.nalovma.kittyday.utils.ViewModelFactory;

import javax.inject.Inject;

import butterknife.BindView;

public class BreedsFragment extends BaseFragment implements BreedsListener {


    @BindView(R.id.recyclerView)
    RecyclerView listView;
    @BindView(R.id.tv_error)
    TextView errorTextView;
    @BindView(R.id.loading_view)
    View loadingView;

    @Inject
    ViewModelFactory viewModelFactory;
    private BreedsViewModel breedsViewModel;

    @Override
    protected int layoutRes() {
        return R.layout.breeds_fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        breedsViewModel = ViewModelProviders.of(this, viewModelFactory).get(BreedsViewModel.class);

        listView.addItemDecoration(new DividerItemDecoration(getBaseActivity(), 0));
        listView.setAdapter(new BreedsAdapter(breedsViewModel, this, this));
        listView.setLayoutManager(new LinearLayoutManager(getContext()));

        observableViewModel();
    }

    @Override
    public void onBreedSelected(Breed breed) {
        BreedDetailsViewModel detailsViewModel = ViewModelProviders.of(getBaseActivity(), viewModelFactory).get(BreedDetailsViewModel.class);
        detailsViewModel.setSelectedBreed(breed);
        getBaseActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new BreedDetailsFragment())
                .addToBackStack(null).commit();
    }

    private void observableViewModel() {
        breedsViewModel.getBreedsLivedata().observe(this, breed -> {
            if (breed != null) listView.setVisibility(View.VISIBLE);
        });

        breedsViewModel.getLoadError().observe(this, isError -> {
            if (isError != null) if (isError) {
                errorTextView.setVisibility(View.VISIBLE);
                listView.setVisibility(View.GONE);
                errorTextView.setText("An Error Occurred While Loading Data!");
            } else {
                errorTextView.setVisibility(View.GONE);
                errorTextView.setText(null);
            }
        });

        breedsViewModel.getLoading().observe(this, isLoading -> {
            if (isLoading != null) {
                loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                if (isLoading) {
                    errorTextView.setVisibility(View.GONE);
                    listView.setVisibility(View.GONE);
                }
            }
        });
    }
}
