package com.example.androidring;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preview.support.v4.app.NotificationManagerCompat;
import android.preview.support.wearable.notifications.*;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
    enum Role {
        ROLE1, ROLE2;
    }

    private Role role = Role.ROLE1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getIntent().getBooleanExtra("isRole2", false)) {
            role = Role.ROLE2;
        }
        if (role == Role.ROLE1) {
            sendQueryAngryNotification();
        } else {
            sendGetYelledNotification();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.action_role1:
                role = Role.ROLE1;
                return true;
            case R.id.action_role2:
                Intent intent = new Intent();
                intent.setClassName("com.example.androidring", "com.example.androidring.MainActivity");
                intent.putExtra("isRole2", true);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void sendQueryAngryNotification() {
        sendNotification(true);
    }

    private void sendGetYelledNotification() {
        sendNotification(false);
    }

    private void sendNotification(Boolean isQueryAngry) {
        // NotificationをStackするときなどに使用するID
        int notificationId = 1;
        // Notificationに表示するタイトルと文字
        String eventTitle = isQueryAngry ? "怒ってる？" : "お怒りです";
        String eventText = isQueryAngry ? "怒ってたらOpenしてね" : "早く謝ったら？";
        // タップされた時にActivityに渡すIntentをPendingIntentとして作成
        Intent viewIntent = new Intent(this, isQueryAngry ? ReceiveRole1Activity.class : ReceiveRole2Activity.class); // isQueryAngry == Role1とみなす
        PendingIntent viewPendingIntent = PendingIntent.getActivity(this, 0, viewIntent, 0);
        // NotificationCompat.Builderを使ってNotificationを作る
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_stat_name)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), isQueryAngry ? R.drawable.ring_blue : R.drawable.ring_red))
                        .setContentTitle(eventTitle)
                        .setContentText(eventText)
                        .setContentIntent(viewPendingIntent);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(notificationId, notificationBuilder.build());
    }
}
