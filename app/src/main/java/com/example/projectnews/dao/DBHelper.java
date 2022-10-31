package com.example.projectnews.dao;

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
    //SESSION TABLE
    public static final String SESSION_TABLE_NAME = "session";
    public static final String SESSION_KEY_COLUMN = "key";
    public static final String SESSION_VALUE_COLUMN = "value";

    //NEW_CATEGORY TABLE
    public static final String NEW_CATEGORY_TABLE_NAME = "newcategory";
    public static final String NEW_CAT_ID_COLUMN = "id";
    public static final String NEW_CAT_NAME_COLUMN = "name";
    public static final String NEW_CAT_IMAGE_LINK_COLUMN = "imagelink";

    //NEW TABLE
    public static final String NEW_TABLE_NAME = "new";
    public static final String NEW_ID_COLUMN = "id";
    public static final String NEW_CATEGORY_NAME_COLUMN = "cate_name";
    public static final String NEW_TITLE_COLUMN = "title";
    public static final String NEW_CONTENT_COLUMN = "content";
    public static final String NEW_IMAGE_COLUMN = "image";
    public static final String NEW_AUTHOR_COLUMN = "author";
    public static final String NEW_CREATE_DATE_COLUMN = "create_date";

    //NEW FAVOR TABLE
    public static final String NEW_FAVOR_TABLE_NAME = "new_favor";
    public static final String NEW_FAVOR_ID_COLUMN = "id";
    public static final String NEW_FAVOR_NEW_ID_COLUMN = "new_id";
    public static final String NEW_FAVOR_USER_COLUMN = "username";

    //NOTE TABLE
    private static final String tableName = "mynotes";
    private static final String columnId = "id";
    private static final String columnTitle = "title";
    private static final String columnDescription = "description";

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
       // MyDB.execSQL(SQLQuery1);
        String create_session_table = String.format("CREATE TABLE %s(%s TEXT, %s TEXT)",
                SESSION_TABLE_NAME, SESSION_KEY_COLUMN, SESSION_VALUE_COLUMN);
        String create_new_cat_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT)",
                NEW_CATEGORY_TABLE_NAME, NEW_CAT_ID_COLUMN, NEW_CAT_NAME_COLUMN, NEW_CAT_IMAGE_LINK_COLUMN);
        String create_new_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT)",
                NEW_TABLE_NAME, NEW_ID_COLUMN, NEW_CATEGORY_NAME_COLUMN, NEW_TITLE_COLUMN, NEW_CONTENT_COLUMN, NEW_IMAGE_COLUMN, NEW_AUTHOR_COLUMN, NEW_CREATE_DATE_COLUMN);
        String create_new_favor_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s INTEGER, %s TEXT)",
                NEW_FAVOR_TABLE_NAME, NEW_FAVOR_ID_COLUMN, NEW_FAVOR_NEW_ID_COLUMN, NEW_FAVOR_USER_COLUMN);

        String query = "CREATE TABLE "+tableName+
                " ("+columnId+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                columnTitle+ " TEXT, "+
                columnDescription +" Text);";

        MyDB.execSQL("create Table user(username TEXT primary key, password TEXT, email TEXT, status text, role text,avatar blob,showname text)");
        MyDB.execSQL(query);
        MyDB.execSQL(create_session_table);
        MyDB.execSQL(create_new_cat_table);
        MyDB.execSQL(create_new_table);
        MyDB.execSQL(create_new_favor_table);

        //User
        MyDB.execSQL(SQLQuery2);

        //New
        MyDB.execSQL(insertTableNewCategoryquery);
        MyDB.execSQL(insertTableNewquery);

    }
    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists user");
        MyDB.execSQL("drop Table if exists truyen");
        MyDB.execSQL("DROP TABLE IF EXISTS "+ tableName);
        MyDB.execSQL("DROP TABLE IF EXISTS "+ SESSION_TABLE_NAME);
        MyDB.execSQL("DROP TABLE IF EXISTS "+ NEW_TABLE_NAME);
        MyDB.execSQL("DROP TABLE IF EXISTS "+ NEW_FAVOR_TABLE_NAME);
        MyDB.execSQL("DROP TABLE IF EXISTS "+ NEW_CATEGORY_TABLE_NAME);
        onCreate(MyDB);
    }
    /**
     * @author Minhbd
     * Query xử lý session cho app
     * **/
    public void addSession(String key, String value) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SESSION_KEY_COLUMN, key);
        values.put(SESSION_VALUE_COLUMN, value);

        db.insert(SESSION_TABLE_NAME, null, values);
        db.close();
    }

    public String getSession(String key) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(SESSION_TABLE_NAME, null, SESSION_KEY_COLUMN + " = ?", new String[] { String.valueOf(key) },null, null, null);
        if(cursor != null){
            if(cursor.getCount()==0) return null;
            cursor.moveToFirst();
        }

        String value = cursor.getString(1);
        return value;
    }
    public void removeSession(String key) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(SESSION_TABLE_NAME, SESSION_KEY_COLUMN + " = ?", new String[] { String.valueOf(key) });
        db.close();
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
    public Cursor readNotes(){
        String query = "SELECT * FROM "+  tableName;
        SQLiteDatabase database= this.getReadableDatabase();

        Cursor cursor= null;
        if (database!= null){
            cursor = database.rawQuery(query,null);
        }
        return  cursor;
    }
    public void deleteAllNotes(){
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "DELETE FROM "+ tableName;
        database.execSQL(query);

    }
    public void updateNotes(String title,String desc , String id){
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



    //DATA USER
    private String SQLQuery2 = "INSERT INTO user VAlUES ('admin','admin','admin@gmail.com',0,1,'','admin')";

    //DATA NEW CATEGORY
    private String insertTableNewCategoryquery = "INSERT INTO "+NEW_CATEGORY_TABLE_NAME+" ("+NEW_CAT_NAME_COLUMN+","+NEW_CAT_IMAGE_LINK_COLUMN+" ) VALUES \n" +
            "   ('Mới nhất', 'https://play-lh.googleusercontent.com/P8D-vfnCmeaP3b3pbS_JmWlDkGGYaPg1xE4rOXMWPiTsL8fKlpsTxgVOkWj7w1ryx0pC'),\n" +
            "   ('Công nghệ', 'https://i.chungta.vn/2020/01/24/AI-tech-620x389-2485-1579853809.jpg'),\n" +
            "   ('Thế giới', 'http://nghiencuuquocte.org/wp-content/uploads/2021/01/globe_hand.jpg'),\n" +
            "   ('Thể thao', 'https://irace.vn/wp-content/uploads/2019/10/silhouete-action-sport-outdoors-group-kids-having-fun-playing-soccer-football.jpg'),\n" +
            "   ('Sức khỏe', 'https://ichef.bbci.co.uk/news/640/cpsprodpb/D897/production/_101174455_whatsubject.jpg'),\n" +
            "   ('Thời trang', 'https://media.vneconomy.vn/w800/images/upload/2022/09/15/avaa.png');";

    //DATA NEW
    private String insertTableNewquery = "INSERT INTO "+NEW_TABLE_NAME+" ("+NEW_CATEGORY_NAME_COLUMN+","+NEW_TITLE_COLUMN+","+NEW_CONTENT_COLUMN+","+NEW_IMAGE_COLUMN+","+NEW_AUTHOR_COLUMN+","+NEW_CREATE_DATE_COLUMN+" ) VALUES \n" +
            "   ( 'Thể Thao', 'Quang Hải không dự AFF Cup 2022', 'Liên đoàn Bóng đá Việt Nam xác nhận tiền vệ Nguyễn Quang Hải không tham dự AFF Cup 2022 cùng đội tuyển Việt Nam.\n" +
            "\n" +
            "Tổng thư ký VFF Lê Hoài Anh cho biết đã làm việc với Pau FC - đội bóng chủ quản hiện tại của Quang Hải. CLB nước Pháp quyết định không nhả người do thời gian tổ chức AFF Cup không thuộc FIFA Days. \"VFF đã cố gắng nhưng CLB có toàn quyền quyết định\", ông Hoài Anh xác nhận với VnExpress chiều 29/10. \"Quang Hải phải tham dự cả các trận giao hữu cùng CLB theo quy định cầu thủ chuyên nghiệp\"', 'https://i1-thethao.vnecdn.net/2022/10/29/-7331-1667039856.jpg?w=680&h=0&q=100&dpr=1&fit=crop&s=VRyJuGY6aC2oQOkWgt1UYg', 'Hiếu Lương', '29/10/2022'),\n" +

            "   ( 'Công nghệ', 'Trên tay Canon EOS R7: Quay được 4K60, chống rung ngon, tracking rất nhạy', 'Canon EOS R7 là một chiếc máy cảm biến crop có khả năng quay 4K60fps, độ phân giải ảnh 32,5MP và tốc độ chụp lên tới 30fps nhưng có mức giá cực kì tốt từ nhà Canon. Ngoại hình chắc chắn và menu dễ dùng cùng layout phím bấm cực kì thân thiện, khiến R7 cho mình khá nhiều ấn tượng tốt.', 'https://photo2.tinhte.vn/data/attachment-files/2022/10/6180046_cover_tren-tay-canon-eos-r7-1.jpg', 'Đức Minh', '30/10/2022'),\n" +
            "   ( 'Công nghệ', 'Trên tay Canon EOS R7: Quay được 4K60, chống rung ngon, tracking rất nhạy', 'Canon EOS R7 là một chiếc máy cảm biến crop có khả năng quay 4K60fps, độ phân giải ảnh 32,5MP và tốc độ chụp lên tới 30fps nhưng có mức giá cực kì tốt từ nhà Canon. Ngoại hình chắc chắn và menu dễ dùng cùng layout phím bấm cực kì thân thiện, khiến R7 cho mình khá nhiều ấn tượng tốt.', 'https://photo2.tinhte.vn/data/attachment-files/2022/10/6180046_cover_tren-tay-canon-eos-r7-1.jpg', 'Đức Minh', '30/10/2022'),\n" +
            "   ( 'Công nghệ', 'Trên tay Canon EOS R7: Quay được 4K60, chống rung ngon, tracking rất nhạy', 'Canon EOS R7 là một chiếc máy cảm biến crop có khả năng quay 4K60fps, độ phân giải ảnh 32,5MP và tốc độ chụp lên tới 30fps nhưng có mức giá cực kì tốt từ nhà Canon. Ngoại hình chắc chắn và menu dễ dùng cùng layout phím bấm cực kì thân thiện, khiến R7 cho mình khá nhiều ấn tượng tốt.', 'https://photo2.tinhte.vn/data/attachment-files/2022/10/6180046_cover_tren-tay-canon-eos-r7-1.jpg', 'Đức Minh', '30/10/2022'),\n" +
            "   ( 'Công nghệ', 'Trên tay cục CarPlay không dây của QuadLock: nhỏ gọn, kết nối khá nhanh', 'Cho tới khi cầm cục này trên tay thì mình chỉ biết thương hiệu QuadLock chuyên làm các ngàm xịn gắn điện thoại, case gắn điện thoại lên xe mô tô, xe ô tô, và chắc cũng đã nhiều anh em chơi xe sử dụng cái case của QuadLock rồi. Nếu anh em đã tin yêu thương hiệu này thì giờ có thể sử dụng thêm một món của họ, cục CarPlay không dây.', 'https://photo2.tinhte.vn/data/attachment-files/2022/10/6186575_Cover.jpg', 'Đức Minh', '2/11/2022');";
}