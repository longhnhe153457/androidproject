package com.example.projectnews.dao;

import com.example.projectnews.model.CategoryRvModal;
import com.example.projectnews.model.New;

import java.util.ArrayList;

public interface INewDao {
    public New getNewById(int Id);
    public ArrayList<New> getAllNews();
    public ArrayList<New> getNewsByCategory(String category);
    public ArrayList<New> getNewsBySearch(String text);
    public ArrayList<CategoryRvModal> getAllNewsCategory();
    public ArrayList<Integer> getListNewFavor(String username);
    public void AddNewFavor(int newId, String username);
    public void RemoveNewFavor(int newId, String username);
}
