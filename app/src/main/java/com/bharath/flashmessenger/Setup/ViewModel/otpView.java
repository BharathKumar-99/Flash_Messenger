package com.bharath.flashmessenger.Setup.ViewModel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.bharath.flashmessenger.Setup.ui.Login;
import com.bharath.flashmessenger.Setup.ui.Otp;
import com.bharath.flashmessenger.Setup.ui.ProfileSetup;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class otpView extends ViewModel {

FirebaseAuth mAuth=FirebaseAuth.getInstance();
    Context context;
    String mVerificationId;

    PhoneAuthProvider.ForceResendingToken mResendToken;

    public void otp(String phone, Activity context){
        this.context=context;

        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber(phone)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(context)
                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        signInWithPhoneAuthCredential(phoneAuthCredential);
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Toast.makeText(context, "Verification Failed", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCodeSent(String verificationId,
                                           PhoneAuthProvider.ForceResendingToken forceResendingToken) {


                        Toast.makeText(context, "Otp Sent", Toast.LENGTH_SHORT).show();
                        // The corresponding whitelisted code above should be used to complete sign-in.
                        mVerificationId = verificationId;
                        mResendToken = forceResendingToken;
                    }

                })
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithCredential:success");
                            Intent intent=new Intent(context, ProfileSetup.class);
                            context.startActivity(intent);
                            Otp otp=new Otp();
                            otp.finish();
                            FirebaseUser user = task.getResult().getUser();
                            // Update UI
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w("TAG", "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }

                    }
                });
    }
public void loginusingotp(String otp,Context context){

    try {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, otp);
        signInWithPhoneAuthCredential(credential);
    }catch (Exception e){
        Toast toast = Toast.makeText(context, "Verification Code is wrong", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

}



    public void resendVerificationCode(String phoneNumber,Activity context) {
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber(phoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(context)
                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        signInWithPhoneAuthCredential(phoneAuthCredential);
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Toast.makeText(context, "Verification Failed", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCodeSent(String verificationId,
                                           PhoneAuthProvider.ForceResendingToken forceResendingToken) {


                        Toast.makeText(context, "Otp Sent", Toast.LENGTH_SHORT).show();
                        // The corresponding whitelisted code above should be used to complete sign-in.
                        mVerificationId = verificationId;
                        mResendToken = forceResendingToken;
                    }

                })
                .setForceResendingToken(mResendToken)
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }
}
