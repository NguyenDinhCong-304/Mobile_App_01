package com.example.nguyendinhcong_2122110566;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UserActivity extends AppCompatActivity {
    TextView textUserEmail, textUserName;
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        textUserEmail = findViewById(R.id.textUserEmail);
        textUserName = findViewById(R.id.textUserName);
        btnLogout = findViewById(R.id.btnLogout);

//        // Lấy email từ SharedPreferences
//        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
//        String email = prefs.getString("email", "Không có email");
//        String name = email.split("@")[0];  // Giả định tên là phần trước @
//
//        textUserEmail.setText("Email: " + email);
//        textUserName.setText("Tên người dùng: " + name);
        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        String username = prefs.getString("username", "Không có username");
        String email = prefs.getString("email", "Không có email");

        textUserName.setText("Tên người dùng: " + username);
        textUserEmail.setText("Email: " + email);


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xoá SharedPreferences nếu cần
                SharedPreferences.Editor editor = prefs.edit();
                editor.clear();
                //editor.remove("isLoggedIn");
                editor.apply();

                // Trở về màn hình đăng nhập
                Intent intent = new Intent(UserActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //quay lại trang chủ
        findViewById(R.id.btnBack).setOnClickListener(view -> {
            startActivity(new Intent(this, HomeActivity.class));
        });
    }
}