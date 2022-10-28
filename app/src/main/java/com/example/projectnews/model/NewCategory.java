package com.example.projectnews.model;

public class NewCategory {
    private int ID;
    private String Name;

    public NewCategory() {
    }

    public NewCategory(int ID, String name) {
        this.ID = ID;
        Name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
