package com.example.nguyendinhcong_2122110566;

public class Product {
    int Image;
    String Name;
    String Price;
    public Product(int image, String name, String price){
        this.Image=image;
        this.Name=name;
        this.Price=price;

    }
    public int getImage(){return Image;}
    public String getName(){return Name;}
    public String getPrice(){return Price;}
}
