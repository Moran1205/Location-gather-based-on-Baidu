package com.example.maplocationdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.QuickContactBadge;

import androidx.annotation.Nullable;

public class LoginInActivity extends Activity {
    private EditText login_username;
    private EditText login_password;
    private Button login_button_sign_in;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        login_username = findViewById(R.id.login_username);
        login_password = findViewById(R.id.login_password);
        login_button_sign_in = findViewById(R.id.login_button_sign_in);

        login_button_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(login_username.getText().toString(),login_password.getText().toString());
            }
        });
    }

    private void validate(String username,String password){

    }
}
