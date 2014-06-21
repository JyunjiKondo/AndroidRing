package com.example.androidring;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ReceiveRole2Activity extends ReceiveActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView message = (TextView)findViewById(R.id.message);
        message.setText(R.string.sorry_message);
        findViewById(R.id.button).setVisibility(View.GONE);
    }
}
