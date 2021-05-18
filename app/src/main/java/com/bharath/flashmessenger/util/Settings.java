package com.bharath.flashmessenger.util;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.bharath.flashmessenger.R;
import com.bharath.flashmessenger.Setup.ui.Login;
import com.google.firebase.auth.FirebaseAuth;

public class Settings extends AppCompatActivity {
TextView wallaper,editprofile,logout;
FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        wallaper=findViewById(R.id.wallpaper);
        editprofile=findViewById(R.id.editprofile);
        logout=findViewById(R.id.logout);
        auth=FirebaseAuth.getInstance();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);

editprofile.setOnClickListener(v->{
    Intent intent1=new Intent(this, ProfileEdit.class);
    startActivity(intent1);

});




        logout.setOnClickListener(v->{

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Do you really want to SignOut");
            builder.setTitle("Sign out");
            builder.setCancelable(false);
            builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
            builder.setPositiveButton("Sign out", (dialog, which) ->
            {
                auth.signOut();
                Intent intent=new Intent(this, Login.class);
                startActivity(intent);
                finishAffinity();
            });
            AlertDialog alert = builder.create();
            alert.show();


        });
    }
}