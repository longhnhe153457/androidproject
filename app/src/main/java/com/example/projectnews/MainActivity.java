package com.example.projectnews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.projectnews.adapter.adapterNew;
import com.example.projectnews.adapter.adapterchuyenmuc;
import com.example.projectnews.adapter.adapterthongtin;
import com.example.projectnews.model.New;
import com.example.projectnews.model.TaiKhoan;
import com.example.projectnews.model.chuyenmuc;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    NavigationView navigationView;
    ListView listView,listViewNew,listViewThongTin;
    DrawerLayout drawerLayout;

    DBHelper DBHelper;

    ArrayList<New> newArrayList;

    ArrayList<TaiKhoan> taiKhoanArrayList;

    ArrayList<chuyenmuc> chuyenmucArrayList;
    TextView textName;
//
    adapterNew adapterNew;
    adapterthongtin adapterthongtin;
    adapterchuyenmuc adapterchuyenmuc;
    String email;
    String username;

//public  void  viewData(){
//    Cursor cursor= DBHelper.viewData();
//    if(cursor.getCount()==0){
//        Toast.makeText(this,"No data to show",Toast.LENGTH_SHORT).show();
//
//    }
//    else{
//        while (cursor.moveToNext()){
//
//            listnew.add(cursor.getString(3));
//
//            listnew.add(cursor.getString(1));
//        }
//        adapterNew = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listnew);
//        listViewNew.setAdapter(adapterNew);
//    }
//
//}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBHelper = new DBHelper(this);



        Intent intent = getIntent();


        AnhXa();
        ActionBar();
        ActionViewFlipper();
      //  viewData();
      //  textName.setText(intent.getStringExtra("username"));
        listViewNew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent1=new Intent(MainActivity.this,MainNoiDung.class);
                startActivity(intent1);
                String tent =newArrayList.get(i).getTenBao();
                String noidungt = newArrayList.get(i).getNoiDung();
                intent1.putExtra("tentruyen",tent);
                intent1.putExtra("noidung",noidungt);
                startActivity(intent1);
                String text = listViewNew.getItemAtPosition(i).toString();
                Toast.makeText(MainActivity.this,""+text,Toast.LENGTH_SHORT).show();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if( i ==0 ){

                }
                else if( i==1){
                    finish();
                }

            }
        });

    }

    private  void ActionViewFlipper(){
        ArrayList<String> mangquangcao= new ArrayList<>();
        mangquangcao.add("https://suno.vn/blog/wp-content/uploads/2016/12/nhanvienkinhdoanhonline-01.png");
        mangquangcao.add("https://erpviet.vn/upload/Blog/Kien%20thuc%20quan%20tri%20doanh%20nghiep/cach-tu-van-khach-hang-1.jpg");
        mangquangcao.add("https://png.pngtree.com/png-vector/20191022/ourlarge/pngtree-online-assistant-woman-vector-consulting-client-customer-help-illustration-png-image_1840562.jpg");
        for(int i=0;i<mangquangcao.size();i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.get().load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            viewFlipper.addView(imageView);

        }
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);

        Animation animation_side_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animation_side_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_side_in);
        viewFlipper.setOutAnimation(animation_side_out);

    }
    private void AnhXa(){
        toolbar= findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper=findViewById(R.id.viewflipper);
        listView = findViewById(R.id.lisviewmanhinhchinh);
        listViewNew=findViewById(R.id.listviewnews);
        listViewThongTin=findViewById(R.id.listviewthongtin);
        navigationView=findViewById(R.id.navigationview);
        drawerLayout=findViewById(R.id.drawerlayout);
        textName= findViewById(R.id.TEXT_NAME);
        newArrayList= new ArrayList<>();

        Cursor cursor = DBHelper.getData();

        while (cursor.moveToNext()){
            int id =cursor.getInt(0);
            String tenbao=cursor.getString(1);
            String noidung = cursor.getString(2);
            String anh =cursor.getString(3);

            newArrayList.add(new New(id,tenbao,noidung,anh));

            adapterNew= new adapterNew( getApplicationContext() , newArrayList);

            listViewNew.setAdapter(adapterNew);
        }
        cursor.moveToFirst();
        cursor.close();

        taiKhoanArrayList = new  ArrayList<>();
        taiKhoanArrayList.add(new TaiKhoan(username,email));

        adapterthongtin = new adapterthongtin(this, R.layout.navigation_thongtin,taiKhoanArrayList);
        listViewThongTin.setAdapter(adapterthongtin);

        chuyenmucArrayList =new ArrayList<>();
        chuyenmucArrayList.add(new chuyenmuc("Thông tin",R.drawable.ic_baseline_face_24));
        chuyenmucArrayList.add(new chuyenmuc("Đăng xuất",R.drawable.ic_baseline_login_24));

        adapterchuyenmuc =new adapterchuyenmuc(this, R.layout.chuyenmuc,chuyenmucArrayList);

        listView.setAdapter(adapterchuyenmuc);
    }
    private  void ActionBar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch ((item.getItemId())){
            case R.id.menu1:
                Intent intent = new Intent(MainActivity.this,MainTimKiem.class);
                startActivity(intent);
                break;
            default:break;

        }
        return super.onOptionsItemSelected(item);
    }
}