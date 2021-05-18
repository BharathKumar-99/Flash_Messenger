package com.bharath.flashmessenger.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bharath.flashmessenger.R;
import com.bharath.flashmessenger.Setup.Model.Selectormodel;
import com.bharath.flashmessenger.ViewModel.StatusViewModel;
import com.bumptech.glide.Glide;

import java.util.List;


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

    viewModel.getname().observe(getViewLifecycleOwner(),model->{
        if (model != null) {
            for (Selectormodel models:model){
                Name.setText(models.getName());
                Glide.with(getContext()).load(models.getprofile()).into(profile);
            }
        }
    });

        view.setOnClickListener(v->{
viewModel.statusupload(getContext());
        });





        return root;

}}