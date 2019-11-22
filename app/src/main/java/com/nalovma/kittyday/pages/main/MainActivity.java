package com.nalovma.kittyday.pages.main;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.nalovma.kittyday.R;
import com.nalovma.kittyday.base.BaseActivity;
import com.nalovma.kittyday.pages.breeds.BreedsFragment;
import com.nalovma.kittyday.pages.favorite.FavoriteFragment;
import com.nalovma.kittyday.pages.public_images.PublicImagesFragment;
import com.nalovma.kittyday.pages.search_images.SearchImagesFragment;
import com.nalovma.kittyday.pages.upload_image.UploadImageFragment;

import java.util.Objects;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements MainNavigation {

    private NavigationDrawerFragment mNavigationDrawerFragment;

    @BindView(R.id.main_drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.main_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.main_toolbar_title)
    TextView mToolbarTitleTextView;

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(mToolbar);
        mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.main_drawer_fragment);
        Objects.requireNonNull(mNavigationDrawerFragment).setUp(R.id.main_drawer_fragment, mDrawerLayout, mToolbar);
        if (savedInstanceState == null)
            switchFragment(new PublicImagesFragment());

    }

    @Override
    public void goToBreeds() {
        switchFragment(new BreedsFragment());
    }

    @Override
    public void goToPublicImages() {
        switchFragment(new PublicImagesFragment());
    }

    @Override
    public void goToFavorites() {
        switchFragment(new FavoriteFragment());
    }

    @Override
    public void goToUploadImage() {
        switchFragment(new UploadImageFragment());
    }

    @Override
    public void goToSearch() {
        switchFragment(new SearchImagesFragment());
    }

    public void setToolbarBackgroundDrawable(@DrawableRes int drawable) {
        mToolbar.setBackground(ContextCompat.getDrawable(this, drawable));
    }

    public void setToolbarBackgroundColor(@ColorRes int color) {
        mToolbar.setBackgroundColor(ContextCompat.getColor(this, color));
    }

    public void setToolbarTitleColor(@ColorRes int color) {
        mToolbarTitleTextView.setTextColor(ContextCompat.getColor(this, color));
    }

    public void setToolbarNavigationIcon(@DrawableRes int drawable) {
        mToolbar.setNavigationIcon(drawable);
    }

    public void setToolbarTitle(String title) {
        mToolbarTitleTextView.setText(title);
    }

    private void switchFragment(@NonNull Fragment fragment) {
        switchFragment(fragment, true);
    }

    private void switchFragment(@NonNull Fragment fragment, boolean clearBackStack) {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.main_container);
        if (fragment.getClass().isInstance(currentFragment)) {
            return;
        }
        if (clearBackStack) {
            clearFullBackStackButFirst(this);
        }
        addFragmentToBackStack(fragment);
    }

    private void addFragmentToBackStack(@NonNull Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, fragment, fragment.getTag())
                .addToBackStack(fragment.getClass().getSimpleName())
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (mNavigationDrawerFragment.isDrawerOpen()) {
            mNavigationDrawerFragment.closeDrawer();
            return;
        }
        if (getSupportFragmentManager().getBackStackEntryCount() <= 1) {
            finish();
            return;
        }
        mNavigationDrawerFragment.setButtonActivated(mNavigationDrawerFragment.mDrawerPublicImagesButton);
        super.onBackPressed();
    }

    public void clearFullBackStackButFirst(AppCompatActivity activity) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 1) {
            // clear the fragment back stack
            FragmentManager.BackStackEntry first = fragmentManager.getBackStackEntryAt(1);
            fragmentManager.popBackStackImmediate(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }
}

