package com.bharath.flashmessenger.ViewModel;

import android.content.Context;
import android.net.Uri;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.bharath.flashmessenger.R;
import com.bumptech.glide.Glide;

import java.io.File;

public class UploadPreview {

    PopupWindow mpopup;
    ImageView imageView;
    TextView caption;
    ImageButton ulpoad,returner;

    public void preview(Context context, File file){



        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View popUpView = inflater.inflate(R.layout.uploadstatus, null); // inflating popup layout
        mpopup = new PopupWindow(popUpView, Toolbar.LayoutParams.MATCH_PARENT,
                Toolbar.LayoutParams.WRAP_CONTENT, true); // Creation of popup
        mpopup.setAnimationStyle(android.R.style.Animation_Dialog);
        mpopup.showAtLocation(popUpView, Gravity.FILL, 0, 0);

        imageView=popUpView.findViewById(R.id.previewimg);
        Uri path= Uri.fromFile(new File(String.valueOf(file)));
        Glide.with(context).load(path).into(imageView);
    }
}
