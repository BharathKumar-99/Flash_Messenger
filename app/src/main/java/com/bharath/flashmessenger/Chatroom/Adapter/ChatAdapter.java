package com.bharath.flashmessenger.Chatroom.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bharath.flashmessenger.Chatroom.Model.ChatModel;
import com.bharath.flashmessenger.Group.ViewModel.ContactViewModel;
import com.bharath.flashmessenger.R;
import com.bharath.flashmessenger.Setup.Model.Selectormodel;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter {
    List<ChatModel> contacts;
    Context context;

    public ChatAdapter(List<ChatModel> list,Context context) {
        this.contacts=list;
        this.context=context;
    }

    @Override
    public int getItemViewType(int position) {
        ChatModel message=contacts.get(position);
        if(FirebaseAuth.getInstance().getUid().equals(message.getSenderid())){
            return 1;
        }
        else
            return 0;
    }
    public void setdata(List<ChatModel> contacts){
        this.contacts=contacts;
        notifyDataSetChanged();
    }
    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        if(viewType == 1){
            View view= LayoutInflater.from(context).inflate(R.layout.sender,parent,false);
            return new sender(view);
        }
            View view= LayoutInflater.from(context).inflate(R.layout.reciver,parent,false);
        return new reciver(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        ChatModel message=contacts.get(position);
        if(FirebaseAuth.getInstance().getUid().equals(message.getSenderid())){
            sender viewholder=(sender) holder;
            viewholder.sendertext.setText(message.getMsg());
        }else
        {
            reciver viewholder=(reciver) holder;
            viewholder.recivertext.setText(message.getMsg());
            Glide.with(context).load(message.getProfilepic()).into(((reciver) holder).profile_image);
        }
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    class sender extends RecyclerView.ViewHolder{
        TextView sendertext;
        public sender(@NonNull @NotNull View itemView) {
            super(itemView);
            sendertext=itemView.findViewById(R.id.sendertext);
        }
    }
    class reciver extends RecyclerView.ViewHolder{
        TextView recivertext;
        ImageView profile_image;
        public reciver(@NonNull @NotNull View itemView) {
            super(itemView);
            recivertext=itemView.findViewById(R.id.recivertext);
            profile_image=itemView.findViewById(R.id.profile_image);
        }
    }
}
