package com.example.projectnews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.projectnews.adapter.NewAdapter;
import com.example.projectnews.adapter.NewCategoryAdapter;
import com.example.projectnews.dao.INewDao;
import com.example.projectnews.dao.NewDao;
import com.example.projectnews.model.CategoryRvModal;
import com.example.projectnews.model.New;

import java.util.ArrayList;

public class NewSearchActivity extends AppCompatActivity {
    private EditText editTextSearchNew;
    INewDao newDao;
    TextView tvResultSearch;
    RecyclerView newsRV;
    ArrayList<New> newArrayList;
    NewAdapter newsRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_search);

        newArrayList= new ArrayList<>();
        editTextSearchNew = findViewById(R.id.idEDNewSearch);
        newsRV = findViewById(R.id.idRVNewsSearchResult);
        tvResultSearch = findViewById((R.id.idTVResultNewSearch));
    }

    public void onBackMain(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void onSearchNew(View view){
        String textSearch = editTextSearchNew.getText().toString().trim();
        newDao = new NewDao(this);
        newArrayList= newDao.getNewsBySearch(textSearch);
        newsRVAdapter = new NewAdapter(this, newArrayList);
        newsRV.setLayoutManager(new LinearLayoutManager(this));
        newsRV.setAdapter(newsRVAdapter);
        tvResultSearch.setText(Integer.toString(newArrayList.size()) + " kết quả");
        newsRVAdapter.notifyDataSetChanged();
    }
}