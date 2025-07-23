package com.example.nguyendinhcong_2122110566;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.picasso.Picasso;

public class ProductDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        ImageView imageView = findViewById(R.id.imageViewDetail);
        TextView nameView = findViewById(R.id.textNameDetail);
        TextView desciptionView = findViewById(R.id.textDescription);
        TextView priceView = findViewById(R.id.textPriceDetail);

        // Nhận dữ liệu từ Intent
        String imageUrl = getIntent().getStringExtra("image");
        String name = getIntent().getStringExtra("name");
        String description = getIntent().getStringExtra("description");
        double price = getIntent().getDoubleExtra("price", 0.0);
        // Load ảnh từ URL
        Picasso.get().load(imageUrl).into(imageView);

        nameView.setText(name);
        priceView.setText("Giá: " + price);
        desciptionView.setText("Mô tả: " + description);
        Product product = new Product(0, name, price, imageUrl, description); // id = 0, dùng imageUrl

        Button btnAddToCart = findViewById(R.id.btnAddToCart);
        btnAddToCart.setOnClickListener(view -> {
            CartManager.getInstance().addToCart(product);
            Toast.makeText(ProductDetailActivity.this, "Đã thêm vào giỏ: " + product.getName(), Toast.LENGTH_SHORT).show();
        });

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(view -> {
            startActivity(new Intent(this, HomeActivity.class));
        });

        ImageButton btnCart = findViewById(R.id.btnCart);
        btnCart.setOnClickListener(view -> {
            startActivity(new Intent(this, CartActivity.class));
        });
    }
}