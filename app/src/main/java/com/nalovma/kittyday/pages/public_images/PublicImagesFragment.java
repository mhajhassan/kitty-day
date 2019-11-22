package com.nalovma.kittyday.pages.public_images;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.nalovma.kittyday.R;
import com.nalovma.kittyday.base.BaseFragment;
import com.nalovma.kittyday.pages.favorite.FavoriteViewModel;
import com.nalovma.kittyday.data.model.PublicImage;
import com.nalovma.kittyday.data.model.Vote;
import com.nalovma.kittyday.utils.ViewModelFactory;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.Duration;
import com.yuyakaido.android.cardstackview.StackFrom;
import com.yuyakaido.android.cardstackview.SwipeAnimationSetting;
import com.yuyakaido.android.cardstackview.SwipeableMethod;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static com.nalovma.kittyday.utils.Constants.*;
import static com.nalovma.kittyday.utils.Utils.getUniqueID;

public class PublicImagesFragment extends BaseFragment implements CardStackListener {

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    ViewModelFactory viewModelFactory;
    PublicImagesViewModel publicImagesViewModel;
    FavoriteViewModel favoriteViewModel;

    private CardStackLayoutManager manager;
    private PublicImagesAdapter publicImagesAdapter;

    @BindView(R.id.card_stack_view)
    CardStackView cardStackView;

    @BindView(R.id.images_layout)
    ConstraintLayout imagesConstraintLayout;

    @BindView(R.id.tv_error)
    TextView errorTextView;

    @BindView(R.id.loading_view)
    View loadingView;

    @BindView(R.id.adView)
    AdView mAdView;

    private int pageNumber = 0;
    private int itemLimit = 10;


    private PublicImage currentPublicImageItem;
    private String sub_id;

    @Override
    protected int layoutRes() {
        return R.layout.public_images_fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        publicImagesViewModel = ViewModelProviders.of(this, viewModelFactory).get(PublicImagesViewModel.class);
        favoriteViewModel = ViewModelProviders.of(this, viewModelFactory).get(FavoriteViewModel.class);
        manager = new CardStackLayoutManager(getBaseActivity(), this);
        publicImagesAdapter = new PublicImagesAdapter(publicImagesViewModel, favoriteViewModel, this);

        setToolbarBackgroundColor(R.color.colorPrimaryDark);
        setToolbarNavigationIcon(R.drawable.ic_menu_white_24dp);
        setToolbarTitleColor(R.color.color_white);
        setToolbarTitle(getString(R.string.home));

        if (sharedPreferences.getString(UNIQUE_KEY, "").isEmpty()) {
            sharedPreferences.edit().putString(UNIQUE_KEY, getUniqueID()).apply();
        }
        sub_id = sharedPreferences.getString(UNIQUE_KEY, "");
        initializeCardStackView();

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
    }

    @Override
    public void onCardDragging(Direction direction, float ratio) {

    }

    @Override
    public void onCardSwiped(Direction direction) {
        if (manager.getTopPosition() == publicImagesAdapter.getItemCount() - 2) {
            paginate();
        }
    }

    @Override
    public void onCardRewound() {

    }

    @Override
    public void onCardCanceled() {

    }

    @Override
    public void onCardAppeared(View view, int position) {
        currentPublicImageItem = publicImagesAdapter.getPublicImageItem(position);
    }

    @Override
    public void onCardDisappeared(View view, int position) {

    }

    @OnClick(R.id.skip_button)
    public void onSkipButtonClicked(View view) {
        SwipeAnimationSetting setting = new SwipeAnimationSetting.Builder()
                .setDirection(Direction.Left)
                .setDuration(Duration.Slow.duration)
                .setInterpolator(new AccelerateInterpolator())
                .build();
        manager.setSwipeAnimationSetting(setting);
        cardStackView.swipe();
        postVote(currentPublicImageItem.getId(), sub_id, VOTE_SKIP);
        sendAnalyticsData(SKIP);
    }

    @OnClick(R.id.like_button)
    public void onLikeButtonClicked(View view) {
        SwipeAnimationSetting setting = new SwipeAnimationSetting.Builder()
                .setDirection(Direction.Right)
                .setDuration(Duration.Slow.duration)
                .setInterpolator(new AccelerateInterpolator())
                .build();
        manager.setSwipeAnimationSetting(setting);
        cardStackView.swipe();
        postVote(currentPublicImageItem.getId(), sub_id, VOTE_LIKE);
        sendAnalyticsData(LIKE);
    }

    private void postVote(String imageId, String subId, int Value) {
        publicImagesViewModel.postVote(new Vote(imageId, subId, Value));
    }

    private void initializeCardStackView() {
        manager.setStackFrom(StackFrom.None);
        manager.setVisibleCount(itemLimit);
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
        observableViewModel();
        paginate();
    }

    private void paginate() {
        publicImagesViewModel.fetchPublicImages(pageNumber++, itemLimit, ITEMS_ORDER_RANDOM);
    }

    private void observableViewModel() {

        publicImagesViewModel.getPublicImagesLivedata().observe(this, publicImageList -> {
            if (publicImageList != null) {
                imagesConstraintLayout.setVisibility(View.VISIBLE);
            }
        });

        publicImagesViewModel.getLoadError().observe(this, isError -> {
            if (isError != null) if (isError) {
                errorTextView.setVisibility(View.VISIBLE);
                imagesConstraintLayout.setVisibility(View.GONE);
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
                    imagesConstraintLayout.setVisibility(View.GONE);
                }
            }
        });
    }

    private void sendAnalyticsData(String type) {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, type);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, VOTING);
        getFirebaseAnalytics().logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }
}
