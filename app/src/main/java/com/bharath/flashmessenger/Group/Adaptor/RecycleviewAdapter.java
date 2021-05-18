package com.bharath.flashmessenger.Group.Adaptor;

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

import com.bharath.flashmessenger.Group.ViewModel.ContactViewModel;
import com.bharath.flashmessenger.R;
import com.bharath.flashmessenger.Setup.Model.Selectormodel;
import com.bumptech.glide.Glide;

import java.util.List;

public class RecycleviewAdapter extends RecyclerView.Adapter<RecycleviewAdapter.viewHolder> {
    List<Selectormodel> contacts;
    Context context;

    public RecycleviewAdapter(List<Selectormodel> list) {
        this.contacts=list;
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contatcsselector, parent, false);
        Log.d("TAG", "onCreateViewHolder: ");
        return new viewHolder(view);

    }
    public void setdata(List<Selectormodel> contacts){
        this.contacts=contacts;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.name.setText(contacts.get(position).getName());
        Glide.with(holder.itemView.getContext()).load(contacts.get(position).getprofile()).into(holder.pic);
        holder.addtocontact.setOnClickListener(v->{
            ContactViewModel contactViewModel=new ContactViewModel();
            contactViewModel.uploadtofriendlist(contacts.get(position).getUid(),context);
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
        ImageView pic;
        ImageButton addtocontact;
        TextView name;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            pic=itemView.findViewById(R.id.profile_image);
            name=itemView.findViewById(R.id.contactname);
            addtocontact=itemView.findViewById(R.id.addtocontact);
        }
}}
