package com.bharath.flashmessenger.Repo;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.bharath.flashmessenger.Model.Statusm;
import com.bharath.flashmessenger.Setup.Model.Selectormodel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StatusRepo {

    FirebaseAuth auth=FirebaseAuth.getInstance();
    String uid = auth.getUid();
    MutableLiveData<List<Statusm>> Stroies;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    MutableLiveData<List<Selectormodel>> contr;
    FirebaseDatabase database;

    ArrayList<Selectormodel> con = new ArrayList<>();

    public MutableLiveData<List<Selectormodel>> getNames() {
        if (contr == null) {
            contr = new MutableLiveData<>();
        }
        db.collection("Users").whereEqualTo("uid",uid)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        con.clear();
                        for (QueryDocumentSnapshot doc : value) {
                            if (doc != null) {
                                con.add(doc.toObject(Selectormodel.class));
                            }
                        }
                        contr.postValue(con);
                    }

                });
        return contr;
}



    public MutableLiveData<List<Statusm>> getStroies(){

        if (Stroies == null) {
            Stroies = new MutableLiveData<>();
        }

        database=FirebaseDatabase.getInstance();
        database.getReference().child("stories").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot dp : snapshot.getChildren()){
                        Statusm statusm=new Statusm();
                        statusm.setName(dp.child("name").getValue(String.class));
                        statusm.setProfileimg(dp.child("profileimg").getValue(String.class));
                        statusm.setLastupdate(dp.child("lastupdate").getValue(String.class));

                        ArrayList<Statusm> statusms=new ArrayList<>();
                        for(DataSnapshot snap : dp.child("Statusms").getChildren()){
                            Statusm statusim=snap.getValue(Statusm.class);
                            Log.d("TAG", "onCreate: "+statusim.getTimestamp());
                            statusms.add(statusim);
                        }
                        statusm.setStatusms(statusms);
                        Stroies.postValue(Collections.singletonList(statusm));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return Stroies;
    }
}
