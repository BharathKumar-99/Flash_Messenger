package com.bharath.flashmessenger.Group.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.bharath.flashmessenger.Group.Adaptor.RecycleviewAdapter;
import com.bharath.flashmessenger.Group.ViewModel.ContactViewModel;
import com.bharath.flashmessenger.MainActivity;
import com.bharath.flashmessenger.R;

public class Contacts extends AppCompatActivity {
RecyclerView recyclerView;
ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar7);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
        getSupportActionBar().setTitle("Select Contact");

       recyclerView=findViewById(R.id.rv);
        ContactViewModel viewModel=new ViewModelProvider(this).get(ContactViewModel.class);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager. VERTICAL);
         recyclerView.setLayoutManager(linearLayoutManager);

        viewModel.getNames().observe(this,contr ->{
            RecycleviewAdapter homeAdapter=new RecycleviewAdapter(contr);
            recyclerView.setAdapter(homeAdapter);
            homeAdapter.notifyDataSetChanged();

        });


    }
}