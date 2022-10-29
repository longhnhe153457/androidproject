package com.example.projectnews.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.projectnews.model.CategoryRvModal;
import com.example.projectnews.model.New;

import java.util.ArrayList;

public class NewDao extends DBHelper implements INewDao{

    public NewDao(Context context) {
        super(context);
    }

    /*
      @author minhbd
      Feature News Query
  */
    @Override
    public ArrayList<New> getAllNews() {
        ArrayList<New>  newList = new ArrayList<>();
        String query = "SELECT * FROM " +  NEW_TABLE_NAME;

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

    @Override
    public ArrayList<New> getNewsByCategory(String category) {
        if(category=="") category = "Mới nhất";
        ArrayList<New>  newList = new ArrayList<>();
        String query = "SELECT * FROM " +  NEW_TABLE_NAME +" WHERE "+ NEW_CATEGORY_NAME_COLUMN +" = '" + category+"';";

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

    @Override
    public ArrayList<CategoryRvModal> getAllNewsCategory() {
        ArrayList<CategoryRvModal>  newCateList = new ArrayList<>();
        String query = "SELECT * FROM " +  NEW_CATEGORY_TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false) {
            CategoryRvModal newCate = new CategoryRvModal(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
            newCateList.add(newCate);
            cursor.moveToNext();
        }
        return newCateList;
    }
}
