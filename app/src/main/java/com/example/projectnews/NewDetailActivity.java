package com.example.projectnews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectnews.dao.DBHelper;
import com.example.projectnews.dao.INewDao;
import com.example.projectnews.dao.NewDao;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.InputStream;

public class NewDetailActivity extends AppCompatActivity {
    String id, title,author, category, content, imageURL, createDate, username;
    private TextView titleTV, authorTV, contentTV, categoryTV, createDateTV;
    private ImageView newsIV, btnSaveFavor;
    DBHelper dbHelper;
    INewDao newDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_detail);

        dbHelper = new DBHelper(this);
        newDao = new NewDao(this);
        username = dbHelper.getSession("username");
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
        btnSaveFavor = findViewById(R.id.idIvSaveNewFv);
        if(newDao.checkNewExistFavor(Integer.parseInt(id),username)){
            btnSaveFavor.setBackgroundResource(R.drawable.ic_save_favor_done);
        }else btnSaveFavor.setBackgroundResource(R.drawable.ic_save_favor);


        titleTV.setText(title);
        categoryTV.setText(category);
        contentTV.setText(content);
        authorTV.setText(author);
        createDateTV.setText(createDate);
        Picasso.get().load(imageURL).into(newsIV);

        btnSaveFavor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onSaveFavor(v);
            }
        });

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
    }
    public void shareMessenger(View v) {
        // showToast("checking");

        File dir = new File(Environment.getExternalStorageDirectory(), "MyFolder");

        File imgFile = new File(dir, "Image.png");

        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        sendIntent.setType("image/*");
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + imgFile));
        sendIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(imageURL));
        sendIntent.putExtra(Intent.EXTRA_TEXT, title);
        //sendIntent.setPackage("com.facebook.orca");
        sendIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        try {
            startActivity(Intent.createChooser(sendIntent, "Share images..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(NewDetailActivity.this, "Please Install Facebook Messenger", Toast.LENGTH_LONG).show();
        }

    }

    public void onBackMain(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
    public void onSaveFavor(View view) {
        int newId = Integer.parseInt(id);
        if(newDao.checkNewExistFavor(newId,username)){
            newDao.RemoveNewFavor(newId, username);
            btnSaveFavor.setBackgroundResource(R.drawable.ic_save_favor);
            Toast.makeText(NewDetailActivity.this, "Đã gỡ bài viết khỏi danh sách yêu thích", Toast.LENGTH_SHORT).show();
            return;
        }
        newDao.AddNewFavor(newId, username);
        Toast.makeText(NewDetailActivity.this, "Thêm bài viết vào danh sách yêu thích thành công", Toast.LENGTH_SHORT).show();
        btnSaveFavor.setBackgroundResource(R.drawable.ic_save_favor_done);
        Intent intent = new Intent(this, NewFavoriteActivity.class);
        startActivity(intent);
    }





}