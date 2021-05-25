package com.bharath.flashmessenger.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bharath.flashmessenger.MainActivity;
import com.bharath.flashmessenger.Model.Statusim;
import com.bharath.flashmessenger.Model.Statusm;
import com.bharath.flashmessenger.R;
import com.bumptech.glide.Glide;
import com.devlomi.circularstatusview.CircularStatusView;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import omari.hamza.storyview.StoryView;
import omari.hamza.storyview.callback.StoryClickListeners;
import omari.hamza.storyview.model.MyStory;

public class StatusRecycle extends RecyclerView.Adapter<StatusRecycle.viewHolder> {
Context context;
List<Statusm> Statusm;
    public StatusRecycle(List<Statusm> statusm, Context context) {
        this.Statusm=statusm;
        this.context=context;
    }

    @NonNull
    @NotNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.statusrow, parent, false);
        return new StatusRecycle.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull viewHolder holder, int position) {



        Statusm userstatus=Statusm.get(position);

        holder.wheel.setPortionsCount(userstatus.getStatusms().size());
        Statusim laststatus=userstatus.getStatusms().get(userstatus.getStatusms().size() - 1);

        Glide.with(context).load(laststatus.getImageurl()).into(holder.propic);
        holder.wheel.setOnClickListener(v ->{


            ArrayList<MyStory> myStories = new ArrayList<>();
            Statusim statusim=new Statusim();
            String time= userstatus.getTimestamp();
            Log.d("TAG", "onBindViewHolder: "+time);
            for(Statusim status:userstatus.getStatusms()){
                @SuppressLint("SimpleDateFormat")
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
                    myStories.add(new MyStory(
                            status.getImageurl()

                    ));

            }


            new StoryView.Builder(((MainActivity)context).getSupportFragmentManager())
                    .setStoriesList(myStories) // Required
                    .setStoryDuration(5000) // Default is 2000 Millis (2 Seconds)
                    .setTitleText(userstatus.getName()) // Default is Hidden
                    .setSubtitleText(null)// Default is Hidden
                    .setTitleLogoUrl(userstatus.getProfileimg())
                    .setStoryClickListeners(new StoryClickListeners() {
                        @Override
                        public void onDescriptionClickListener(int position) {
                            //your action
                        }

                        @Override
                        public void onTitleIconClickListener(int position) {
                            //your action
                        }
                    }) // Optional Listeners
                    .build() // Must be called before calling show method
                    .show();
        });
        holder.name.setText(userstatus.getName());



    }

    @Override
    public int getItemCount() {
        return Statusm.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView name,time;
        ImageView propic;
        CircularStatusView wheel;
        public viewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.namer2);
            time=itemView.findViewById(R.id.timeed);
            propic=itemView.findViewById(R.id.statusimg2);
            wheel=itemView.findViewById(R.id.wheel);
        }
    }
}
