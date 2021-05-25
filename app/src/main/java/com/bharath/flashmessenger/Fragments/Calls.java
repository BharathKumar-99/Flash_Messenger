package com.bharath.flashmessenger.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bharath.flashmessenger.Adapter.CallsRecycle;
import com.bharath.flashmessenger.Adapter.HomeRecycleAdapter;
import com.bharath.flashmessenger.Group.ui.Contacts;
import com.bharath.flashmessenger.R;
import com.bharath.flashmessenger.ViewModel.CallsViewModel;
import com.bharath.flashmessenger.ViewModel.HomeViewModel;
import com.bharath.flashmessenger.util.Settings;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import java.util.List;

public class Calls extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_calls, container, false);

        FloatingActionButton add =root. findViewById(R.id.floatingActionButton);
        TextView text =root. findViewById(R.id.stocktext);
        RecyclerView recyclerView=root.findViewById(R.id.callrecycle);
        add.setOnClickListener(v -> {
            Intent intent=new Intent(getContext(), Contacts.class);
            startActivity(intent);
        });

        CallsViewModel viewModel=new ViewModelProvider(this).get(CallsViewModel.class);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager. VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        text.setText("Click on the add button to start calling");
        viewModel.getcalls().observe(getViewLifecycleOwner(),contr->{
            CallsRecycle homeAdapter=new CallsRecycle(contr,getContext());
            recyclerView.setAdapter(homeAdapter);
            homeAdapter.notifyDataSetChanged();
            text.setVisibility(View.GONE);





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