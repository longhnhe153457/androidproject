package com.example.projectnews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.projectnews.adapter.NewAdapter;
import com.example.projectnews.adapter.NewCategoryAdapter;
import com.example.projectnews.adapter.adapterNew;
import com.example.projectnews.adapter.adapterchuyenmuc;
import com.example.projectnews.adapter.adapterthongtin;
import com.example.projectnews.model.CategoryRvModal;
import com.example.projectnews.model.New;
import com.example.projectnews.model.NewCategory;
import com.example.projectnews.model.TaiKhoan;
import com.example.projectnews.model.chuyenmuc;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NewCategoryAdapter.CategoryClickInterface {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    NavigationView navigationView;
    TextView username1;
    ListView listView,listViewNew,listViewThongTin;
    DrawerLayout drawerLayout;
    DBHelper dbHelper;
    ArrayList<TaiKhoan> taiKhoanArrayList;
    ArrayList<chuyenmuc> chuyenmucArrayList;
    TextView textName;
    adapterNew adapterNew;
    adapterthongtin adapterthongtin;
    adapterchuyenmuc adapterchuyenmuc;
    String email;
    String username;


    RecyclerView newsRV, newCateRV;
    ProgressBar loadingPB;
    ArrayList<New> newArrayList;
    ArrayList<CategoryRvModal> categoryRvModalArrayList;
    NewCategoryAdapter categoryRVAdapter;
    NewAdapter newsRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(this);
        Intent intent = getIntent();
        String username1 = intent.getStringExtra("username");

        LoadMainView();
        ActionBar();

     //   ActionViewFlipper();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                        Intent intent = new Intent(MainActivity.this, UpdateProfileActivity.class);
                        intent.putExtra("username",username1);
                        startActivity(intent);
//                    else {
//                        Toast.makeText(MainActivity.this,"Bạn không có quyền ",Toast.LENGTH_SHORT).show();
//                        Log.e("Đăng bài : ","Bạn không có quyền ");
//                    }
                }
                else if(position == 1){
                    Intent intent = new Intent(MainActivity.this,WeatherActity.class);
                    startActivity(intent);
                }
                else if(position == 2){
                    Intent intent = new Intent(MainActivity.this,MainActivityNote.class);
                    startActivity(intent);
                }
                else if(position == 3){
                    Intent intent = new Intent(MainActivity.this,StartGame.class);
                    startActivity(intent);
                }
                else if(position == 4){
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
    private void LoadMainView(){

        newsRV = findViewById(R.id.idRvNews);
        newCateRV = findViewById(R.id.idRVCategories);
        loadingPB = findViewById(R.id.idPBLoading);
        newArrayList = new ArrayList<>();
        getNews();
        categoryRvModalArrayList = new ArrayList<>();
        getCategories();
        newsRVAdapter = new NewAdapter(this, newArrayList);
        categoryRVAdapter = new NewCategoryAdapter(categoryRvModalArrayList, this, this::onCategoryClick);
        newsRV.setLayoutManager(new LinearLayoutManager(this));
        newsRV.setAdapter(newsRVAdapter);
        newCateRV.setAdapter(categoryRVAdapter);
        newsRVAdapter.notifyDataSetChanged();


       toolbar= findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper=findViewById(R.id.viewflipper);
        listView = findViewById(R.id.listviewmanhinhchinh);
        listViewThongTin=findViewById(R.id.listviewThongTin);
        navigationView=findViewById(R.id.navigationview);
        drawerLayout = findViewById(R.id.drawerlayout);

        //navigation
        Intent intent = getIntent();
        String username1 = intent.getStringExtra("username");
        if(username1 == null){
            username1 = null;

        }
        if(username1!=null) {
            Cursor cursor1 = dbHelper.getDatausername(username1);
        // String ten = cursor.getString(0);
            String sdt = cursor1.getString(6);
            taiKhoanArrayList = new  ArrayList<>();

            taiKhoanArrayList.add(new TaiKhoan(username1,sdt));

            adapterthongtin = new adapterthongtin(this, R.layout.navigation_thongtin,taiKhoanArrayList);
            listViewThongTin.setAdapter(adapterthongtin);
        }

        //Navigation 2
        chuyenmucArrayList =new ArrayList<>();
        chuyenmucArrayList.add(new chuyenmuc("Thông tin",R.drawable.ic_baseline_face_24));
        chuyenmucArrayList.add(new chuyenmuc("Thời tiết",R.drawable.ic_baseline_cloud_24));
        chuyenmucArrayList.add(new chuyenmuc("Ghi chú",R.drawable.ic_baseline_event_note_24));
        chuyenmucArrayList.add(new chuyenmuc("Quizz",R.drawable.ic_baseline_quiz_24));
        chuyenmucArrayList.add(new chuyenmuc("Đăng xuất",R.drawable.ic_baseline_login_24));
        adapterchuyenmuc =new adapterchuyenmuc(this, R.layout.chuyenmuc,chuyenmucArrayList);
        listView.setAdapter(adapterchuyenmuc);
    }

    private  void ActionBar(){
        setSupportActionBar(toolbar);
        //set nút của toolbar là true
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Tạo icon cho toolbar
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);

        //Tạo sự kiện click cho toolbar
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Gọi lại drawerlayout, do toolbar được gọi ra nhờ drawerlayout
                drawerLayout.openDrawer(GravityCompat.START);   //GravityCompat.START làm nó nhảy ra giữa
            }
        });
    }

    private void getCategories(){
        categoryRvModalArrayList.add(new CategoryRvModal(1,"Mới nhất", "https://bucket.nhanh.vn/store1/41822/menu/icon_15224_1568273112_icon-home-cam.png"));
        categoryRvModalArrayList.add(new CategoryRvModal(2,"Công nghệ", "https://i.chungta.vn/2020/01/24/AI-tech-620x389-2485-1579853809.jpg"));
        categoryRvModalArrayList.add(new CategoryRvModal(3,"Thế giới", "http://nghiencuuquocte.org/wp-content/uploads/2021/01/globe_hand.jpg"));
        categoryRvModalArrayList.add(new CategoryRvModal(4,"Thể thao", "https://irace.vn/wp-content/uploads/2019/10/silhouete-action-sport-outdoors-group-kids-having-fun-playing-soccer-football.jpg"));
        categoryRvModalArrayList.add(new CategoryRvModal(5,"Sức khỏe", "https://ichef.bbci.co.uk/news/640/cpsprodpb/D897/production/_101174455_whatsubject.jpg"));
        categoryRvModalArrayList.add(new CategoryRvModal(6,"Thời trang", "https://media.vneconomy.vn/w800/images/upload/2022/09/15/avaa.png"));
    }

    private void getNews(){
        loadingPB.setVisibility(View.VISIBLE);
        newArrayList.clear();
        newArrayList = dbHelper.getAllNews();
        loadingPB.setVisibility(View.GONE);

    }

    @Override
    public void onCategoryClick(int position) {

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