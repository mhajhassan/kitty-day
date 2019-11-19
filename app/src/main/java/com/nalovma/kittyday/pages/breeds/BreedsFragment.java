package com.nalovma.kittyday.pages.breeds;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
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


    //todo: fix the savedInstanceState of the fragment for searching
    @BindView(R.id.recyclerView)
    RecyclerView listView;
    @BindView(R.id.tv_error)
    TextView errorTextView;
    @BindView(R.id.loading_view)
    View loadingView;

    @BindView(R.id.search_view)
    SearchView searchView;

    @Inject
    ViewModelFactory viewModelFactory;
    private BreedsViewModel breedsViewModel;
    private String mSearchString;
    private static final String SEARCH_KEY = "search_key";
    private BreedsAdapter breedsAdapter;

    @Override
    protected int layoutRes() {
        return R.layout.breeds_fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        breedsViewModel = ViewModelProviders.of(this, viewModelFactory).get(BreedsViewModel.class);

        listView.addItemDecoration(new DividerItemDecoration(getBaseActivity(), 0));
        breedsAdapter = new BreedsAdapter(this);
        listView.setAdapter(breedsAdapter);
        listView.setLayoutManager(new LinearLayoutManager(getContext()));

        setToolbarBackgroundColor(R.color.colorPrimaryDark);
        setToolbarNavigationIcon(R.drawable.ic_menu_white_24dp);
        setToolbarTitleColor(R.color.color_white);
        setToolbarTitle(getString(R.string.breeds));

        if (savedInstanceState != null) {
            mSearchString = savedInstanceState.getString(SEARCH_KEY);
            searchView.setQuery(mSearchString, true);
            searchView.clearFocus();
            Log.d("logs", "savedInstanceState - true");

        } else {
            initSearchView();
            fetchBreeds();
            Log.d("logs", "savedInstanceState - false");
        }


    }

    @Override
    public void onBreedSelected(Breed breed) {
        BreedDetailsViewModel detailsViewModel = ViewModelProviders.of(getBaseActivity(), viewModelFactory).get(BreedDetailsViewModel.class);
        detailsViewModel.setSelectedBreed(breed);
        getBaseActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new BreedDetailsFragment())
                .addToBackStack(null).commit();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mSearchString = searchView.getQuery().toString();
        outState.putString(SEARCH_KEY, mSearchString);
    }

    private void fetchBreeds() {
        breedsViewModel.fetchBreeds();
        breedsViewModel.getBreedsLivedata().observe(this, breeds -> {
            if (breeds != null) {
                breedsAdapter.setData(breeds);
                listView.setVisibility(View.VISIBLE);
            }
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

    private void fetchBreedByName(String name) {
        breedsViewModel.fetchBreedByName(name);
        breedsViewModel.getBreedsLivedata().observe(this, breeds -> {
            if (breeds != null) {
                breedsAdapter.setData(breeds);
                listView.setVisibility(View.VISIBLE);
            }
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

    private void initSearchView() {

        Log.d("logs", "initSearchView");
        // change hint and text color
        EditText searchEditText = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(getResources().getColor(R.color.color_white));
        searchEditText.setHintTextColor(getResources().getColor(R.color.white_OP_87));

        // change the close icon
        ImageView closeButtonImage = searchView.findViewById(androidx.appcompat.R.id.search_close_btn);
        closeButtonImage.setImageResource(R.drawable.ic_close_white_24dp);

        // change the search icon
        ImageView searchButtonImage = searchView.findViewById(androidx.appcompat.R.id.search_button);
        searchButtonImage.setImageResource(R.drawable.ic_search_white_24dp);

        searchView.setIconified(false);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!newText.isEmpty()) {
                    Log.d("logs", "onQueryTextChange - true");
                    fetchBreedByName(newText);
                } else {
                    Log.d("logs", "onQueryTextChange - false");
                    fetchBreeds();
                }
                return false;
            }
        });

    }
}
