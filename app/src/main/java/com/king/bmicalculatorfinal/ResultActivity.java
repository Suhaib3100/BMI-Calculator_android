package com.king.bmicalculatorfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ResultActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView tvBMIValue, tvBMICategory, tvSummary, tvAdvice;
    private Button btnRetry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Initialize views
        initializeViews();
        setupToolbar();
        displayResults();
        setupRetryButton();
    }

    private void initializeViews() {
        toolbar = findViewById(R.id.toolbar);
        tvBMIValue = findViewById(R.id.tvBMIValue);
        tvBMICategory = findViewById(R.id.tvBMICategory);
        tvSummary = findViewById(R.id.tvSummary);
        tvAdvice = findViewById(R.id.tvAdvice);
        btnRetry = findViewById(R.id.btnRetry);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    private void displayResults() {
        Intent intent = getIntent();
        float bmi = intent.getFloatExtra("bmi", 0);
        float weight = intent.getFloatExtra("weight", 0);
        float heightCm = intent.getFloatExtra("height_cm", 0);
        int age = intent.getIntExtra("age", 0);
        String gender = intent.getStringExtra("gender");

        // Display BMI value
        tvBMIValue.setText(String.format("%.1f", bmi));

        // Determine BMI category and set colors
        BMICategory bmiCategory = getBMICategory(bmi);
        setBMICategoryDisplay(bmiCategory);

        // Display summary
        String summary = String.format("Height: %.0f cm | Weight: %.0f kg", heightCm, weight);
        tvSummary.setText(summary);

        // Display advice
        String advice = getAdviceForCategory(bmiCategory);
        tvAdvice.setText(advice);
    }

    private BMICategory getBMICategory(float bmi) {
        if (bmi < 18.5) {
            return BMICategory.UNDERWEIGHT;
        } else if (bmi >= 18.5 && bmi < 25) {
            return BMICategory.NORMAL;
        } else if (bmi >= 25 && bmi < 30) {
            return BMICategory.OVERWEIGHT;
        } else {
            return BMICategory.OBESE;
        }
    }

    private void setBMICategoryDisplay(BMICategory category) {
        switch (category) {
            case UNDERWEIGHT:
                tvBMICategory.setText(R.string.bmi_underweight_text);
                tvBMICategory.setTextColor(getResources().getColor(R.color.bmi_underweight));
                break;
            case NORMAL:
                tvBMICategory.setText(R.string.bmi_normal_text);
                tvBMICategory.setTextColor(getResources().getColor(R.color.bmi_normal));
                break;
            case OVERWEIGHT:
                tvBMICategory.setText(R.string.bmi_overweight_text);
                tvBMICategory.setTextColor(getResources().getColor(R.color.bmi_overweight));
                break;
            case OBESE:
                tvBMICategory.setText(R.string.bmi_obese_text);
                tvBMICategory.setTextColor(getResources().getColor(R.color.bmi_obese));
                break;
        }
    }

    private String getAdviceForCategory(BMICategory category) {
        switch (category) {
            case UNDERWEIGHT:
                return getString(R.string.bmi_underweight_advice);
            case NORMAL:
                return getString(R.string.bmi_normal_advice);
            case OVERWEIGHT:
                return getString(R.string.bmi_overweight_advice);
            case OBESE:
                return getString(R.string.bmi_obese_advice);
            default:
                return getString(R.string.bmi_normal_advice);
        }
    }

    private void setupRetryButton() {
        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to InputActivity
                Intent intent = new Intent(ResultActivity.this, InputActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    // BMI Category enum
    private enum BMICategory {
        UNDERWEIGHT,
        NORMAL,
        OVERWEIGHT,
        OBESE
    }
}
