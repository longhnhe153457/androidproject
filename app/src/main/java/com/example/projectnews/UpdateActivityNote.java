package com.example.projectnews;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectnews.dao.DBHelper;

public class UpdateActivityNote extends AppCompatActivity {
    EditText title, desc;
    Button update;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatenote);

        title = findViewById(R.id.title);
        desc = findViewById(R.id.description);
        update = findViewById(R.id.update_note);

        Intent i = getIntent();
        title.setText(i.getStringExtra("title"));
        desc.setText(i.getStringExtra("desc"));
        id=i.getStringExtra("id");

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(title.getText().toString()) && !TextUtils.isEmpty(desc.getText().toString())){
                    DBHelper database = new DBHelper(UpdateActivityNote.this);
                    database.updateNotes(title.getText().toString(),desc.getText().toString(),id);
                    startActivity(new Intent(UpdateActivityNote.this, MainActivityNote.class));
                } else {
                    Toast.makeText(UpdateActivityNote.this, "", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}