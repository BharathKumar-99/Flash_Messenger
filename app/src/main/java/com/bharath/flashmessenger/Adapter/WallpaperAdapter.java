package com.bharath.flashmessenger.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bharath.flashmessenger.R;
import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class WallpaperAdapter extends RecyclerView.Adapter<WallpaperAdapter.viewholder> {
    Context context;
    List<Integer> wallpaper;
    public WallpaperAdapter(Context context, List<Integer> wallper) {
        this.context=context;
        this.wallpaper=wallper;
    }

    @NonNull
    @NotNull
    @Override
    public viewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wallpapergrid, parent, false);
        return new WallpaperAdapter.viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull viewholder holder, int position) {
        Glide.with(context).load(wallpaper.get(position)).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return wallpaper.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public viewholder(@NonNull @NotNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.wallpaperpic);
        }
    }
}
