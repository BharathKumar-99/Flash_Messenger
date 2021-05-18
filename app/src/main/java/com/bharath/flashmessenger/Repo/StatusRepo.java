package com.bharath.flashmessenger.Repo;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.bharath.flashmessenger.Setup.Model.Selectormodel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class StatusRepo {

    FirebaseAuth auth=FirebaseAuth.getInstance();
    String uid = auth.getUid();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    MutableLiveData<List<Selectormodel>> contr;
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

}