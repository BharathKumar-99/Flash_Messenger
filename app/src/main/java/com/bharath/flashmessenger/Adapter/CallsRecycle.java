package com.bharath.flashmessenger.Adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bharath.flashmessenger.Chatroom.ui.Chatroom;
import com.bharath.flashmessenger.R;
import com.bharath.flashmessenger.Setup.Model.Selectormodel;
import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CallsRecycle extends RecyclerView.Adapter<CallsRecycle.viewHolder> {
    List<Selectormodel> contacts;
    Context context;

    public CallsRecycle(List<Selectormodel> contr,Context context) {
        this.context=context;
        this.contacts=contr;
    }

    @NonNull
    @NotNull
    @Override
    public CallsRecycle.viewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.callrow, parent, false);
        return new CallsRecycle.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull viewHolder holder, int position) {
        holder.name.setText(contacts.get(position).getName());
        Glide.with(holder.itemView.getContext()).load(contacts.get(position).getprofile()).into(holder.profilepic);
        holder.call.setOnClickListener(v->{
            Intent dialIntent = new Intent(Intent.ACTION_DIAL);
            dialIntent.setData(Uri.parse("tel:" + contacts.get(position).getPhonenum()));
            context.startActivity(dialIntent);
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
        TextView name;
        ImageButton call;
 viewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            profilepic=itemView.findViewById(R.id.profile_image);
            name=itemView.findViewById(R.id.homename);
            call=itemView.findViewById(R.id.dailer);

        }
    }



}
