package com.example.smartlist.Activity;

public class ListData {
    String name;
    int desc;
    int image;

    public ListData(String name,  int desc, int image) {
        this.name = name;
        this.desc = desc;
        this.image = image;
    }
    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }
}