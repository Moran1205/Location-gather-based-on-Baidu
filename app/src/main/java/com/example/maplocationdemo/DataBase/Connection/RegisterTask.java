package com.example.maplocationdemo.DataBase.Connection;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.maplocationdemo.DataBase.User;
import com.example.maplocationdemo.LoginInActivity;
import com.example.maplocationdemo.RegisterActivity;

public class RegisterTask extends AsyncTask<Void, Void, Void> {
    private User user;
    private Context context;

    public RegisterTask(User user, Context context) {
        this.user = user;
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        CloudBDConnection.insert_user_info(user);
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        Toast.makeText(context, "注册成功！", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context, LoginInActivity.class);
        context.startActivity(intent);
        super.onPostExecute(unused);
    }
}
