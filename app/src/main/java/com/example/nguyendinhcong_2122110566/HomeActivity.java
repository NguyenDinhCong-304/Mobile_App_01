package com.example.nguyendinhcong_2122110566;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ListView listView = findViewById(R.id.listView);

        List<Product> products = new ArrayList<>();
        products.add(new Product(R.drawable.iphone_16_pro_max, "Iphone 16", "29.000.000"));
        products.add(new Product(R.drawable.samsung_s24, "Samsung S24", "23.000.000"));
        products.add(new Product(R.drawable.oppo_reno11, "Oppo Reno 11", "12.000.000"));
        products.add(new Product(R.drawable.xiaomi_redmi_13, "Xiaomi Redmi", "6.500.000"));

        ProductAdapter adapter = new ProductAdapter(this, products);
        listView.setAdapter(adapter);

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(it);
            }
        });
    }
}