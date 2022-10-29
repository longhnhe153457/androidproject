package com.example.projectnews.model;

public class New {
    private int ID;
    private String Category;
    private String Title;
    private String Content;
    private String ImageLink;
    private String Author;
    private String CreateDate;

    public New() {}

    public New(int ID, String title, String content, String imageLink, String author, String createDate) {
        this.ID = ID;
        Title = title;
        Content = content;
        ImageLink = imageLink;
        Author = author;
        CreateDate = createDate;
    }

    public New(int ID, String category, String title, String content, String imageLink, String author, String createDate) {
        this.ID = ID;
        Category = category;
        Title = title;
        Content = content;
        ImageLink = imageLink;
        Author = author;
        CreateDate = createDate;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getImageLink() {
        return ImageLink;
    }

    public void setImageLink(String imageLink) {
        ImageLink = imageLink;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String createDate) {
        CreateDate = createDate;
    }
}
