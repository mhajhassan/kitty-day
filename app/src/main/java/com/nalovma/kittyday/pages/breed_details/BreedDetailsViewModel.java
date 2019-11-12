package com.nalovma.kittyday.pages.breed_details;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nalovma.kittyday.data.model.Breed;

import javax.inject.Inject;

public class BreedDetailsViewModel extends ViewModel {

    private final MutableLiveData<Breed> selectedBreed = new MutableLiveData<>();

    @Inject
    public BreedDetailsViewModel() {

    }

    public LiveData<Breed> getSelectedBreed() {
        return selectedBreed;
    }

    public void setSelectedBreed(Breed breed) {
        selectedBreed.setValue(breed);
    }
}
