package com.example.projectnews;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "Appdocbao.db";
    Context context;
    public DBHelper(Context context) {
        super(context, "Signinb.db", null, 1);
        this.context = context;
    }

    private static String TABLE_BAO = "bao";
    private static String ID_BAO= "idbao";
    private static String TEN_BAO = "tieude";
    private static String NOI_DUNG = "noidung";
    private static String IMAGE = "anh";

    private static final String tableName = "mynotes";
    private static final String columnId = "id";
    private static final String columnTitle = "title";
    private static final String columnDescription = "description";



    @Override
    public void onCreate(SQLiteDatabase MyDB) {
       // MyDB.execSQL(SQLQuery1);
        MyDB.execSQL("create Table user(username TEXT primary key, password TEXT, email TEXT, status text, role text,avatar blob,showname text)");
       MyDB.execSQL("create Table bao(idbao integer primary key AUTOINCREMENT, tieude TEXT UNIQUE, noidung TEXT,  anh text)");


        String query = "CREATE TABLE "+tableName+
                " ("+columnId+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                columnTitle+ " TEXT, "+
                columnDescription +" Text);";

        MyDB.execSQL(SQLQuery2);

        MyDB.execSQL(SQLQuery4);
        MyDB.execSQL(SQLQuery5);
        MyDB.execSQL(SQLQuery6);
        MyDB.execSQL(SQLQuery7);
        MyDB.execSQL(SQLQuery8);

        // table note

        MyDB.execSQL(query);


    }



    private String SQLQuery2 = "INSERT INTO user VAlUES ('admin','admin','admin@gmail.com',0,1,'','admin')";
    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists user");
        MyDB.execSQL("drop Table if exists truyen");
        MyDB.execSQL("DROP TABLE IF EXISTS "+ tableName);
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
    public  Cursor getData(){
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor res=MyDB.rawQuery("SELECT * FROM "+TABLE_BAO,null);
        return res;

    }
    public Cursor getDatausername(String a) {

        SQLiteDatabase MyDB = this.getReadableDatabase();
        String sql="select * from user where username =?";
        Cursor cursor=MyDB.rawQuery(sql, new String[] { a });
        if (cursor != null)
        {   cursor.moveToFirst();}
        return cursor;
//        String ten = cursor.getString(0);
//        String sdt = cursor.getString(6);
//        byte[] anh = cursor.getBlob(5);


    }


    private String SQLQuery4 = "INSERT INTO bao VALUES (null,'Bao co co','','https://toplist.vn/images/800px/rua-va-tho-230179.jpg')";
    private String SQLQuery5 = "INSERT INTO bao VALUES (null,'Test db','','https://toplist.vn/images/800px/cu-cai-trang-230181.jpg')";
    private String SQLQuery6 = "INSERT INTO bao VALUES (null,'Minh hoa','','https://toplist.vn/images/800px/de-den-va-de-trang-230182.jpg')";
    private String SQLQuery7 = "INSERT INTO bao VALUES (null,'Anh hoat hinh','','https://toplist.vn/images/800px/chu-be-chan-cuu-230183.jpg')";
    private String SQLQuery8 = "INSERT INTO bao VALUES (null,'Anh nen','','https://toplist.vn/images/800px/cau-be-chan-cuu-va-cay-da-co-thu-230184.jpg')";

    //Notes


    public void  addNotes(String title, String desc){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(columnTitle,title);
        cv.put(columnDescription,desc);

        long resultValue = db.insert(tableName,null,cv);

        if (resultValue == -1){
            Toast.makeText(context, "Dữ liệu không được add ", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
        }

    }

    Cursor readNotes(){
        String query = "SELECT * FROM "+  tableName;
        SQLiteDatabase database= this.getReadableDatabase();

        Cursor cursor= null;
        if (database!= null){
            cursor = database.rawQuery(query,null);
        }
        return  cursor;
    }

    void deleteAllNotes(){
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "DELETE FROM "+ tableName;
        database.execSQL(query);

    }

    void updateNotes(String title,String desc , String id){
        SQLiteDatabase database =  this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(columnTitle,title);
        contentValues.put(columnDescription,desc);

        long resut  = database.update(tableName,contentValues,"id=?",new String[]{id});
        if (resut == -1){
            Toast.makeText(context, "Thất bại", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Hoàn thành!", Toast.LENGTH_SHORT).show();
        }
    }

    public  void  deleteSingleItem(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(tableName,"id=?", new String[]{id});
        if (result == -1){
            Toast.makeText(context, "Dữ liệu chưa được xóa", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Xóa dữ liệu thành công", Toast.LENGTH_SHORT).show();
        }
    }



}