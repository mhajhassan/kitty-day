package com.nalovma.kittyday.pages.main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.nalovma.kittyday.R;
import com.nalovma.kittyday.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class NavigationDrawerFragment extends BaseFragment {

    private MainNavigation mMainNavigation;
    private DrawerLayout mDrawerLayout;
    private View mFragmentContainerView;

    @BindView(R.id.breeds_button)
    Button mDrawerBreedsButton;

    @BindView(R.id.public_images_button)
    Button mDrawerPublicImagesButton;

    @Override
    protected int layoutRes() {
        return R.layout.fragment_navigation_drawer;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mMainNavigation = (MainNavigation) context;
    }

    public boolean isDrawerOpen() {
        return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(mFragmentContainerView);
    }

    public void closeDrawer() {
        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawer(mFragmentContainerView);
        }
    }

    private ActionBar getActionBar() {
        return ((AppCompatActivity) getActivity()).getSupportActionBar();
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout, Toolbar toolbar) {
        mFragmentContainerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(getActivity(),
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close) {
        };
        drawerLayout.addDrawerListener(mDrawerToggle);
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDefaultDisplayHomeAsUpEnabled(true);
        }
        mDrawerToggle.syncState();
    }

    @OnClick(R.id.breeds_button)
    public void onBreedsClicked(View view) {
        mMainNavigation.goToBreeds();
        setButtonActivated(mDrawerBreedsButton);
        closeDrawer();
    }

    @OnClick(R.id.public_images_button)
    public void onPublicImagesClicked(View view) {
        mMainNavigation.goToPublicImages();
        setButtonActivated(mDrawerPublicImagesButton);
        closeDrawer();
    }

    public void setButtonActivated(Button buttonActivated) {
        mDrawerPublicImagesButton.setActivated(false);
        mDrawerBreedsButton.setActivated(false);
        buttonActivated.setActivated(true);
    }
}
