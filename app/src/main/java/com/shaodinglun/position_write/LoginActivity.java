//LoginActivity
package com.shaodinglun.position_write;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(v -> {
            // 获取用户输入的用户名和密码
            String username = ((EditText) findViewById(R.id.username)).getText().toString();
            String password = ((EditText) findViewById(R.id.password)).getText().toString();

            // 验证用户名和密码
            if (validateLogin(username, password)) {
                // 验证通过，跳转到主界面
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            } else {
                // 验证失败，提示用户
                Toast.makeText(LoginActivity.this, R.string.invalid_login_toast, Toast.LENGTH_SHORT).show();
            }
        });
    }

    // 简单的用户名和密码验证逻辑
    private boolean validateLogin(String username, String password) {
        return "user".equals(username) && "password".equals(password);
    }
}


