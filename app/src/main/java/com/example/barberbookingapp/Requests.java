package com.example.barberbookingapp;

public class Requests {
    private String Name;
    private String Desc;
    private int Photo;

    public Requests() {
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
