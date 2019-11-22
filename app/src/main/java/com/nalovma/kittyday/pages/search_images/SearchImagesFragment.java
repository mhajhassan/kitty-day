package com.nalovma.kittyday.pages.search_images;

import android.os.Bundle;
import android.util.Log;
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
import com.nalovma.kittyday.pages.breeds.BreedsAdapter;
import com.nalovma.kittyday.pages.breeds.BreedsViewModel;
import com.nalovma.kittyday.pages.favorite.FavoriteViewModel;
import com.nalovma.kittyday.pages.public_images.PublicImagesAdapter;
import com.nalovma.kittyday.pages.public_images.PublicImagesViewModel;
import com.nalovma.kittyday.utils.ViewModelFactory;

import javax.inject.Inject;

import butterknife.BindView;

import static com.nalovma.kittyday.utils.Constants.ITEMS_ORDER;

public class SearchImagesFragment extends BaseFragment {

    @BindView(R.id.recyclerView)
    RecyclerView listView;
    @BindView(R.id.tv_error)
    TextView errorTextView;
    @BindView(R.id.loading_view)
    View loadingView;

    @Inject
    ViewModelFactory viewModelFactory;
    private BreedsViewModel breedsViewModel;
    private PublicImagesViewModel publicImagesViewModel;
    private FavoriteViewModel favoriteViewModel;
    private CategoriesViewModel categoriesViewModel;


    private BreedsAdapter breedsAdapter;
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
        breedsAdapter = new BreedsAdapter();
        publicImagesAdapter = new PublicImagesAdapter(publicImagesViewModel, favoriteViewModel, this);
        listView.setAdapter(publicImagesAdapter);
        listView.setLayoutManager(new LinearLayoutManager(getContext()));

        setToolbarBackgroundColor(R.color.colorPrimaryDark);
        setToolbarNavigationIcon(R.drawable.ic_menu_white_24dp);
        setToolbarTitleColor(R.color.color_white);
        setToolbarTitle(getString(R.string.search));
        observableViewModel();

    }

    private void observableViewModel() {
        publicImagesViewModel.fetchPublicImages(0, 10, ITEMS_ORDER);
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


}
