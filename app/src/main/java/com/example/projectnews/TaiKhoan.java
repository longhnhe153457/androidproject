package com.example.projectnews;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectnews.adapter.EmployeeAdapterClass;
import com.example.projectnews.dao.DBHelper;

import java.util.List;

public class TaiKhoan extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actitvity_taikhoan);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        DBHelper databaseHelperClass = new DBHelper(this);
        List<com.example.projectnews.model.TaiKhoan> employeeModelClasses = databaseHelperClass.getEmployeeList();

        if (employeeModelClasses.size() > 0){
            EmployeeAdapterClass employeadapterclass = new EmployeeAdapterClass(employeeModelClasses,TaiKhoan.this);
            recyclerView.setAdapter(employeadapterclass);
        }else {
            Toast.makeText(this, "There is no employee in the database", Toast.LENGTH_SHORT).show();
        }

    }
}
