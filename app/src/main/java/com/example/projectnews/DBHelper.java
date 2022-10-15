package com.example.projectnews;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "Appdocbao.db";
    public DBHelper(Context context) {
        super(context, "Sign.db", null, 1);
    }

    private static String TABLE_BAO = "bao";
    private static String ID_BAO= "idbao";
    private static String TEN_BAO = "tieude";
    private static String NOI_DUNG = "noidung";
    private static String IMAGE = "anh";
//    private String SQLQuery1 = "CREATE TABLE "+ TABLE_TRUYEN +" ( "+ID_TRUYEN+" integer primary key AUTOINCREMENT, "
//            +TEN_TRUYEN+" TEXT UNIQUE, "
//            +NOI_DUNG+" TEXT, "
//            +IMAGE+" TEXT  )";



    @Override
    public void onCreate(SQLiteDatabase MyDB) {
       // MyDB.execSQL(SQLQuery1);
        MyDB.execSQL("create Table user(username TEXT primary key, password TEXT, email TEXT, status text, role text)");
       MyDB.execSQL("create Table bao(idbao integer primary key AUTOINCREMENT, tieude TEXT UNIQUE, noidung TEXT,  anh text)");
            MyDB.execSQL(SQLQuery2);

            MyDB.execSQL(SQLQuery4);
        MyDB.execSQL(SQLQuery5);
        MyDB.execSQL(SQLQuery6);
        MyDB.execSQL(SQLQuery7);
        MyDB.execSQL(SQLQuery8);


    }



    private String SQLQuery2 = "INSERT INTO user VAlUES ('admin','admin','admin@gmail.com',0,1)";
    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists user");
        MyDB.execSQL("drop Table if exists truyen");
        onCreate(MyDB);
    }



    public Boolean insertData(String username, String password,String email){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("email", email);
        contentValues.put("status", 0);
       contentValues.put("role", 0);
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
    public  Cursor getData(){
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor res=MyDB.rawQuery("SELECT * FROM "+TABLE_BAO,null);
        return res;

    }

    private String SQLQuery4 = "INSERT INTO bao VALUES (null,'Bao co co','','https://toplist.vn/images/800px/rua-va-tho-230179.jpg')";
    private String SQLQuery5 = "INSERT INTO bao VALUES (null,'Test db','','https://toplist.vn/images/800px/cu-cai-trang-230181.jpg')";
    private String SQLQuery6 = "INSERT INTO bao VALUES (null,'Minh hoa','','https://toplist.vn/images/800px/de-den-va-de-trang-230182.jpg')";
    private String SQLQuery7 = "INSERT INTO bao VALUES (null,'Anh hoat hinh','','https://toplist.vn/images/800px/chu-be-chan-cuu-230183.jpg')";
    private String SQLQuery8 = "INSERT INTO bao VALUES (null,'Anh nen','','https://toplist.vn/images/800px/cau-be-chan-cuu-va-cay-da-co-thu-230184.jpg')";


}