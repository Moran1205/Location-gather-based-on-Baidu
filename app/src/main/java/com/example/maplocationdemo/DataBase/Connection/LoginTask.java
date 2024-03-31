package com.example.maplocationdemo.DataBase.Connection;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.Settings;
import android.widget.Toast;

import com.example.maplocationdemo.DataBase.User;
import com.example.maplocationdemo.LoginInActivity;
import com.example.maplocationdemo.MainActivity;

public class LoginTask extends AsyncTask<Void, Void, Boolean> {
    private User user;
    private Context context;

    @Override
    protected Boolean doInBackground(Void... voids) {
        return CloudBDConnection.select_user_info(user);
    }

    public LoginTask(User user, Context context) {
        this.user = user;
        this.context = context;
    }

    @Override
    protected void onPostExecute(Boolean isSuccessfulLogin) {
        // 获取设备信息
        String deviceId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        String deviceModel = Build.MODEL;
        String osVersion = Build.VERSION.RELEASE;

        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("deviceId", deviceId);
        editor.putString("deviceModel", deviceModel);
        editor.putString("osVersion", osVersion);
        editor.apply();

        System.out.println(deviceId);
        System.out.println(deviceModel);
        System.out.println(osVersion);

        // 根据查询结果进行相应操作
        if (isSuccessfulLogin) {
            // 登录成功，跳转到主Activity
            Intent intentLoginIn = new Intent(context, MainActivity.class);
            context.startActivity(intentLoginIn);
        } else {
            // 登录失败，显示错误信息
            Toast.makeText(context, "账号密码错误！请重新输入", Toast.LENGTH_SHORT).show();
        }
    }


}
