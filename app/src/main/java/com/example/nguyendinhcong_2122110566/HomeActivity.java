package com.example.nguyendinhcong_2122110566;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

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
        products.add(new Product(R.drawable.oppo_reno_11, "Oppo Reno 11", "12.000.000"));
        products.add(new Product(R.drawable.xiaomi_redmi_13, "Xiaomi Redmi", "6.500.000"));

        ProductAdapter adapter = new ProductAdapter(this, products);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Product product = products.get(position);
            Intent intent = new Intent(HomeActivity.this, ProductDetailActivity.class);
            intent.putExtra("image", product.getImage());
            intent.putExtra("name", product.getName());
            intent.putExtra("price", product.getPrice());
            startActivity(intent);
        });
        //nút đến trang giỏ hàng
        ImageButton btnCart = findViewById(R.id.btnCart);
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(HomeActivity.this, CartActivity.class);
                startActivity(it);
            }
        });
        //Nút đến trang user
        ImageButton btnUser = findViewById(R.id.btnUser);
        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(HomeActivity.this, UserActivity.class);
                startActivity(it);
            }
        });
//        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
//        bottomNav.setOnItemSelectedListener(item -> {
//            switch (item.getItemId()) {
//                case R.id.nav_home:
//                    // Đang ở Home, không làm gì
//                    return true;
//                case R.id.nav_cart:
//                    startActivity(new Intent(this, CartActivity.class));
//                    return true;
//                case R.id.nav_account:
//                    Toast.makeText(this, "Tài khoản", Toast.LENGTH_SHORT).show();
//                    return true;
//            }
//            return false;
//        });
    }
}