package com.bharath.flashmessenger.Group.ViewModel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bharath.flashmessenger.Group.Repo.ContactRepo;
import com.bharath.flashmessenger.Setup.Model.Selectormodel;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ContactViewModel extends ViewModel {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    MutableLiveData<List<Selectormodel>> contr;
    MutableLiveData<Selectormodel> contacts;
    ArrayList<Selectormodel> con = new ArrayList<>();
    ContactRepo contactRepo=new ContactRepo();


    public MutableLiveData<List<Selectormodel>> getNames() {

        contr= contactRepo.getNames();
        return contr;
    }

    public void uploadtofriendlist(String Uid, Context context){
contactRepo.friendlist(Uid,context);
    }


}
