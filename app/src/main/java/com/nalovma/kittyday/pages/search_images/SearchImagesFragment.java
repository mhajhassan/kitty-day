package com.nalovma.kittyday.pages.search_images;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
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
import com.nalovma.kittyday.data.model.Category;
import com.nalovma.kittyday.pages.breeds.BreedsViewModel;
import com.nalovma.kittyday.pages.favorite.FavoriteViewModel;
import com.nalovma.kittyday.pages.public_images.PublicImagesAdapter;
import com.nalovma.kittyday.pages.public_images.PublicImagesViewModel;
import com.nalovma.kittyday.utils.ViewModelFactory;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

import static com.nalovma.kittyday.utils.Constants.*;

public class SearchImagesFragment extends BaseFragment {

    @BindView(R.id.recyclerView)
    RecyclerView listView;
    @BindView(R.id.tv_error)
    TextView errorTextView;
    @BindView(R.id.loading_view)
    View loadingView;

    @BindView(R.id.breeds_spinner)
    Spinner breedsSpinner;

    @BindView(R.id.categories_spinner)
    Spinner categoriesSpinner;

    private List<Breed> breedList = new ArrayList<>();
    private List<Category> categoryList = new ArrayList<>();

    @Inject
    ViewModelFactory viewModelFactory;
    private BreedsViewModel breedsViewModel;
    private PublicImagesViewModel publicImagesViewModel;
    private FavoriteViewModel favoriteViewModel;
    private CategoriesViewModel categoriesViewModel;

    private String selectedBreedId;
    private String selectedCategoryId;

    private PublicImagesAdapter publicImagesAdapter;


    @Override
    protected int layoutRes() {
        return R.layout.search_images_fragment;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        breedsViewModel = ViewModelProviders.of(this, viewModelFactory).get(BreedsViewModel.class);
        publicImagesViewModel = ViewModelProviders.of(this, viewModelFactory).get(PublicImagesViewModel.class);
        favoriteViewModel = ViewModelProviders.of(this, viewModelFactory).get(FavoriteViewModel.class);
        categoriesViewModel = ViewModelProviders.of(this, viewModelFactory).get(CategoriesViewModel.class);


        listView.addItemDecoration(new DividerItemDecoration(getBaseActivity(), 0));
        publicImagesAdapter = new PublicImagesAdapter(publicImagesViewModel, favoriteViewModel, this);
        listView.setAdapter(publicImagesAdapter);
        listView.setLayoutManager(new LinearLayoutManager(getContext()));

        setToolbarBackgroundColor(R.color.colorPrimaryDark);
        setToolbarNavigationIcon(R.drawable.ic_menu_white_24dp);
        setToolbarTitleColor(R.color.color_white);
        setToolbarTitle(getString(R.string.search));
        initTheBreedsSpinner();
        initTheCategoriesSpinner();

        breedsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (breedList.size() > 0) {

                    selectedBreedId = breedList.get(position).getId();
                    observableViewModel(selectedBreedId, "");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        categoriesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (breedList.size() > 0) {

                    selectedCategoryId = categoryList.get(position).getId().toString();
                    observableViewModel("", selectedCategoryId);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void observableViewModel(String breed, String category) {
        publicImagesViewModel.fetchPublicImages(0, 10, ITEMS_ORDER_DES, breed, category);
        publicImagesViewModel.getPublicImagesLivedata().observe(this, publicImageList -> {
            if (publicImageList != null) {
                listView.setVisibility(View.VISIBLE);
            }
        });

        publicImagesViewModel.getLoadError().observe(this, isError -> {
            if (isError != null) if (isError) {
                errorTextView.setVisibility(View.VISIBLE);
                listView.setVisibility(View.GONE);
                errorTextView.setText("An Error Occurred While Loading Data!");
            } else {
                errorTextView.setVisibility(View.GONE);
                errorTextView.setText(null);
            }
        });

        publicImagesViewModel.getLoading().observe(this, isLoading -> {
            if (isLoading != null) {
                loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                if (isLoading) {
                    errorTextView.setVisibility(View.GONE);
                    listView.setVisibility(View.GONE);
                }
            }
        });
    }

    private void initTheBreedsSpinner() {
        breedsViewModel.fetchBreeds();
        breedsViewModel.getBreedsLivedata().observe(this, breeds -> {
            if (breeds != null) {
                breedList = breeds;
                ArrayAdapter breedAdapter = new ArrayAdapter(getBaseActivity(), R.layout.spinner_item, breedList);
                breedsSpinner.setAdapter(breedAdapter);
                selectedBreedId = breedList.get(0).getId();
            }
        });

    }

    private void initTheCategoriesSpinner() {
        categoriesViewModel.fetchCategories();
        categoriesViewModel.getCategoriesLivedata().observe(this, categories -> {
            if (categories != null) {
                categoryList = categories;
                ArrayAdapter categoriesAdapter = new ArrayAdapter(getBaseActivity(), R.layout.spinner_item, categoryList);
                categoriesSpinner.setAdapter(categoriesAdapter);
                selectedCategoryId = categoryList.get(0).getId().toString();
            }
        });
    }

}
