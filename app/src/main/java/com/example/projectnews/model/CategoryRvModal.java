package com.example.projectnews.model;

public class CategoryRvModal {
    private int id;
    private String category;
    private String categoryImageUrl;

    public CategoryRvModal() {
    }

    public CategoryRvModal(int id, String category, String categoryImageUrl) {
        this.id = id;
        this.category = category;
        this.categoryImageUrl = categoryImageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryImageUrl() {
        return categoryImageUrl;
    }

    public void setCategoryImageUrl(String categoryImageUrl) {
        this.categoryImageUrl = categoryImageUrl;
    }
}
