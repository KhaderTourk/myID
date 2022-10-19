package com.example.myshop.data;

import android.net.Uri;

public class Product{
    private String name;
    private String price;
    private String type;
    private String description;
    private Uri image;

    public Product(String name,String price,String type,String description, Uri image) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.description = description;
        this.image = image;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Uri getImage() {
        return image;
    }
    public void setImage(Uri image) {
        this.image = image;
    }
}