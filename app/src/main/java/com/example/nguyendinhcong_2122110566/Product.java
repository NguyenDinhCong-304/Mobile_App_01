package com.example.nguyendinhcong_2122110566;

import java.io.Serializable;

public class Product implements Serializable {
    int Image;
    String Name;
    String Price;
    int quantity;
    public Product(int image, String name, String price){
        this.Image=image;
        this.Name=name;
        this.Price=price;
        this.quantity = 1;
    }
    public int getImage(){return Image;}
    public String getName(){return Name;}
    public String getPrice(){return Price;}
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void increaseQuantity() { this.quantity++; }
    public void decreaseQuantity() { if (this.quantity > 1) this.quantity--; }
    private boolean selectedForCheckout = false;

    public boolean isSelectedForCheckout() {
        return selectedForCheckout;
    }

    public void setSelectedForCheckout(boolean selectedForCheckout) {
        this.selectedForCheckout = selectedForCheckout;
    }
}
