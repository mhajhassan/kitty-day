package com.nalovma.kittyday.pages.upload_image;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.nalovma.kittyday.R;
import com.nalovma.kittyday.base.BaseFragment;
import com.nalovma.kittyday.utils.ViewModelFactory;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickCancel;
import com.vansuita.pickimage.listeners.IPickResult;

import java.io.File;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.nalovma.kittyday.utils.Constants.IMAGE;


public class UploadImageFragment extends BaseFragment {

    @Inject
    ViewModelFactory viewModelFactory;
    UploadImageViewModel uploadImageViewModel;

    @BindView(R.id.captured_image)
    ImageView capturedImage;

    @BindView(R.id.upload_images_button)
    Button uploadImagesButton;

    @BindView(R.id.loading_view)
    View loadingView;

    private File imageFile;

    @Override
    protected int layoutRes() {
        return R.layout.upload_image_fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        uploadImageViewModel = ViewModelProviders.of(this, viewModelFactory).get(UploadImageViewModel.class);

        setToolbarBackgroundColor(R.color.colorPrimaryDark);
        setToolbarNavigationIcon(R.drawable.ic_menu_white_24dp);
        setToolbarTitleColor(R.color.color_white);
        setToolbarTitle(getString(R.string.upload_images));
        uploadImagesButton.setVisibility(View.GONE);
        loadingView.setVisibility(View.GONE);
    }

    @OnClick(R.id.camera_button)
    public void onCameraButtonClicked(View view) {
        PickImageDialog.build(new PickSetup())
                .setOnPickResult(new IPickResult() {
                    @Override
                    public void onPickResult(PickResult r) {
                        capturedImage.setImageBitmap(r.getBitmap());
                        imageFile = new File(r.getPath());
                        uploadImagesButton.setVisibility(View.VISIBLE);
                    }
                })
                .setOnPickCancel(new IPickCancel() {
                    @Override
                    public void onCancelClick() {
                        uploadImagesButton.setVisibility(View.GONE);
                    }
                }).show(getFragmentManager());
    }

    @OnClick(R.id.upload_images_button)
    public void onUploadImageButtonClicked(View view) {
        uploadImagesButton.setVisibility(View.GONE);
        RequestBody sub_id = RequestBody.create("user_1234", MediaType.parse("multipart/form-data"));
        RequestBody requestFile = RequestBody.create(imageFile, MediaType.parse("multipart/form-data"));
        MultipartBody.Part image = MultipartBody.Part.createFormData("file", imageFile.getName(), requestFile);
        uploadImageViewModel.uploadImage(sub_id, image);
        //send info about uploaded images
        sendAnalyticsData();


        uploadImageViewModel.getLoadError().observe(this, isError -> {
            if (isError != null) if (isError) {
                uploadImagesButton.setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(), getResources().getString(R.string.upload_error), Toast.LENGTH_SHORT).show();
            } else {
                uploadImagesButton.setVisibility(View.GONE);
            }
        });

        uploadImageViewModel.getLoading().observe(this, isLoading -> {
            if (isLoading != null) {
                loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                if (isLoading) {
                    uploadImagesButton.setVisibility(View.GONE);
                }
            }
        });
    }

    private void sendAnalyticsData() {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, imageFile.getName());
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, IMAGE);
        getFirebaseAnalytics().logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }


}
