package com.example.nguyendinhcong_2122110566;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

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

//        findViewById(R.id.checkoutButton).setOnClickListener(view -> {
//            CartManager.getInstance().clearCart();
//            Toast.makeText(this, "Đặt hàng thành công!", Toast.LENGTH_SHORT).show();
//            finish();
//        });
        findViewById(R.id.checkoutButton).setOnClickListener(view -> {
            Intent intent = new Intent(CartActivity.this, CheckoutActivity.class);
            startActivity(intent);
        });

        updateTotalPrice();
    }

    public void updateTotalPrice() {
        int total = 0;
        for (Product product : cartItems) {
            String priceStr = product.getPrice().replace(".", "").replace(" VND", "");
            try {
                total += Integer.parseInt(priceStr) * product.getQuantity();
            } catch (NumberFormatException ignored) {}
        }
        totalPriceTextView.setText("Tổng: " + String.format("%,d", total) + " VND");
    }
}
