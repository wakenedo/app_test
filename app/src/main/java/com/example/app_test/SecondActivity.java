package com.example.app_test;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        String message = getIntent().getStringExtra("greeting_message");
        TextView greetingTextView = findViewById(R.id.textView);
        if(greetingTextView != null) {
            greetingTextView.setText(message != null ? message : "No message received");
        }
    }
}
