package com.bharath.flashmessenger.Setup.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;

import com.bharath.flashmessenger.R;
import com.bharath.flashmessenger.Setup.ViewModel.otpView;
import com.mukesh.OnOtpCompletionListener;
import com.mukesh.OtpView;

public class Otp extends AppCompatActivity {
TextView wrongnumber,resend,title;
OtpView otpView1;

String phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
      otpView  view = new ViewModelProvider(this).get(otpView.class);
        Intent intent=new Intent();
        phone=getIntent().getStringExtra("key");
        Log.d("TAG", "onCreate: "+phone);

        title=findViewById(R.id.title);
        wrongnumber=findViewById(R.id.wrong);
        resend=findViewById(R.id.resend);
        title.setText("Your Entered Phone Number Is "+phone);
        view.otp(phone,Otp.this);
        otpView1=findViewById(R.id.otp_view);
        new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                resend.setText("Wait Until " + millisUntilFinished / 1000+" To Send Code Again");
            }

            public void onFinish() {
                resend.setText("RESEND CODE");
            }
        }.start();
wrongnumber.setOnClickListener(v->{
    Intent intent1=new Intent(this,Login.class);
    startActivity(intent1);
    finish();
});



resend.setOnClickListener(v->
{
    String resend1=resend.getText().toString();
    if(resend1.equals("RESEND CODE")) {
        view.resendVerificationCode(phone, Otp.this);
    }else
        return;
});

otpView1.setOtpCompletionListener(new OnOtpCompletionListener() {
    @Override
    public void onOtpCompleted(String otp) {
        view.loginusingotp(otp,getApplicationContext());
    }
});

    }
    public void finish(){
        finishAffinity();
    }

}