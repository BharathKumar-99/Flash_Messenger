package com.bharath.flashmessenger.util;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageButton;

import com.bharath.flashmessenger.R;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.common.util.concurrent.ListenableFuture;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class UploadStatusPreview extends AppCompatActivity {
    Executor cameraExecutor;
    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;
    ImageCapture imageCapture;
    CameraSelector cameraSelector;
    ImageButton shutter,flash,swap;
    PreviewView previewView;
    Camera camera;
    int flashMode =  ImageCapture.FLASH_MODE_OFF;
    private int lensFacing=CameraSelector.LENS_FACING_BACK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_status_preview);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar6);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
        getSupportActionBar().setTitle("Upload Status");

        cameraExecutor = Executors.newSingleThreadExecutor();

        cameraProviderFuture = ProcessCameraProvider.getInstance(this);
        previewView = findViewById(R.id.previewView);
        shutter = findViewById(R.id.shuter);
        flash = findViewById(R.id.flash);
        swap = findViewById(R.id.swap);
        Intent intent=new Intent(this,uploadStatus.class);


        flash.setOnClickListener(v->{
        switch(flashMode) {
            case ImageCapture.FLASH_MODE_OFF:
                flashMode = ImageCapture.FLASH_MODE_ON;
                flash.setImageResource(R.drawable.ic_baseline_flash_on_24);
                YoYo.with(Techniques.Tada)
                        .duration(700)
                        .playOn(findViewById(R.id.flash));
                break;
            case ImageCapture.FLASH_MODE_ON:
                flashMode = ImageCapture.FLASH_MODE_AUTO;
                flash.setImageResource(R.drawable.ic_baseline_flash_auto_24);
                YoYo.with(Techniques.Tada)
                        .duration(700)

                        .playOn(findViewById(R.id.flash));
                break;
            case ImageCapture.FLASH_MODE_AUTO:
                flashMode = ImageCapture.FLASH_MODE_OFF;
                flash.setImageResource(R.drawable.ic_baseline_flash_off_24);
                YoYo.with(Techniques.Tada)
                        .duration(700)

                        .playOn(findViewById(R.id.flash));
                break;
        }

        ProcessCameraProvider cameraProvider = null;
        try {
            cameraProvider = cameraProviderFuture.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        bindPreview(cameraProvider);

    });



swap.setOnClickListener(v->{
        if (CameraSelector.LENS_FACING_BACK == lensFacing) {
            lensFacing=CameraSelector.LENS_FACING_FRONT;
        } else {
            lensFacing=CameraSelector.LENS_FACING_BACK;
        }
        ProcessCameraProvider cameraProvider = null;
        try {
            cameraProvider = cameraProviderFuture.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        bindPreview(cameraProvider);
    });







        shutter.setOnClickListener(v->{

        SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US);
        File file = new File(getBatchDirectoryName(), mDateFormat.format(new Date()) + ".jpg");

        ImageCapture.OutputFileOptions outputFileOptions =
                new ImageCapture.OutputFileOptions.Builder(file).build();
        imageCapture.takePicture(outputFileOptions, cameraExecutor,
                new ImageCapture.OnImageSavedCallback() {
                    @Override
                    public void onImageSaved(ImageCapture.OutputFileResults outputFileResults) {
                        intent.putExtra("file",file.toString());
                        startActivity(intent);
                        finishAffinity();
                        Log.d("TAG", "onImageSaved: " + file);

                    }
                    @Override
                    public void onError(ImageCaptureException error) {
                        error.printStackTrace();
                        Log.d("TAG", "onImageSaveId: " + error +getBatchDirectoryName());
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
    }, ContextCompat.getMainExecutor(this));


}
    private void bindPreview(@NonNull ProcessCameraProvider cameraProvider) {
        Preview preview = new Preview.Builder()
                .build();

        cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(lensFacing)
                .build();

        preview.setSurfaceProvider(previewView.getSurfaceProvider());

        imageCapture = new ImageCapture.Builder()
                .setCaptureMode(ImageCapture.CAPTURE_MODE_MAXIMIZE_QUALITY)
                .setFlashMode(flashMode)
                .build();
        Log.d("TAG", "bindPreview: "+ flashMode);
        cameraProvider.unbindAll();
        camera = cameraProvider.bindToLifecycle((LifecycleOwner)this, cameraSelector, imageCapture,preview);
        if ( camera.getCameraInfo().hasFlashUnit() ) {
            camera.getCameraControl().enableTorch(false);
        }
//        CameraControl cameraControl = camera.getCameraControl();
//
//        MeteringPointFactory factory = new SurfaceOrientedMeteringPointFactory(width, height);
//        MeteringPoint point = factory.createPoint(x, y);
//        FocusMeteringAction action = new FocusMeteringAction.Builder(point, FocusMeteringAction.FLAG_AF)
//
//                .setAutoCancelDuration(5, TimeUnit.SECONDS)
//                .build();
//
//        ListenableFuture future = cameraControl.startFocusAndMetering(action);
//        future.addListener( () -> {
//            try {
//                FocusMeteringResult result = (FocusMeteringResult) future.get();
//                // process the result
//            } catch (Exception e) {
//            }
//        } , cameraExecutor);

    }
    public String getBatchDirectoryName() {

        String app_folder_path = Environment.getExternalStorageDirectory().toString() + "/FlashMessenger";
        File dir = new File(app_folder_path);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        return app_folder_path;
    }
    public void setFlashMode (int flashMode){

    }

}