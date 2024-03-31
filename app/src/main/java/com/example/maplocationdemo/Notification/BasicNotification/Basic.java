package com.example.maplocationdemo.Notification.BasicNotification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.example.maplocationdemo.LoginInActivity;
import com.example.maplocationdemo.MainActivity;
import com.example.maplocationdemo.Notification.Channel.create;
import com.example.maplocationdemo.R;

import java.util.Random;

public class Basic {

        private static final String CHANNEL_ID = "channel_id";
        private static final String CHANNEL_NAME = "Channel Name";
        private static final String CHANNEL_DESCRIPTION = "Channel Description";

        public void showBasicNotification(Context context) {
            create create = new create();
            create.createNotificationChannel(context);

//            // 创建通知渠道
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                NotificationChannel channel =
//                        new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
//                channel.setDescription(CHANNEL_DESCRIPTION);
//
//                // 在系统中注册通道
//                NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
//                notificationManager.createNotificationChannel(channel);
//            }

            // 设置通知的点按操作
            Intent intent = new Intent(context, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_IMMUTABLE);

            // 构建通知内容
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                    .setSmallIcon(R.drawable.notification)  // 通知图标
                    .setContentTitle("Notification Title")  // 通知标题
                    .setContentText("This is a basic notification") // 通知内容
                    .setContentIntent(pendingIntent)    // 点按操作
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);  // 重要等级

            // 定义一个随机的 notificationId
            int notificationId = new Random().nextInt(1000);
            // 显示通知
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            notificationManager.notify(notificationId, builder.build());

        }
}
