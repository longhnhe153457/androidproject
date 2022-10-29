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

import com.example.projectnews.model.New;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "Appdocbao.db";
    Context context;
    public DBHelper(Context context) {
        super(context, DBNAME, null, 1);
        this.context = context;
    }
    //NEW_CATEGORY TABLE
    private static final String NEW_CATEGORY_TABLE_NAME = "newcategory";
    private static final String NEW_CAT_ID_COLUMN = "id";
    private static final String NEW_CAT_NAME_COLUMN = "name";
    private static final String NEW_CAT_IMAGE_LINK_COLUMN = "imagelink";

    //NEW TABLE
    private static final String NEW_TABLE_NAME = "new";
    private static final String NEW_ID_COLUMN = "id";
    private static final String NEW_CATEGORY_NAME_COLUMN = "cate_name";
    private static final String NEW_TITLE_COLUMN = "title";
    private static final String NEW_CONTENT_COLUMN = "content";
    private static final String NEW_IMAGE_COLUMN = "image";
    private static final String NEW_AUTHOR_COLUMN = "author";
    private static final String NEW_CREATE_DATE_COLUMN = "create_date";

    //NOTE TABLE
    private static final String tableName = "mynotes";
    private static final String columnId = "id";
    private static final String columnTitle = "title";
    private static final String columnDescription = "description";

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
       // MyDB.execSQL(SQLQuery1);
        String create_new_cat_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT)",
                NEW_CATEGORY_TABLE_NAME, NEW_CAT_ID_COLUMN, NEW_CAT_NAME_COLUMN, NEW_CAT_IMAGE_LINK_COLUMN);
        String create_new_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT)",
                NEW_TABLE_NAME, NEW_ID_COLUMN, NEW_TITLE_COLUMN, NEW_CATEGORY_NAME_COLUMN, NEW_CONTENT_COLUMN, NEW_IMAGE_COLUMN, NEW_AUTHOR_COLUMN, NEW_CREATE_DATE_COLUMN);
        String query = "CREATE TABLE "+tableName+
                " ("+columnId+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                columnTitle+ " TEXT, "+
                columnDescription +" Text);";

        MyDB.execSQL("create Table user(username TEXT primary key, password TEXT, email TEXT, status text, role text,avatar blob,showname text)");
        MyDB.execSQL(query);
        MyDB.execSQL(create_new_cat_table);
        MyDB.execSQL(create_new_table);

        //User
        MyDB.execSQL(SQLQuery2);

        //New
        MyDB.execSQL(SQLQuery4);
        MyDB.execSQL(SQLQuery5);
        MyDB.execSQL(SQLQuery6);
        MyDB.execSQL(SQLQuery7);
        MyDB.execSQL(SQLQuery8);
    }
    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists user");
        MyDB.execSQL("drop Table if exists truyen");
        MyDB.execSQL("DROP TABLE IF EXISTS "+ tableName);
        MyDB.execSQL("DROP TABLE IF EXISTS "+ NEW_TABLE_NAME);
        MyDB.execSQL("DROP TABLE IF EXISTS "+ NEW_CATEGORY_TABLE_NAME);
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
//        String ten = cursor.getString(0);
//        String sdt = cursor.getString(6);
//        byte[] anh = cursor.getBlob(5);


    }

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

    /*
       @author minhbd
       Feature News Query
   */
    public ArrayList<New> getAllNews() {
        ArrayList<New>  newList = new ArrayList<>();
        String query = "SELECT * FROM " + NEW_TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false) {
            New newObj = new New(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getString(4), cursor.getString(5), cursor.getString(6));
            newList.add(newObj);
            cursor.moveToNext();
        }
        return newList;
    }

    //DATA USER
    private String SQLQuery2 = "INSERT INTO user VAlUES ('admin','admin','admin@gmail.com',0,1,'','admin')";

    //DATA NEW
    private String SQLQuery4 = "INSERT INTO " +NEW_TABLE_NAME+ " VALUES (null,'Bao co co','The gioi', 'a','https://toplist.vn/images/800px/rua-va-tho-230179.jpg', 'Duc Minh', '12/2/2022')";
    private String SQLQuery5 = "INSERT INTO " +NEW_TABLE_NAME+ " VALUES (null,'Test db','The thao','a','https://toplist.vn/images/800px/cu-cai-trang-230181.jpg', 'Duc Minh', '12/2/2022')";
    private String SQLQuery6 = "INSERT INTO " +NEW_TABLE_NAME+ " VALUES (null,'Minh hoa','The thao','a','https://toplist.vn/images/800px/de-den-va-de-trang-230182.jpg', 'Duc Minh', '12/2/2022')";
    private String SQLQuery7 = "INSERT INTO " +NEW_TABLE_NAME+ " VALUES (null,'Anh hoat hinh','The thao','a','https://toplist.vn/images/800px/chu-be-chan-cuu-230183.jpg', 'Duc Minh', '12/2/2022')";
    private String SQLQuery8 = "INSERT INTO " +NEW_TABLE_NAME+ " VALUES (null,'Anh nen','Suc Khoe','a','https://toplist.vn/images/800px/cau-be-chan-cuu-va-cay-da-co-thu-230184.jpg', 'Duc Minh', '12/2/2022')";


}