package com.bharath.flashmessenger.Chatroom.Repo;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.bharath.flashmessenger.Chatroom.Model.ChatModel;
import com.bharath.flashmessenger.Group.Model.Friendlistmodel;
import com.bharath.flashmessenger.MainActivity;
import com.bharath.flashmessenger.Setup.Model.Selectormodel;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Chatrepo {
    Boolean sent;
    FirebaseAuth auth=FirebaseAuth.getInstance();
    String uid = auth.getUid();

    MutableLiveData<List<ChatModel>> contr;
    MutableLiveData<List<ChatModel>> cont;
    MutableLiveData<Selectormodel> contacts;
    ArrayList<ChatModel> con = new ArrayList<>();
     DatabaseReference mDatabase;



    public MutableLiveData<List<ChatModel>> getchat(String senderuid) {
        if (cont == null) {
            cont = new MutableLiveData<>();
        }
        if (contr == null) {
            contr = new MutableLiveData<>();
        }
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("chats").child(senderuid).child("messages").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                con.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ChatModel message = dataSnapshot.getValue(ChatModel.class);
                    con.add(message);
                }
                contr.postValue(con);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
        return contr;
    }
            public void send(String msg,String senderuid,String uid,String reciverid,long time,String pic ){
        String randomkey=mDatabase.push().getKey();
        ChatModel message=new ChatModel(msg,uid,time,pic);
                assert randomkey != null;
                Log.d("TAG", "send: "+ pic);
                mDatabase.child("chats")
                        .child(senderuid).child("messages").child(randomkey).setValue(message).addOnSuccessListener(aVoid ->{
                            mDatabase.child("chats")
                                    .child(reciverid).child("messages").child(randomkey).setValue(message)
                                    .addOnSuccessListener(aVoid1 -> {
                                                sent=true;
                                                Log.d("TAG", "send: ");
                                            }
                                    ).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull @NotNull Exception e) {
                                    Log.d("TAG", "onFailure: "+ e);
                                }
                            });
                        }
                       ).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {
                        Log.d("TAG", "onFailure: "+ e);
                    }
                });






    }


    }
