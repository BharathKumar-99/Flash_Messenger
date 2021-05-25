package com.bharath.flashmessenger.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bharath.flashmessenger.Adapter.StatusRecycle;
import com.bharath.flashmessenger.R;
import com.bharath.flashmessenger.Setup.Model.Selectormodel;
import com.bharath.flashmessenger.ViewModel.StatusViewModel;
import com.bharath.flashmessenger.util.UploadStatusPreview;
import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Objects;


public class Status extends Fragment {
    TextView Name;
   View view;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       MutableLiveData<List<Selectormodel>> name;
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_status, container, false);
       Name=root.findViewById(R.id.namer);
       view=root.findViewById(R.id.view5);
        ImageView profile=root.findViewById(R.id.statusimg);
        RecyclerView recyclerView=root.findViewById(R.id.statusrv);
        StatusViewModel viewModel=new ViewModelProvider(this).get(StatusViewModel.class);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager. VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

    viewModel.getname().observe(getViewLifecycleOwner(),model->{
        if (model != null) {
            for (Selectormodel models:model){
                Name.setText(models.getName());
                Glide.with(Objects.requireNonNull(getContext())).load(models.getprofile()).into(profile);
            }
        }
    });

        view.setOnClickListener(v->{
            Intent intent=new Intent(getContext(), UploadStatusPreview.class);
            startActivity(intent);
        });

    viewModel.getStatus().observe(getViewLifecycleOwner(),statusm -> {
        StatusRecycle statusRecycle=new StatusRecycle(statusm,getContext());
        recyclerView.setAdapter(statusRecycle);
        statusRecycle.notifyDataSetChanged();
});



        return root;

}}