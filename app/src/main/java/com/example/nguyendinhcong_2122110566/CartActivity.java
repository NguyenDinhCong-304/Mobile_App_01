package com.example.nguyendinhcong_2122110566;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class CartActivity extends AppCompatActivity {
    private ListView cartListView;
    private TextView totalPriceTextView;
    private CartAdapter adapter;
    private List<Product> cartItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartListView = findViewById(R.id.cartListView);
        totalPriceTextView = findViewById(R.id.totalPriceTextView);
        cartItems = CartManager.getInstance().getCartItems();

        adapter = new CartAdapter(this, cartItems);
        cartListView.setAdapter(adapter);

        findViewById(R.id.btnBack).setOnClickListener(view -> {
            startActivity(new Intent(this, HomeActivity.class));
        });

        findViewById(R.id.btnBackToHome).setOnClickListener(view -> {
            startActivity(new Intent(this, HomeActivity.class));
        });

        findViewById(R.id.checkoutButton).setOnClickListener(view -> {
            Intent intent = new Intent(CartActivity.this, CheckoutActivity.class);
            startActivity(intent);
        });

        updateTotalPrice();
    }

    public void updateTotalPrice() {
        double total = 0.0; // Chuyển sang double để giữ phần thập phân
        for (Product product : cartItems) {
            if (product.isSelected()) {
                total += product.getPrice() * product.getQuantity();
            }
        }

        // Định dạng theo kiểu tiền tệ Mỹ, giữ 2 chữ số thập phân
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
        formatter.setMinimumFractionDigits(2); // bắt buộc có 2 số sau dấu .
        formatter.setMaximumFractionDigits(2); // không làm tròn quá mức

        String formattedTotal = formatter.format(total);
        totalPriceTextView.setText("Tổng: " + formattedTotal);
    }
}
