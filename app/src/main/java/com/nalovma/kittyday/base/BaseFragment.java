package com.nalovma.kittyday.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.nalovma.kittyday.pages.main.MainActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerFragment;

public abstract class BaseFragment extends DaggerFragment {

    private Unbinder unbinder;
    private AppCompatActivity activity;

    @LayoutRes
    protected abstract int layoutRes();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(layoutRes(), container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (AppCompatActivity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    public AppCompatActivity getBaseActivity() {
        return activity;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
    }

    protected void setToolbarTitleColor(@ColorRes int color) {
        if (!(getActivity() instanceof MainActivity)) {
            return;
        }
        ((MainActivity) getActivity()).setToolbarTitleColor(color);
    }

    protected void setToolbarBackgroundColor(@ColorRes int color) {
        if (!(getActivity() instanceof MainActivity)) {
            return;
        }
        ((MainActivity) getActivity()).setToolbarBackgroundColor(color);
    }

    protected void setToolbarBackgroundDrawable(@DrawableRes int drawable) {
        if (!(getActivity() instanceof MainActivity)) {
            return;
        }
        ((MainActivity) getActivity()).setToolbarBackgroundDrawable(drawable);
    }

    protected void setToolbarNavigationIcon(@DrawableRes int drawable) {
        if (!(getActivity() instanceof MainActivity)) {
            return;
        }
        ((MainActivity) getActivity()).setToolbarNavigationIcon(drawable);
    }

    protected void setToolbarTitle(String message) {
        if (!(getActivity() instanceof MainActivity)) {
            return;
        }
        ((MainActivity) getActivity()).setToolbarTitle(message);
    }

    public FirebaseAnalytics getFirebaseAnalytics(){
        // Obtain the FirebaseAnalytics instance.
        return FirebaseAnalytics.getInstance(getBaseActivity());
    }
}