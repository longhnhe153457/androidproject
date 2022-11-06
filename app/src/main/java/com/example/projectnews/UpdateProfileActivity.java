package com.example.projectnews;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projectnews.dao.DBHelper;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class UpdateProfileActivity extends AppCompatActivity {
    final String DATABASE_NAME = "Appdocbao.db";
    final int RESQUEST_TAKE_PHOTO = 123;
    final int REQUEST_CHOOSE_PHOTO = 321;
    Button btnChonHinh, btnChupHinh, btnLuu,btnHuy;
    EditText edtTen,edtGamil;
    ImageView imgHinhDaiDien;
    com.example.projectnews.dao.DBHelper DBHelper;
    String username;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        DBHelper = new DBHelper(this);

        Intent intent = getIntent();
        username=(intent.getStringExtra("username"));
        addControls();
        addEvents();
        if(username!=null) {
            Cursor cursor = DBHelper.getDatausername(username);
            String sdt = cursor.getString(6);
            byte[] anh = cursor.getBlob(5);

            Bitmap bitmap = BitmapFactory.decodeByteArray(anh, 0, anh.length);

            Bitmap icon = BitmapFactory.decodeResource(getResources(),
                    R.drawable.anou);

            if(bitmap == null){
                imgHinhDaiDien.setImageBitmap(icon);
            }
            else {
                imgHinhDaiDien.setImageBitmap(bitmap);
            }
            edtGamil.setText(sdt);
        }
        else{
            Toast.makeText(UpdateProfileActivity.this, "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
        }

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String ten = edtTen.getText().toString();
                String tendung = edtGamil.getText().toString();
                byte[] anh = getByteArrayFromImageView(imgHinhDaiDien);
                Boolean checkuser = DBHelper.updateprofile(username, tendung,anh);
                if (checkuser == true ) {
                    Intent intent = new Intent(UpdateProfileActivity.this, MainActivity.class);
                    intent.putExtra("username",username);
                    Toast.makeText(UpdateProfileActivity.this, "Update thông tin thành công", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();

                }
                else{
                    Toast.makeText(UpdateProfileActivity.this, "Xảy ra lỗi", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }





    private void addEvents(){
        btnChonHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePhoto();
            }
        });

        btnChupHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });


        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });
    }


    private void takePicture(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, RESQUEST_TAKE_PHOTO);
    }

    private void choosePhoto(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CHOOSE_PHOTO);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == REQUEST_CHOOSE_PHOTO){
                try {
                    Uri imageUri = data.getData();
                    InputStream is = getContentResolver().openInputStream(imageUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    imgHinhDaiDien.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }else if(requestCode == RESQUEST_TAKE_PHOTO){
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imgHinhDaiDien.setImageBitmap(bitmap);
            }
        }
    }



    private byte[] getByteArrayFromImageView(ImageView imgv){
        BitmapDrawable drawable = (BitmapDrawable) imgv.getDrawable();
        Bitmap bmp = drawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    private void cancel(){
        Intent intent = new Intent(UpdateProfileActivity.this, MainActivity.class);
        intent.putExtra("username",username);
        startActivity(intent);
        finish();
    }



    private  void addControls(){
        btnChonHinh = (Button) findViewById(R.id.btnChonHinh);
        btnChupHinh = (Button) findViewById(R.id.btnChupHinh);
        btnLuu = (Button) findViewById(R.id.btnLuu);
        btnHuy = (Button) findViewById(R.id.btnHuy);

       // edtTen = (EditText) findViewById(R.id.edtTen);
        edtGamil = (EditText) findViewById(R.id.edtSdt);
        imgHinhDaiDien = (ImageView) findViewById(R.id.imgHinhDaiDien);
    }



}
