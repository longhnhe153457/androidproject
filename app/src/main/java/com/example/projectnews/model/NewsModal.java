package com.example.projectnews.model;

import java.util.ArrayList;

public class NewsModal {
    private String status;
    private String totalResult;
    private ArrayList<New> articles;

    public NewsModal(String status, String totalResult, ArrayList<New> articles) {
        this.status = status;
        this.totalResult = totalResult;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(String totalResult) {
        this.totalResult = totalResult;
    }

    public ArrayList<New> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<New> articles) {
        this.articles = articles;
    }
}
