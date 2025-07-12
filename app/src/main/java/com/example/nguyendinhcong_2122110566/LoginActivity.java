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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        Button btnLogin = findViewById(R.id.btnLoginHome);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText objUserName = findViewById(R.id.editTextUserName);
                EditText objPassWord = findViewById(R.id.editTextTextPassword);

                String email = objUserName.getText().toString().trim();
                String pass = objPassWord.getText().toString().trim();

                loginWithAPI(email, pass); // Gọi hàm đăng nhập
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
    private void loginWithAPI(String username, String password) {
        String url = "https://fakestoreapi.com/auth/login";

        RequestQueue queue = Volley.newRequestQueue(this);

        JSONObject loginData = new JSONObject();
        try {
            loginData.put("username", username);
            loginData.put("password", password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                url,
                loginData,
                response -> {
                    try {
                        String token = response.getString("token");

                        // Lưu token vào SharedPreferences (tuỳ chọn)
                        SharedPreferences preferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                        preferences.edit().putString("token", token).apply();

                        Toast.makeText(getApplicationContext(), "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();

                        fetchUserInfo(username);

                        // Chuyển đến HomeActivity
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Lỗi khi xử lý phản hồi", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    Toast.makeText(getApplicationContext(), "Đăng nhập thất bại: " + error.toString(), Toast.LENGTH_LONG).show();
                }
        );

        queue.add(request);
    }
    private void fetchUserInfo(String username) {
        String url = "https://fakestoreapi.com/users";

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject userObj = response.getJSONObject(i);
                            String uname = userObj.getString("username");

                            if (uname.equals(username)) {
                                String email = userObj.getString("email");

                                // Lưu username và email
                                SharedPreferences preferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                                preferences.edit()
                                        .putString("username", uname)
                                        .putString("email", email)
                                        .putBoolean("isLoggedIn", true)
                                        .apply();

                                // Sau khi có thông tin thì chuyển màn hình
                                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                                finish();
                                return;
                            }
                        }

                        Toast.makeText(this, "Không tìm thấy người dùng", Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                },
                error -> Toast.makeText(this, "Lỗi khi lấy thông tin người dùng", Toast.LENGTH_SHORT).show()
        );

        queue.add(request);
    }

}