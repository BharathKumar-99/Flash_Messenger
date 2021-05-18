package com.bharath.flashmessenger.Setup.ViewModel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.bharath.flashmessenger.Setup.ui.Login;
import com.bharath.flashmessenger.Setup.ui.Otp;

public class loginView extends ViewModelProvider {
    public loginView(@NonNull ViewModelStoreOwner owner) {
        super(owner);
    }
    public void send(String phonenum, Context context){
        phonenum="+91"+phonenum;
        if(phonenum == null){
            return;
        }
        Intent intent=new Intent(context, Otp.class);
        Log.d("TAG", "send: "+ phonenum);
        intent.putExtra("key",phonenum);
        context.startActivity(intent);
    }
}
