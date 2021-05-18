package com.bharath.flashmessenger.ViewModel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;
import android.util.Size;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bharath.flashmessenger.R;
import com.bharath.flashmessenger.Repo.StatusRepo;
import com.bharath.flashmessenger.Setup.Model.Selectormodel;
import com.bumptech.glide.Glide;
import com.google.common.util.concurrent.ListenableFuture;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class StatusViewModel extends ViewModel {
    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;
@SuppressLint("StaticFieldLeak")
Context context;
    ImageCapture imageCapture;
    CameraSelector cameraSelector;
    ImageButton shutter,flash,swap;
    MutableLiveData<List<Selectormodel>> Status;
    PopupWindow mpopup;
    @SuppressLint("StaticFieldLeak")
    PreviewView previewView;

    public MutableLiveData<List<Selectormodel>> getname(){
        StatusRepo repo=new StatusRepo();
        Status=repo.getNames();
        return Status;
    }


    public void statusupload(Context context){
        Executor cameraExecutor = Executors.newSingleThreadExecutor();
this.context=context;
        cameraProviderFuture = ProcessCameraProvider.getInstance(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View popUpView = inflater.inflate(R.layout.statuspopup,

                    null); // inflating popup layout
            mpopup = new PopupWindow(popUpView, Toolbar.LayoutParams.FILL_PARENT,
                    Toolbar.LayoutParams.WRAP_CONTENT, true); // Creation of popup
            mpopup.setAnimationStyle(android.R.style.Animation_Dialog);
            mpopup.showAtLocation(popUpView, Gravity.FILL, 0, 0);
        previewView = popUpView.findViewById(R.id.previewView);
        shutter = popUpView.findViewById(R.id.shuter);
        flash = popUpView.findViewById(R.id.flash);
        swap = popUpView.findViewById(R.id.swap);


        shutter.setOnClickListener(v->{
            SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US);
            File file = new File(getBatchDirectoryName(), mDateFormat.format(new Date()) + ".jpg");

            ImageCapture.OutputFileOptions outputFileOptions =
                    new ImageCapture.OutputFileOptions.Builder(file).build();
            imageCapture.takePicture(outputFileOptions, cameraExecutor,
                    new ImageCapture.OnImageSavedCallback() {
                        @Override
                        public void onImageSaved(ImageCapture.OutputFileResults outputFileResults) {
                            Log.d("TAG", "onImageSaved: ");
                        }

                        @Override
                        public void onError(ImageCaptureException error) {
                            error.printStackTrace();
                            Log.d("TAG", "onImageSaveId: ");
                        }
                    });
        });





        cameraProviderFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                bindPreview(cameraProvider);
            } catch (ExecutionException | InterruptedException e) {
                // No errors need to be handled for this Future.
                // This should never be reached.
            }
        }, ContextCompat.getMainExecutor(context));


        }
    private void bindPreview(@NonNull ProcessCameraProvider cameraProvider) {
        Preview preview = new Preview.Builder()
                .build();

        cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build();

        preview.setSurfaceProvider(previewView.getSurfaceProvider());

        imageCapture =
                new ImageCapture.Builder()
                        .build();

       Camera camera= cameraProvider.bindToLifecycle((LifecycleOwner) context, cameraSelector, preview);


    }
    public String getBatchDirectoryName() {

        String app_folder_path = Environment.getExternalStorageDirectory().toString() + "/FlashMessanger";
        File dir = new File(app_folder_path);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        return app_folder_path;
    }
}
