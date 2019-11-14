package com.nalovma.kittyday.pages.random_images;

import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;

import com.nalovma.kittyday.R;
import com.nalovma.kittyday.base.BaseFragment;
import com.nalovma.kittyday.utils.ViewModelFactory;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.StackFrom;
import com.yuyakaido.android.cardstackview.SwipeableMethod;

import javax.inject.Inject;

import butterknife.BindView;

public class PublicImagesFragment extends BaseFragment implements CardStackListener {

    @Inject
    ViewModelFactory viewModelFactory;
    PublicImagesViewModel publicImagesViewModel;

    private CardStackLayoutManager manager;
    private PublicImagesAdapter publicImagesAdapter;

    @BindView(R.id.card_stack_view)
    CardStackView cardStackView;

    @BindView(R.id.images_layout)
    RelativeLayout imagesRelativeLayout;

    @BindView(R.id.tv_error)
    TextView errorTextView;
    @BindView(R.id.loading_view)
    View loadingView;

    @Override
    protected int layoutRes() {
        return R.layout.public_images_fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        publicImagesViewModel = ViewModelProviders.of(this, viewModelFactory).get(PublicImagesViewModel.class);
        manager = new CardStackLayoutManager(getBaseActivity(), this);
        publicImagesAdapter = new PublicImagesAdapter(publicImagesViewModel, this);
        initializeCardStackView();
    }

    @Override
    public void onCardDragging(Direction direction, float ratio) {

    }

    @Override
    public void onCardSwiped(Direction direction) {

    }

    @Override
    public void onCardRewound() {

    }

    @Override
    public void onCardCanceled() {

    }

    @Override
    public void onCardAppeared(View view, int position) {

    }

    @Override
    public void onCardDisappeared(View view, int position) {

    }

    private void initializeCardStackView() {
        manager.setStackFrom(StackFrom.None);
        manager.setVisibleCount(3);
        manager.setTranslationInterval(8.0f);
        manager.setScaleInterval(0.95f);
        manager.setSwipeThreshold(0.3f);
        manager.setMaxDegree(20.0f);
        manager.setDirections(Direction.HORIZONTAL);
        manager.setCanScrollHorizontal(true);
        manager.setCanScrollVertical(true);
        manager.setSwipeableMethod(SwipeableMethod.AutomaticAndManual);
        manager.setOverlayInterpolator(new LinearInterpolator());
        cardStackView.setLayoutManager(manager);
        cardStackView.setAdapter(publicImagesAdapter);
        observableViewModel("0", "10", "RANDOM");
    }

    private void observableViewModel(String page, String limit, String order) {
        publicImagesViewModel.fetchPublicImages(page, limit, order);
        publicImagesViewModel.getPublicImagesLivedata().observe(this, publicImageList -> {
            if (publicImageList != null) imagesRelativeLayout.setVisibility(View.VISIBLE);
        });

        publicImagesViewModel.getLoadError().observe(this, isError -> {
            if (isError != null) if (isError) {
                errorTextView.setVisibility(View.VISIBLE);
                imagesRelativeLayout.setVisibility(View.GONE);
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
                    imagesRelativeLayout.setVisibility(View.GONE);
                }
            }
        });
    }
}
