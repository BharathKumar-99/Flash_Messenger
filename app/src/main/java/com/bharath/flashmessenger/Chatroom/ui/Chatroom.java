package com.bharath.flashmessenger.Chatroom.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bharath.flashmessenger.Adapter.WallpaperAdapter;
import com.bharath.flashmessenger.Chatroom.Adapter.ChatAdapter;
import com.bharath.flashmessenger.Chatroom.ViewModel.ChatView;
import com.bharath.flashmessenger.R;
import com.bharath.flashmessenger.util.Settings;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Chatroom extends AppCompatActivity {
    TextView msg;
    String uid;
    FirebaseAuth auth;
    RecyclerView recyclerView;
    ImageButton send;
    ImageView background;
    String Profileimg, Name, Phone, Uid;
    Uri Wallpapers;
    String Status;
    PopupWindow mpopup;
    List<Integer> wallper=new ArrayList<>();
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);
        auth = FirebaseAuth.getInstance();
        msg = findViewById(R.id.msg);
        uid = auth.getUid();
        background = findViewById(R.id.background);
        send = findViewById(R.id.send);
        recyclerView = findViewById(R.id.rvs);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        Toolbar toolbar = findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24);
        LoadPreferences();


        Profileimg = getIntent().getStringExtra("pic");
        Name = getIntent().getStringExtra("name");
        Uid = getIntent().getStringExtra("uid");
        Phone = getIntent().getStringExtra("phone");
        Status = getIntent().getStringExtra("status");
        getSupportActionBar().setTitle(Name);
        ChatView view = new ViewModelProvider(this).get(ChatView.class);
        view.inti(Profileimg, Name, Phone, Uid);


        view.getchat().observe(this, contr -> {
            ChatAdapter homeAdapter = new ChatAdapter(contr, this);
            recyclerView.setAdapter(homeAdapter);
            homeAdapter.notifyDataSetChanged();
        });

        toolbar.setOnClickListener(v -> {
            popup();
        });


        send.setOnClickListener(v -> {
            String Msg;
            Msg = msg.getText().toString();
            msg.setText(null);
            String currentuid = uid;
            String senderroom = currentuid + Uid;

            String reciverroom = Uid + currentuid;
            Date date = new Date();
            view.send(Msg, senderroom, currentuid, reciverroom, date.getTime(), this);
        });
    }


    public void popup() {
        View popUpView = getLayoutInflater().inflate(R.layout.activity_profilechat,
                null); // inflating popup layout
        mpopup = new PopupWindow(popUpView, Toolbar.LayoutParams.FILL_PARENT,
                Toolbar.LayoutParams.WRAP_CONTENT, true); // Creation of popup
        mpopup.setAnimationStyle(android.R.style.Animation_Dialog);
        mpopup.showAtLocation(popUpView, Gravity.FILL, 0, 0);


        TextView name = (TextView) popUpView.findViewById(R.id.profilename);
        TextView phone = (TextView) popUpView.findViewById(R.id.profilenum);
        TextView status = (TextView) popUpView.findViewById(R.id.profilestatus);
        ImageView pic = (ImageView) popUpView.findViewById(R.id.pic);
        ImageButton back = (ImageButton) popUpView.findViewById(R.id.reverse);

        back.setOnClickListener(v -> {
            mpopup.dismiss();
        });
        name.setText(Name);
        phone.setText(Phone);
        status.setText(Status);
        Glide.with(this).load(Profileimg).into(pic);
    }
