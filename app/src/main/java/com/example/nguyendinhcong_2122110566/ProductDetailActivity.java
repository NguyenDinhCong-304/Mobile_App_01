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

public class ProductDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        ImageView imageView = findViewById(R.id.imageViewDetail);
        TextView nameView = findViewById(R.id.textNameDetail);
        TextView priceView = findViewById(R.id.textPriceDetail);

        // Lấy dữ liệu từ Intent
        int image = getIntent().getIntExtra("image", 0);
        String name = getIntent().getStringExtra("name");
        String price = getIntent().getStringExtra("price");

        imageView.setImageResource(image);
        nameView.setText(name);
        priceView.setText("Giá: " + price);

        Product product = new Product(image, name, price);

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ProductDetailActivity.this, HomeActivity.class);
                startActivity(it);
            }
        });
        Button btnAddToCart = findViewById(R.id.btnAddToCart);
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartManager.getInstance().addToCart(product);
                Toast.makeText(ProductDetailActivity.this, "Đã thêm vào giỏ: " + product.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        ImageButton btnCart = findViewById(R.id.btnCart);
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ProductDetailActivity.this, CartActivity.class);
                startActivity(it);
            }
        });
    }
}