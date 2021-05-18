package com.bharath.flashmessenger.Chatroom.ViewModel;

import android.content.Context;
import android.net.Uri;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bharath.flashmessenger.Chatroom.Model.ChatModel;
import com.bharath.flashmessenger.Chatroom.Repo.Chatrepo;
import com.bharath.flashmessenger.Chatroom.ui.Chatroom;
import com.bharath.flashmessenger.Repo.HomeRepo;
import com.bharath.flashmessenger.Setup.Model.Selectormodel;
import com.bharath.flashmessenger.Setup.Model.SetupModel;
import com.bharath.flashmessenger.Setup.Repo.dbhelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class ChatView extends ViewModel {
    String Profileimg,Name,Phone,Uid,Pic;
    MutableLiveData<List<ChatModel>> contr;
    Boolean sent;
    String currentuid= FirebaseAuth.getInstance().getUid();
    String senderid;
Chatrepo repo=new Chatrepo();


    public void inti(String profileimg,String name,String phone,String uid){
        this.Name=name;
        this.Phone=phone;
        this.Profileimg=profileimg;
        this.Uid=uid;
        senderid=currentuid+uid;
    }


    public MutableLiveData<List<ChatModel>> getchat(){
      contr=repo.getchat(senderid);
        return contr;

    }

    public void send(String msg, String senderuid, String uid, String reciverid, long time, Context context){

        SetupModel setupModel=new SetupModel();
       dbhelper db=new dbhelper(context);
        List<SetupModel> name=db.showdata();
        for (SetupModel model:name){
            Pic=model.getProfile();
        }

repo.send(msg,senderuid,uid,reciverid,time,Pic);


    }

}
