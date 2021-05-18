package com.bharath.flashmessenger.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bharath.flashmessenger.Repo.HomeRepo;
import com.bharath.flashmessenger.Setup.Model.Selectormodel;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    MutableLiveData<List<Selectormodel>> contr;
    MutableLiveData<Selectormodel> contacts;
    ArrayList<Selectormodel> con = new ArrayList<>();
HomeRepo homeRepo=new HomeRepo();
    public MutableLiveData<List<Selectormodel>> getNames() {

        contr=homeRepo.getNames();
        return contr;
    }
}
