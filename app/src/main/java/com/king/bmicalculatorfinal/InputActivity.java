package com.king.bmicalculatorfinal;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.card.MaterialCardView;

public class InputActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private MaterialCardView cardMale, cardFemale;
    private NumberPicker npMeters, npCentimeters;
    private EditText etWeight, etAge;
    private Button btnCalculate;
    
    private String selectedGender = "Male"; // Default selection
    private int heightMeters = 1;
    private int heightCentimeters = 61;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        // Initialize views
        initializeViews();
        setupToolbar();
        setupGenderSelection();
        setupHeightPickers();
        setupCalculateButton();
    }

    private void initializeViews() {
        toolbar = findViewById(R.id.toolbar);
        cardMale = findViewById(R.id.cardMale);
        cardFemale = findViewById(R.id.cardFemale);
        npMeters = findViewById(R.id.npMeters);
        npCentimeters = findViewById(R.id.npCentimeters);
        etWeight = findViewById(R.id.etWeight);
        etAge = findViewById(R.id.etAge);
        btnCalculate = findViewById(R.id.btnCalculate);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    private void setupGenderSelection() {
        // Set initial selection (Male)
        updateGenderSelection(true);

        cardMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedGender = "Male";
                updateGenderSelection(true);
            }
        });

        cardFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedGender = "Female";
                updateGenderSelection(false);
            }
        });
    }

    private void updateGenderSelection(boolean isMaleSelected) {
        if (isMaleSelected) {
            cardMale.setCardBackgroundColor(getResources().getColor(R.color.selected_background));
            cardMale.setStrokeColor(getResources().getColor(R.color.selected_border));
            cardMale.setStrokeWidth(8);
            cardFemale.setCardBackgroundColor(getResources().getColor(R.color.card_background));
            cardFemale.setStrokeWidth(0);
        } else {
            cardFemale.setCardBackgroundColor(getResources().getColor(R.color.selected_background));
            cardFemale.setStrokeColor(getResources().getColor(R.color.selected_border));
            cardFemale.setStrokeWidth(8);
            cardMale.setCardBackgroundColor(getResources().getColor(R.color.card_background));
            cardMale.setStrokeWidth(0);
        }
    }

    private void setupHeightPickers() {
        // Setup meters picker (0-2)
        npMeters.setMinValue(0);
        npMeters.setMaxValue(2);
        npMeters.setValue(1);
        npMeters.setWrapSelectorWheel(false);

        // Setup centimeters picker (0-99)
        npCentimeters.setMinValue(0);
        npCentimeters.setMaxValue(99);
        npCentimeters.setValue(61);
        npCentimeters.setWrapSelectorWheel(false);

        // Set value change listeners
        npMeters.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                heightMeters = newVal;
            }
        });

        npCentimeters.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                heightCentimeters = newVal;
            }
        });
    }

    private void setupCalculateButton() {
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInputs()) {
                    calculateBMI();
                }
            }
        });
    }

    private boolean validateInputs() {
        String weightStr = etWeight.getText().toString().trim();
        String ageStr = etAge.getText().toString().trim();

        if (TextUtils.isEmpty(weightStr)) {
            Toast.makeText(this, "Please enter weight", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(ageStr)) {
            Toast.makeText(this, "Please enter age", Toast.LENGTH_SHORT).show();
            return false;
        }

        float weight = Float.parseFloat(weightStr);
        int age = Integer.parseInt(ageStr);

        if (weight <= 0 || weight > 500) {
            Toast.makeText(this, "Please enter a valid weight (1-500 kg)", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (age <= 0 || age > 120) {
            Toast.makeText(this, "Please enter a valid age (1-120)", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void calculateBMI() {
        String weightStr = etWeight.getText().toString().trim();
        String ageStr = etAge.getText().toString().trim();

        float weight = Float.parseFloat(weightStr);
        int age = Integer.parseInt(ageStr);

        // Convert height to meters
        float heightInMeters = heightMeters + (heightCentimeters / 100.0f);
        float heightInCm = heightMeters * 100 + heightCentimeters;

        // Calculate BMI: weight (kg) / height (m)²
        float bmi = weight / (heightInMeters * heightInMeters);

        // Navigate to ResultActivity with data
        Intent intent = new Intent(InputActivity.this, ResultActivity.class);
        intent.putExtra("bmi", bmi);
        intent.putExtra("weight", weight);
        intent.putExtra("height_cm", heightInCm);
        intent.putExtra("age", age);
        intent.putExtra("gender", selectedGender);
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
