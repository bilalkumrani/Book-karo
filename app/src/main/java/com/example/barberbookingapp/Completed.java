package com.example.barberbookingapp;

public class Completed {
    private String Name;
    private String Desc;
    private int Photo;

    public Completed() {
    }

    public Completed(String name, String desc, int photo) {
        Name = name;
        Desc = desc;
        Photo = photo;
    }

    public String getName() {
        return Name;
    }

    public String getDesc() {
        return Desc;
    }

    public int getPhoto() {
        return Photo;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public void setPhoto(int photo) {
        Photo = photo;
    }
}
