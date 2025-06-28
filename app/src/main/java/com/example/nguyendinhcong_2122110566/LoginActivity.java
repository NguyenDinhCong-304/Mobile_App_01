package com.example.nguyendinhcong_2122110566;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button btnLogin = findViewById(R.id.btnLoginHome);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText objEmail = findViewById(R.id.editTextTextEmailAddress);
                EditText objPassWord = findViewById(R.id.editTextTextPassword);

                String email = objEmail.getText().toString().trim();
                String pass = objPassWord.getText().toString().trim();

                // Lấy dữ liệu đã lưu từ SharedPreferences
                SharedPreferences preferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                String savedEmail = preferences.getString("email", "");
                String savedPassword = preferences.getString("password", "");

                if(email.equals(savedEmail) && pass.equals(savedPassword)){
                    Intent it = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(it);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Thông tin đăng nhập không đúng", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Button btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(it);
            }
        });
    }
}