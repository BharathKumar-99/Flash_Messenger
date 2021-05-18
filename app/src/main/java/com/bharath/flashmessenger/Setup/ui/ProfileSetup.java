
package com.bharath.flashmessenger.Setup.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bharath.flashmessenger.MainActivity;
import com.bharath.flashmessenger.R;
import com.bharath.flashmessenger.Setup.Model.SetupModel;
import com.bharath.flashmessenger.Setup.Repo.ProfileSetupRepo;
import com.bharath.flashmessenger.Setup.ViewModel.ProfileSetupView;
import com.bharath.flashmessenger.Setup.ViewModel.otpView;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ProfileSetup extends AppCompatActivity {
    TextView name, status;
    RadioButton male, female;
    ImageView profile;
    Button save;
    String Name = null;
    String Gender = null;
    String Phonenum=null;
    Uri selectedimage,Profile = null;
    String Status;
    RadioGroup radioGroup;
    FirebaseAuth auth;
    UploadTask uploadTask;
    FirebaseStorage storage;
    ProgressDialog progressDialog;


    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setup);

        ProfileSetupView profileSetupView = new ViewModelProvider(this).get(ProfileSetupView.class);
        name = findViewById(R.id.name);
        status = findViewById(R.id.status);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        profile = findViewById(R.id.profile_image);
        save = findViewById(R.id.save);
        radioGroup = findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            int id = radioGroup.getCheckedRadioButtonId();
            switch (id) {
                case R.id.male:
                    Gender = "Male";
                    break;
                case R.id.female:
                    Gender = "Female";
                    break;
            }
        });

        profile.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent, 4);
        });


        save.setOnClickListener(v -> {
            Name = name.getText().toString();
            Profile =selectedimage;
            Status = status.getText().toString();
            auth=FirebaseAuth.getInstance();
            Phonenum=auth.getCurrentUser().getPhoneNumber();
            if (TextUtils.isEmpty(Name)) {
                name.setError("Enter the Name");
                return;
            }
            if (Profile == null) {
                Profile = Uri.parse("NoImage");
            }
            if (Gender == null) {
                male.setError("please click any one");
                return;
            }
            if (TextUtils.isEmpty(Status)) {
                status.setError("Enter Status");
                return;
            }
            Toast.makeText(this, "ddd", Toast.LENGTH_SHORT).show();

            profileSetupView.send(Name, Gender,Phonenum,Profile, Status,this);

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 4 && resultCode == RESULT_OK && null != data) {
            final Uri uriImage = data.getData();
            profile.setImageURI(uriImage);
            selectedimage=uriImage;
        }
    }

}