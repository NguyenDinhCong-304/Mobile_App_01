package com.example.nguyendinhcong_2122110566;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class CheckoutActivity extends AppCompatActivity {
    private ListView checkoutListView;
    private TextView totalTextView;
    private EditText addressEditText;
    private Button btnPlaceOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        checkoutListView = findViewById(R.id.checkoutListView);
        totalTextView = findViewById(R.id.totalTextView);
        addressEditText = findViewById(R.id.addressEditText);
        btnPlaceOrder = findViewById(R.id.btnPlaceOrder);

        List<Product> items = CartManager.getInstance().getCartItems();
        ProductAdapter adapter = new ProductAdapter(this, items);
        checkoutListView.setAdapter(adapter);

        int total = calculateTotal(items);
        totalTextView.setText("Tổng tiền: " + String.format("%,d", total) + " VND");

        btnPlaceOrder.setOnClickListener(v -> {
            String address = addressEditText.getText().toString().trim();
            if (address.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập địa chỉ", Toast.LENGTH_SHORT).show();
                return;
            }
            CartManager.getInstance().clearCart();
            Toast.makeText(this, "Đặt hàng thành công!", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        });

        findViewById(R.id.btnBack).setOnClickListener(v -> onBackPressed());
    }

    private int calculateTotal(List<Product> items) {
        int total = 0;
        for (Product p : items) {
            int price = Integer.parseInt(p.getPrice().replace(".", "").replace(" VND", ""));
            total += price * p.getQuantity();
        }
        return total;
    }
}