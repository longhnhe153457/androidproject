package com.example.projectnews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.projectnews.adapter.NewFavorAdapter;
import com.example.projectnews.dao.DBHelper;
import com.example.projectnews.dao.INewDao;
import com.example.projectnews.dao.NewDao;
import com.example.projectnews.model.New;

import java.util.ArrayList;

public class NewFavoriteActivity extends AppCompatActivity {
    ArrayList<New> newArrayList;
    INewDao newDao;
    DBHelper dbHelper;
    NewFavorAdapter newFavorAdapter;
    RecyclerView newsRV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_favourite);

        dbHelper = new DBHelper(this);
        newDao = new NewDao(this);
        newArrayList= new ArrayList<>();
        newsRV = findViewById(R.id.idRVNewsSearchResult);
        getNewFavor();

        newFavorAdapter = new NewFavorAdapter(this, newArrayList);
        newsRV.setLayoutManager(new LinearLayoutManager(this));
        newsRV.setAdapter(newFavorAdapter);
        newFavorAdapter.notifyDataSetChanged();
    }

    public void onBackMain(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    void getNewFavor(){
//        String username = dbHelper.getSession("username");
//        ArrayList<Integer> listNewId = new ArrayList<>();
//        listNewId = newDao.getListNewFavor(username);
//        for( int value : listNewId ) {
//            New newObj = newDao.getNewById(value);
//            if(newObj!=null) newArrayList.add(newObj);
//        }
        newArrayList = newDao.getAllNews();
    }
}