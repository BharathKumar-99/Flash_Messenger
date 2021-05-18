package com.bharath.flashmessenger.Setup.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.bharath.flashmessenger.MainActivity;
import com.bharath.flashmessenger.R;
import com.bharath.flashmessenger.Setup.ViewModel.loginView;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    Button verify;
    String phonenum;
    TextView phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        verify=findViewById(R.id.verify);
        phone=findViewById(R.id.phone);
        phone.setText(null);
        FirebaseAuth auth;
        auth=FirebaseAuth.getInstance();
        if(auth.getCurrentUser()!=null){
            Intent intent=new Intent(this, MainActivity.class);
            startActivity(intent);
            finishAffinity();
        }
        loginView loginView=new loginView(this);
verify.setOnClickListener(v->{

    phonenum=phone.getText().toString();

    Log.d("TAG", "onCreate: "+phonenum);

    if(TextUtils.isEmpty(phonenum)) {
        phone.setError("Enter Number To Login");
        return;
    }

   else{
        loginView.send(phonenum,this);
    }
});
    }
}