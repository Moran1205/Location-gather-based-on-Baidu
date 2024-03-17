package com.example.maplocationdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.maplocationdemo.DataBase.Connection.CloudBDConnection;
import com.example.maplocationdemo.DataBase.User;

import java.sql.SQLException;

public class RegisterActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private EditText rePassword;
    private Button bt_register;



    @Override
    protected void onCreate(@Nullable @androidx.annotation.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        username = findViewById(R.id.register_username);
        password = findViewById(R.id.register_password);
        rePassword = findViewById(R.id.register_rePassword);
        bt_register = findViewById(R.id.register_button_sign_up);


        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    CloudBDConnection.insert_user_info(username.getText().toString(),password.getText().toString());
                    Toast.makeText(RegisterActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this,LoginInActivity.class);
                    startActivity(intent);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
