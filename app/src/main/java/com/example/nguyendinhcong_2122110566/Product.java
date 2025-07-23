package com.example.nguyendinhcong_2122110566;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String name;
    private double price;
    private String description;
    private String imageUrl;
    int quantity;
    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public Product(int id, String name, double price, String imageUrl, String description){
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
        this.quantity = 1;
    }
    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getDescription() { return description; }
    public String getImageUrl() { return imageUrl; }
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
