package com.example.projectnews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectnews.dao.DBHelper;
import com.example.projectnews.dao.INewDao;
import com.example.projectnews.dao.NewDao;
import com.squareup.picasso.Picasso;

public class NewDetailActivity extends AppCompatActivity {
    String id, title,author, category, content, imageURL, createDate;
    private TextView titleTV, authorTV, contentTV, categoryTV, createDateTV;
    private ImageView newsIV;
    DBHelper dbHelper;
    INewDao newDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_detail);

        dbHelper = new DBHelper(this);
        newDao = new NewDao(this);

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
    public void onSaveFavor(View view) {
        int newId = Integer.parseInt(id);
        String username = dbHelper.getSession("username");
        newDao.AddNewFavor(newId, username);
        Intent intent = new Intent(this, NewFavoriteActivity.class);
        startActivity(intent);
    }
}