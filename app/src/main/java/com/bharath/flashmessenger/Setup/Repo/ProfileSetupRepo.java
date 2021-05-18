package com.bharath.flashmessenger.Setup.Repo;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bharath.flashmessenger.MainActivity;
import com.bharath.flashmessenger.R;
import com.bharath.flashmessenger.Setup.Model.SetupModel;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;

public class ProfileSetupRepo {
    FirebaseAuth auth=FirebaseAuth.getInstance();
    FirebaseStorage storage=FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();
    ProgressDialog progressDialog;
String image;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String uid = auth.getUid();
    Uri images;
    String Profile;
    public void Save(String name, String status, String gender, String phonenum, Uri profile, Context context) {

        auth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Uploading ....");
        progressDialog.setCancelable(false);
        progressDialog.show();
image= String.valueOf(profile);

        if (!image.equals("NoImage"))
        {

            StorageReference riversRef = storageRef.child("Profile").child(uid);
          riversRef.putFile(profile).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle unsuccessful uploads
                    progressDialog.dismiss();
                    Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                    // ...
                    progressDialog.dismiss();
                    riversRef.getDownloadUrl().addOnSuccessListener(uri -> {
                        Profile= uri.toString();
                        upload(name, status, gender, phonenum, Profile, context);
                    });

                }
            });

        } else {
if(gender.equals("Male")){


    Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.boy);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
    byte[] data = baos.toByteArray();
    StorageReference riversRef = storageRef.child("Profile").child(uid);
    UploadTask uploadTask = riversRef.putBytes(data);
    uploadTask.addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception exception) {
            // Handle unsuccessful uploads
        }
    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
        @Override
        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
            // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
            // ...
            progressDialog.dismiss();
            riversRef.getDownloadUrl().addOnSuccessListener(uri -> {
                Profile= uri.toString();
                upload(name, status, gender, phonenum, Profile, context);
            });

        }
    });


}else{
    Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.girl);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
    byte[] data = baos.toByteArray();
    StorageReference riversRef = storageRef.child("Profile").child(uid);
    UploadTask uploadTask = riversRef.putBytes(data);
    uploadTask.addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception exception) {
            // Handle unsuccessful uploads
        }
    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
        @Override
        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
            // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
            // ...
            progressDialog.dismiss();
            riversRef.getDownloadUrl().addOnSuccessListener(uri -> {
                Profile= uri.toString();
                upload(name, status, gender, phonenum, Profile, context);
            });

        }
    });

}

        }
    }



    public void upload (String name, String status, String gender, String phonenum, String profile, Context context){
        SetupModel user = new SetupModel(name, status, phonenum, gender, Profile, uid);
        db.collection("Users").document(uid)
                .set(user)
                .addOnSuccessListener(aVoid -> {
                    dbhelper db=new dbhelper(context);
                    db.addtodb(name, status, gender, phonenum, profile);
                    progressDialog.dismiss();
                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);
                })
                .addOnFailureListener(e -> {
                    progressDialog.dismiss();
                    Toast.makeText(context, "Upload failed", Toast.LENGTH_SHORT).show();
                });
    }
}


