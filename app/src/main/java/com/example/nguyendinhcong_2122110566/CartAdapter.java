package com.example.nguyendinhcong_2122110566;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class CartAdapter extends ArrayAdapter<Product> {
    private Context context;
    private List<Product> cartItems;

    public CartAdapter(Context context, List<Product> cartItems) {
        super(context, 0, cartItems);
        this.context = context;
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        }

        Product product = cartItems.get(position);

        ImageView image = convertView.findViewById(R.id.imageViewCart);
        TextView name = convertView.findViewById(R.id.textCartName);
        TextView price = convertView.findViewById(R.id.textCartPrice);
        TextView quantityText = convertView.findViewById(R.id.textQuantity);
        Button btnIncrease = convertView.findViewById(R.id.btnIncrease);
        Button btnDecrease = convertView.findViewById(R.id.btnDecrease);
        Button btnRemove = convertView.findViewById(R.id.btnRemove);

        image.setImageResource(product.getImage());
        name.setText(product.getName());
        price.setText("Giá: " + product.getPrice());
        quantityText.setText(String.valueOf(product.getQuantity()));

        btnIncrease.setOnClickListener(v -> {
            product.increaseQuantity();
            notifyDataSetChanged();
            ((CartActivity) context).updateTotalPrice();
        });

        btnDecrease.setOnClickListener(v -> {
            product.decreaseQuantity();
            notifyDataSetChanged();
            ((CartActivity) context).updateTotalPrice();
        });

        btnRemove.setOnClickListener(v -> {
            cartItems.remove(position);
            notifyDataSetChanged();
            ((CartActivity) context).updateTotalPrice();
        });

        return convertView;
    }
}

