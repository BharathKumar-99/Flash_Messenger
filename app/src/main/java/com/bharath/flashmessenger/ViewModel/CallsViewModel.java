package com.bharath.flashmessenger.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bharath.flashmessenger.Repo.CallsRepo;
import com.bharath.flashmessenger.Repo.HomeRepo;
import com.bharath.flashmessenger.Setup.Model.Selectormodel;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class CallsViewModel extends ViewModel {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    MutableLiveData<List<Selectormodel>> contr;
    MutableLiveData<Selectormodel> contacts;
    ArrayList<Selectormodel> con = new ArrayList<>();
   CallsRepo repo=new CallsRepo();


    public MutableLiveData<List<Selectormodel>> getcalls() {

        contr=repo.getcalls();
        return contr;
    }
}