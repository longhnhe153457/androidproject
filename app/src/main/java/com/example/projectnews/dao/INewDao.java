package com.example.projectnews.dao;

import com.example.projectnews.model.CategoryRvModal;
import com.example.projectnews.model.New;

import java.util.ArrayList;

public interface INewDao {
    public ArrayList<New> getAllNews();
    public ArrayList<New> getNewsByCategory(String category);
    public ArrayList<New> getNewsBySearch(String text);
    public ArrayList<CategoryRvModal> getAllNewsCategory();
}
