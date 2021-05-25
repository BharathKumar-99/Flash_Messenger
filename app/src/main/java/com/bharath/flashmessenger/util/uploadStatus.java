package com.bharath.flashmessenger.util;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bharath.flashmessenger.MainActivity;
import com.bharath.flashmessenger.Model.Statusim;
import com.bharath.flashmessenger.Model.Statusm;
import com.bharath.flashmessenger.R;
import com.bharath.flashmessenger.Setup.Model.Selectormodel;
import com.bharath.flashmessenger.Setup.Model.SetupModel;
import com.bharath.flashmessenger.Setup.Repo.dbhelper;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class uploadStatus extends AppCompatActivity {

    String Caption,Name,pic;
    ImageView imageView;
    TextView caption;
    ImageButton upload,returner;
    Uri path;
    FirebaseAuth auth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String uid;
    ArrayList<Statusm> Strory=new ArrayList<>();
    FirebaseDatabase database;
    List<SetupModel> users;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_status);
        imageView=findViewById(R.id.previewimg);
        upload=findViewById(R.id.upload);
        caption=findViewById(R.id.caption);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar5);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
        getSupportActionBar().setTitle("Upload Preview");
       String file=getIntent().getStringExtra("file");
        auth=FirebaseAuth.getInstance();
        uid=auth.getUid();
        caption.setText(null);
        database=FirebaseDatabase.getInstance();
        preview(file);
        dbhelper db=new dbhelper(this);
        users=db.showdata();
        for (SetupModel models:users){
            Name=models.getName();
            pic=models.getProfile();
        }


        upload.setOnClickListener(v->{
            Caption=caption.getText().toString();
            FirebaseStorage storage=FirebaseStorage.getInstance();
            Date date=new Date();
            StorageReference storageReference=storage.getReference().child("Status").child(date.getTime()+"");
            storageReference.putFile(path).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    storageReference.getDownloadUrl().addOnSuccessListener(uri -> {
                        Statusm statusm=new Statusm();
                        statusm.setName(Name);
                        statusm.setProfileimg(pic);
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                        LocalDateTime now = LocalDateTime.now();
                        System.out.println(dtf.format(now));
                        statusm.setLastupdate(dtf.format(now));

                        HashMap<String,Object> obj=new HashMap<>();
                        obj.put("name",statusm.getName());
                        obj.put("profileimg",statusm.getProfileimg());
                        obj.put("lastupdate",statusm.getLastupdate());
                        obj.put("timestamp:",statusm.getTimestamp());
                        Log.d("TAG", "onCreate: "+statusm.getTimestamp());
                        String imageurl=uri.toString();
                        Statusim statusim=new Statusim(imageurl,statusm.getLastupdate(),Caption);

                        database.getReference().child("stories").child(auth.getUid()).updateChildren(obj);
                        database.getReference().child("stories").child(auth.getUid()).child("Statusms").push().setValue(statusim);

                    });
                }
            });
        });
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finishAffinity();
    }


    public void preview( String file){

        path= Uri.fromFile(new File(file));
        Glide.with(this).load(path).into(imageView);
    }
}