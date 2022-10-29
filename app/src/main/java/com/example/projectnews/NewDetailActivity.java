package com.example.projectnews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NewDetailActivity extends AppCompatActivity {
    String id, title,desc, category, content, imageURL;
    private TextView titleTV, subDescTV, contentTV, categoryTV;
    private ImageView newsIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_detail);

        id = getIntent().getStringExtra("newId");
        title = getIntent().getStringExtra("newTitle");
        desc = getIntent().getStringExtra("newContent");
        content = getIntent().getStringExtra("newContent");
        category = getIntent().getStringExtra("newCategory");
        imageURL = getIntent().getStringExtra("newImageLink");

        titleTV = findViewById(R.id.idTVTitle);
        contentTV = findViewById(R.id.idTVContent);
        newsIV = findViewById(R.id.idIVNews);
        categoryTV = findViewById(R.id.idTVCategory);
        titleTV.setText(title);

        categoryTV.setText(category);
        contentTV.setText(content);

        Picasso.get().load(imageURL).into(newsIV);
    }
}