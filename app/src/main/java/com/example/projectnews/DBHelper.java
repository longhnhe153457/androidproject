package com.example.projectnews;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.Nullable;

import com.example.projectnews.model.New;
import com.example.projectnews.model.NewCategory;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "Appdocbao.db";
    public DBHelper(Context context) {
        super(context, "Signinb.db", null, 1);
    }

    private static String TABLE_BAO = "bao";
    private static String ID_BAO= "idbao";
    private static String TEN_BAO = "tieude";
    private static String NOI_DUNG = "noidung";
    private static String IMAGE = "anh";

    //NEW_CATEGORY TABLE
    private static final String NEW_CATEGORY_TABLE_NAME = "newcates";
    private static final String NEW_CAT_ID_COLUMN = "id";
    private static final String NEW_CAT_NAME_COLUMN = "name";

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        // MyDB.execSQL(SQLQuery1);
        MyDB.execSQL("create Table user(username TEXT primary key, password TEXT, email TEXT, status text, role text,avatar blob,showname text)");
        //MyDB.execSQL("create Table bao(idbao integer primary key AUTOINCREMENT, tieude TEXT UNIQUE, noidung TEXT,  anh text)");
        MyDB.execSQL("create Table bao(idbao integer primary key AUTOINCREMENT,category TEXT, tieude TEXT UNIQUE, noidung TEXT,  anh text)");
        String create_new_cat_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT)",
                NEW_CATEGORY_TABLE_NAME, NEW_CAT_ID_COLUMN, NEW_CAT_NAME_COLUMN);
        MyDB.execSQL(create_new_cat_table);

        //data user
        MyDB.execSQL(SQLQuery2);
        MyDB.execSQL(SQLQuery3);

        //data new
        MyDB.execSQL(SQLQuery4);
        MyDB.execSQL(SQLQuery5);
        MyDB.execSQL(SQLQuery6);
        MyDB.execSQL(SQLQuery7);
        MyDB.execSQL(SQLQuery8);

        //data new category
        MyDB.execSQL(SQLQuery9);
        MyDB.execSQL(SQLQuery10);
        MyDB.execSQL(SQLQuery11);
        MyDB.execSQL(SQLQuery12);
    }



    private String SQLQuery2 = "INSERT INTO user VAlUES ('admin','admin','admin@gmail.com',0,1,'','admin')";
    private String SQLQuery3 = "INSERT INTO user VAlUES ('a','a','admin@gmail.com',0,1,'','a')";
    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists user");
        MyDB.execSQL("drop Table if exists truyen");
        MyDB.execSQL("drop Table if exists bao");

        String drop_new_cat_table = String.format("DROP TABLE IF EXISTS %s", NEW_CATEGORY_TABLE_NAME);
//        String drop_new_table = String.format("DROP TABLE IF EXISTS %s", NEW_TABLE_NAME);
        MyDB.execSQL(drop_new_cat_table);
//        MyDB.execSQL(drop_new_table);

        onCreate(MyDB);
    }


    /*
        @author longhn
        Feature Public Query
    */
    public Boolean insertData(String username, String password,String email){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("email", email);
        contentValues.put("status", 0);
        contentValues.put("role", 0);
        contentValues.put("avatar", "");
        contentValues.put("showname", "");
        long result = MyDB.insert("user", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }
    public Boolean updatepass(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("password", password);

        long result = MyDB.update("user",  contentValues, "username = ?",new String[]{username});
        if(result==-1) return false;
        else
            return true;
    }
    public Boolean updateprofile(String username,String showname,  byte [] avatar){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("showname", showname);
        //       contentValues.put("email", email);
        contentValues.put("avatar", avatar);
        long result = MyDB.update("user",  contentValues, "username = ?",new String[]{username});
        if(result==-1) return false;
        else
            return true;
    }
    public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from user where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from user where username = ? and password = ?", new String[] {username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public Cursor getDatausername(String a) {

        SQLiteDatabase MyDB = this.getReadableDatabase();
        String sql="select * from user where username =?";
        Cursor cursor=MyDB.rawQuery(sql, new String[] { a });
        if (cursor != null)
        {   cursor.moveToFirst();}
        return cursor;
    }

    /*
       @author minhbd
       Feature News Query
   */
    //New Category
    public ArrayList<NewCategory> getAllNewCategories() {
        ArrayList<NewCategory>  newCatList = new ArrayList<>();
        String query = "SELECT * FROM " + NEW_CATEGORY_TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false) {
            NewCategory newObj = new NewCategory(cursor.getInt(0), cursor.getString(1));
            newCatList.add(newObj);
            cursor.moveToNext();
        }
        return newCatList;
    }

    //New
    public ArrayList<New> getAllNews() {
        ArrayList<New>  newList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_BAO;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false) {
            New newObj = new New(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
            newList.add(newObj);
            cursor.moveToNext();
        }
        return newList;
    }







    private String SQLQuery4 = "INSERT INTO bao VALUES (null, 'Moi', 'Bao co co','','https://toplist.vn/images/800px/rua-va-tho-230179.jpg')";
    private String SQLQuery5 = "INSERT INTO bao VALUES (null, 'Moi', 'Test db','','https://toplist.vn/images/800px/cu-cai-trang-230181.jpg')";
    private String SQLQuery6 = "INSERT INTO bao VALUES (null, 'Moi', 'Minh hoa','','https://toplist.vn/images/800px/de-den-va-de-trang-230182.jpg')";
    private String SQLQuery7 = "INSERT INTO bao VALUES (null, 'Moi', 'Anh hoat hinh','','https://toplist.vn/images/800px/chu-be-chan-cuu-230183.jpg')";
    private String SQLQuery8 = "INSERT INTO bao VALUES (null, 'Moi', 'Anh nen','','https://toplist.vn/images/800px/cau-be-chan-cuu-va-cay-da-co-thu-230184.jpg')";

    private String SQLQuery9 =  "INSERT INTO newcates VALUES (null, 'Mới nhất')";
    private String SQLQuery10 = "INSERT INTO newcates VALUES (null, 'Thế giới')";
    private String SQLQuery11 = "INSERT INTO newcates VALUES (null, 'Thể thao')";
    private String SQLQuery12 = "INSERT INTO newcates VALUES (null, 'Sức khỏe')";
}