package com.king.bmicalculatorfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {

    private Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        // Initialize views
        btnStart = findViewById(R.id.btnStart);

        // Set click listener for start button
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to InputActivity
                Intent intent = new Intent(StartActivity.this, InputActivity.class);
                startActivity(intent);
            }
        });
    }
}
