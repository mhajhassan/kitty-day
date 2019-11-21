package com.nalovma.kittyday.pages.favorite;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nalovma.kittyday.R;
import com.nalovma.kittyday.base.BaseFragment;
import com.nalovma.kittyday.data.model.PublicImage;
import com.nalovma.kittyday.utils.ViewModelFactory;

import javax.inject.Inject;

import butterknife.BindView;

public class FavoriteFragment extends BaseFragment {

    @BindView(R.id.recyclerView)
    RecyclerView listView;

    @Inject
    ViewModelFactory viewModelFactory;
    FavoriteViewModel favoriteViewModel;
    FavoriteAdapter favoriteAdapter;

    @Override
    protected int layoutRes() {
        return R.layout.favorite_fragment;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        favoriteViewModel = ViewModelProviders.of(this, viewModelFactory).get(FavoriteViewModel.class);
        listView.addItemDecoration(new DividerItemDecoration(getBaseActivity(), 0));
        favoriteAdapter = new FavoriteAdapter(favoriteViewModel, this);
        listView.setAdapter(favoriteAdapter);
        listView.setLayoutManager(new LinearLayoutManager(getContext()));

        setToolbarBackgroundColor(R.color.colorPrimaryDark);
        setToolbarNavigationIcon(R.drawable.ic_menu_white_24dp);
        setToolbarTitleColor(R.color.color_white);
        setToolbarTitle(getString(R.string.favorite));
        fetchFavorite();
    }

    private void fetchFavorite() {
        favoriteViewModel.loadFavoriteImages();
        favoriteViewModel.getFavoriteImagesLiveData().observe(this, favoriteList -> {
            if (favoriteList != null) {
                listView.setVisibility(View.VISIBLE);
            }
        });
    }
}
