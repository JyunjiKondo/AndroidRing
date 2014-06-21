package com.example.androidring;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preview.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.androidring.R;

public class ReceiveActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);
    }

    protected void sendNotification(Boolean isAngry) {
        // NotificationをStackするときなどに使用するID
        int notificationId = 1;
        // Notificationに表示するタイトルと文字
        String eventTitle = isAngry ? "怒ってる!" : "ごめんね!";
        String eventText = isAngry ? "謝るまで許さない!" : "ケーキ買って帰る";
        // タップされた時にActivityに渡すIntentをPendingIntentとして作成
        Intent viewIntent = new Intent(this, ReceiveActivity.class);
        PendingIntent viewPendingIntent = PendingIntent.getActivity(this, 0, viewIntent, 0);
        // NotificationCompat.Builderを使ってNotificationを作る
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_stat_name)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), isAngry ? R.drawable.ring_red : R.drawable.ring_blue))
                        .setContentTitle(eventTitle)
                        .setContentText(eventText)
                        .setContentIntent(viewPendingIntent);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(notificationId, notificationBuilder.build());
    }
}
