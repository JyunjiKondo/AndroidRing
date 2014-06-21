package com.example.androidring;

import android.os.Bundle;
import android.view.View;

public class ReceiveRole1Activity extends ReceiveActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sendAngryNotification();
    }

    private void sendAngryNotification() {
        sendNotification(true);
    }

    private void sendForgiveNotification() {
        sendNotification(false);
    }

    public void onClick(View view) {
        sendForgiveNotification();
    }
}
