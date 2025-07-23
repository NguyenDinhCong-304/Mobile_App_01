package com.example.nguyendinhcong_2122110566;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private List<Product> allProducts = new ArrayList<>();
    private List<Product> filteredProducts = new ArrayList<>();
    private ProductAdapter adapter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ListView listView = findViewById(R.id.listView);
        adapter = new ProductAdapter(this, filteredProducts);
        listView.setAdapter(adapter);

        getProductsFromAPI(); // Gọi hàm sau khi thiết lập adapter

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Product product = filteredProducts.get(position);
            Intent intent = new Intent(HomeActivity.this, ProductDetailActivity.class);
            intent.putExtra("image", product.getImageUrl());
            intent.putExtra("name", product.getName());
            intent.putExtra("price", product.getPrice());
            intent.putExtra("description", product.getDescription());
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
        searchView = findViewById(R.id.searchView);

// Đặt sự kiện thay đổi văn bản tìm kiếm
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterProducts(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterProducts(newText);
                return false;
            }
        });
    }
    private void getProductsFromAPI() {

        String url = "https://fakestoreapi.com/products";
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET, url, null,
                response -> {
                    try {
                        allProducts.clear();
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject obj = response.getJSONObject(i);
                            int id = obj.getInt("id");
                            String title = obj.getString("title");
                            double price = obj.getDouble("price");
                            String image = obj.getString("image");
                            String description = obj.getString("description");

                            allProducts.add(new Product(id, title, price, image, description));
                        }

                        filteredProducts.clear();
                        filteredProducts.addAll(allProducts);
                        adapter.notifyDataSetChanged();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                },
                error -> Toast.makeText(this, "Lỗi khi tải sản phẩm", Toast.LENGTH_SHORT).show()
        );

        queue.add(request);
    }
    private void filterProducts(String keyword) {
        filteredProducts.clear();
        if (keyword.isEmpty()) {
            filteredProducts.addAll(allProducts);
        } else {
            for (Product product : allProducts) {
                if (product.getName().toLowerCase().contains(keyword.toLowerCase())) {
                    filteredProducts.add(product);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }
}