public void wallpaper(){


    View popUpView = getLayoutInflater().inflate(R.layout.activity_wallpaper,
            null); // inflating popup layout
    mpopup = new PopupWindow(popUpView, Toolbar.LayoutParams.FILL_PARENT,
            Toolbar.LayoutParams.WRAP_CONTENT, true); // Creation of popup
    mpopup.setAnimationStyle(android.R.style.Animation_Dialog);
    mpopup.showAtLocation(popUpView, Gravity.FILL, 0, 0);

    ImageButton close=popUpView.findViewById(R.id.close);
    ImageView a = popUpView.findViewById(R.id.img);
    ImageView b = popUpView.findViewById(R.id.imageView4);
    ImageView c = popUpView.findViewById(R.id.imageView5);
    ImageView d = popUpView.findViewById(R.id.imageView6);
    ImageView e = popUpView.findViewById(R.id.imageView7);
    ImageView j = popUpView.findViewById(R.id.imageView9);
    ImageView k = popUpView.findViewById(R.id.imageView10);
    ImageView l = popUpView.findViewById(R.id.imageView11);
    ImageView h = popUpView.findViewById(R.id.imageView8);
    ImageView t = popUpView.findViewById(R.id.imageView14);
    ImageView q = popUpView.findViewById(R.id.imageView13);
    ImageView u = popUpView.findViewById(R.id.imageView15);
    ImageView x = popUpView.findViewById(R.id.imageView16);
    ImageView y = popUpView.findViewById(R.id.imageView17);
    ImageView z = popUpView.findViewById(R.id.imageView18);




    a.setOnClickListener(v->{
        Glide.with(this).load(R.drawable.a).into(background);
        SavePreferences(R.drawable.a);
        mpopup.dismiss();
    });
b.setOnClickListener(v->{
    Glide.with(this).load(R.drawable.b).into(background);
    SavePreferences(R.drawable.b);
    mpopup.dismiss();
});
    c.setOnClickListener(v->{
        Glide.with(this).load(R.drawable.c).into(background);
        SavePreferences(R.drawable.c);
        mpopup.dismiss();
    });
    d.setOnClickListener(v->{
        Glide.with(this).load(R.drawable.d).into(background);
        SavePreferences(R.drawable.d);
        mpopup.dismiss();
    });
    e.setOnClickListener(v->{
        Glide.with(this).load(R.drawable.e).into(background);
        SavePreferences(R.drawable.e);
        mpopup.dismiss();
    });
    h.setOnClickListener(v->{
        Glide.with(this).load(R.drawable.h).into(background);
        SavePreferences(R.drawable.h);
        mpopup.dismiss();
    });
    j.setOnClickListener(v->{
        Glide.with(this).load(R.drawable.j).into(background);
        SavePreferences(R.drawable.j);
        mpopup.dismiss();
    });
    k.setOnClickListener(v->{
        Glide.with(this).load(R.drawable.k).into(background);
        SavePreferences(R.drawable.k);
        mpopup.dismiss();
    });
    l.setOnClickListener(v->{
        Glide.with(this).load(R.drawable.l).into(background);
        SavePreferences(R.drawable.l);
        mpopup.dismiss();
    });
    x.setOnClickListener(v->{
        Glide.with(this).load(R.drawable.x).into(background);
        SavePreferences(R.drawable.x);
        mpopup.dismiss();
    });
    q.setOnClickListener(v->{
        Glide.with(this).load(R.drawable.q).into(background);
        SavePreferences(R.drawable.q);
        mpopup.dismiss();
    });
    t.setOnClickListener(v->{
        Glide.with(this).load(R.drawable.t).into(background);
        SavePreferences(R.drawable.t);
        mpopup.dismiss();
    });
    u.setOnClickListener(v->{
        Glide.with(this).load(R.drawable.u).into(background);
        SavePreferences(R.drawable.u);
        mpopup.dismiss();
    });
    y.setOnClickListener(v->{
        Glide.with(this).load(R.drawable.y).into(background);
        SavePreferences(R.drawable.y);
        mpopup.dismiss();
    });
    z.setOnClickListener(v->{
        Glide.with(this).load(R.drawable.z).into(background);
        SavePreferences(R.drawable.z);
        mpopup.dismiss();
    });

close.setOnClickListener(v->{
    mpopup.dismiss();
});




}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.call, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.wallpaper:
wallpaper();
                return true;
            case R.id.call:
                Intent intent=new Intent(this, Settings.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void SavePreferences(int drawable)
    {
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("wallpaper", drawable);
        editor.apply();
    }

    public void LoadPreferences()
    {
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        int  Wallpaper = sharedPreferences.getInt("wallpaper", R.color.white);
        Glide.with(this).load(Wallpaper).into(background);
    }

}