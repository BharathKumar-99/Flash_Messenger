package com.bharath.flashmessenger.Group.Repo;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.bharath.flashmessenger.Group.Model.Friendlistmodel;
import com.bharath.flashmessenger.MainActivity;
import com.bharath.flashmessenger.Setup.Model.Selectormodel;
import com.bharath.flashmessenger.Setup.Model.SetupModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ContactRepo {
    FirebaseAuth auth=FirebaseAuth.getInstance();
    String uid = auth.getUid();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    MutableLiveData<List<Selectormodel>> contr;
    MutableLiveData<Selectormodel> contacts;
    ArrayList<Selectormodel> con = new ArrayList<>();

    public MutableLiveData<List<Selectormodel>> getNames() {
        if (contr == null) {
            contr = new MutableLiveData<>();
        }
        db.collection("Users").whereNotEqualTo("uid",uid)
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

    public void friendlist(String Uid, Context context){
        Friendlistmodel user = new Friendlistmodel(Uid);
        db.collection("Users").document(uid).collection("FriendsList").document(Uid)
                .set(user)
                .addOnSuccessListener(aVoid -> {


                })
                .addOnFailureListener(e -> {
                    Toast.makeText(context, "Upload failed", Toast.LENGTH_SHORT).show();
                });
    }
}
