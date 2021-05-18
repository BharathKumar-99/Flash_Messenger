package com.bharath.flashmessenger.Setup.ViewModel;

import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.bharath.flashmessenger.Setup.Repo.ProfileSetupRepo;

public class ProfileSetupView extends ViewModel {
    public String Name,Gender,Status,Phonenum;
    Uri Profile;

    public void send(String name, String gender,String phonenum, Uri profile, String status,Context context){

      Name=name;
      Gender=gender;
      Phonenum=phonenum;
      Profile= profile;
      Status=status;

      ProfileSetupRepo repo=new ProfileSetupRepo();
      repo.Save(Name, Status,Gender,Phonenum, Profile,context);

    }


}
