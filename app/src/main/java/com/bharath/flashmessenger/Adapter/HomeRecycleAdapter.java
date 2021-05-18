package com.bharath.flashmessenger.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bharath.flashmessenger.Chatroom.ui.Chatroom;
import com.bharath.flashmessenger.R;
import com.bharath.flashmessenger.Setup.Model.Selectormodel;
import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HomeRecycleAdapter extends RecyclerView.Adapter<HomeRecycleAdapter.viewHolder> {
    List<Selectormodel> contacts;
    Context context;

    public HomeRecycleAdapter(List<Selectormodel> contr,Context context) {
        this.context=context;
        this.contacts=contr;
    }

    @NonNull
    @NotNull
    @Override
    public HomeRecycleAdapter.viewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.homerecycle, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull HomeRecycleAdapter.viewHolder holder, int position) {
        holder.name.setText(contacts.get(position).getName());
        Glide.with(holder.itemView.getContext()).load(contacts.get(position).getprofile()).into(holder.profilepic);
        holder.chat.setOnClickListener(v->{
            Intent intent=new Intent(context, Chatroom.class);
            intent.putExtra("name",contacts.get(position).getName());
            intent.putExtra("uid",contacts.get(position).getUid());
            intent.putExtra("pic",contacts.get(position).getprofile());
            intent.putExtra("phone",contacts.get(position).getPhonenum());
            intent.putExtra("status",contacts.get(position).getStatus());
            context.startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        if(contacts != null) {
            return contacts.size();
        }
        return 0;
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView profilepic;
        TextView name,msg;
        View chat;
        public viewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            profilepic=itemView.findViewById(R.id.profile_image);
            name=itemView.findViewById(R.id.homename);
            msg=itemView.findViewById(R.id.lstmsg);
            chat=itemView.findViewById(R.id.chatselector);
        }
    }
}
