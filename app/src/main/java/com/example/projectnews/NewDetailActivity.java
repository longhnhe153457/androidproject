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
    String id, title,author, category, content, imageURL, createDate;
    private TextView titleTV, authorTV, contentTV, categoryTV, createDateTV;
    private ImageView newsIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_detail);

        id = getIntent().getStringExtra("newId");
        title = getIntent().getStringExtra("newTitle");
        author = getIntent().getStringExtra("newAuthor");
        content = getIntent().getStringExtra("newContent");
        category = getIntent().getStringExtra("newCategory");
        createDate = getIntent().getStringExtra("newCreateDate");
        imageURL = getIntent().getStringExtra("newImageLink");

        titleTV = findViewById(R.id.idTVTitle);
        contentTV = findViewById(R.id.idTVContent);
        newsIV = findViewById(R.id.idIVNews);
        categoryTV = findViewById(R.id.idTVCategory);
        authorTV = findViewById(R.id.idTVAuthor);
        createDateTV = findViewById(R.id.idTVCreateDate);

        titleTV.setText(title);
        categoryTV.setText(category);
        contentTV.setText(content);
        authorTV.setText(author);
        createDateTV.setText(createDate);
        Picasso.get().load(imageURL).into(newsIV);
    }
    public void onBackMain(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}