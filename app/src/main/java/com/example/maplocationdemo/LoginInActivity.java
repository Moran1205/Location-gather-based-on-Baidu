package com.example.maplocationdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.QuickContactBadge;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.maplocationdemo.DataBase.Connection.CloudBDConnection;

import java.sql.SQLException;

public class LoginInActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button bt_sign_in;
    private Button bt_sign_up;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pre_activity_login);

        username = findViewById(R.id.login_username);
        password = findViewById(R.id.login_password);
        bt_sign_in = findViewById(R.id.login_button_sign_in);
        bt_sign_up = findViewById(R.id.login_button_sign_up);

        bt_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    boolean isSuccessfulLogin = CloudBDConnection.select_user_info(
                            username.getText().toString(), password.getText().toString());
                    if(isSuccessfulLogin){
                        Intent intentLoginIn = new Intent(LoginInActivity.this, MainActivity.class);
                        startActivity(intentLoginIn);
                    }else{
                        Toast.makeText(LoginInActivity.this, "账号密码错误！请重新输入", Toast.LENGTH_SHORT).show();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        bt_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRegister = new Intent(LoginInActivity.this,RegisterActivity.class);
                startActivity(intentRegister);
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
