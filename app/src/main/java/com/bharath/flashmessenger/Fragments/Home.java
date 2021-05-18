package com.bharath.flashmessenger.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.bharath.flashmessenger.Adapter.HomeRecycleAdapter;
import com.bharath.flashmessenger.Group.Adaptor.RecycleviewAdapter;
import com.bharath.flashmessenger.Group.ui.Contacts;
import com.bharath.flashmessenger.R;
import com.bharath.flashmessenger.ViewModel.HomeViewModel;
import com.bharath.flashmessenger.util.Settings;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Home extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);
        FloatingActionButton add =root. findViewById(R.id.add);
        RecyclerView recyclerView=root.findViewById(R.id.recycleviews);
        add.setOnClickListener(v -> {
            Intent intent=new Intent(getContext(), Contacts.class);
            startActivity(intent);
        });
        HomeViewModel viewModel=new ViewModelProvider(this).get(HomeViewModel.class);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager. VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        viewModel.getNames().observe(getViewLifecycleOwner(),contr->{
            HomeRecycleAdapter homeAdapter=new HomeRecycleAdapter(contr,getContext());
            recyclerView.setAdapter(homeAdapter);
            homeAdapter.notifyDataSetChanged();
        });
        return root;
    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.mainmenu, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.search:

                return true;
            case R.id.settings:
                Intent intent=new Intent(getContext(), Settings.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}