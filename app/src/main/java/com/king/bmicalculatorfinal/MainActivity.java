package com.king.bmicalculatorfinal;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Launch StartActivity immediately
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
        finish(); // Close MainActivity
    }
}