package com.example.app_test;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceSaved) {
        super.onCreate(savedInstanceSaved);
        setContentView(R.layout.activity_third);
        double num1 = getIntent().getDoubleExtra("num1", 0.0);
        double num2 = getIntent().getDoubleExtra("num2", 0.0);
        String operator = getIntent().getStringExtra("operator");

        double result = 0.0;
        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 != 0.0) {
                    result = num1 / num2;
                } else {
                    result = Double.NaN; // Handle division by zero
                }
                break;
        }

        // Display the result in a TextView
        TextView resultTextView = findViewById(R.id.resultTextView);
        resultTextView.setText("Result: " + result);
    }
}
