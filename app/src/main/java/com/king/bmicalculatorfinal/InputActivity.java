package com.king.bmicalculatorfinal;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
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
    private NumberPicker npFeet, npInches;
    private EditText etWeight, etAge;
    private Button btnCalculate;
    
    private String selectedGender = "Male"; // Default selection
    private int heightFeet = 6;
    private int heightInches = 1;

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
        npFeet = findViewById(R.id.npFeet);
        npInches = findViewById(R.id.npInches);
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
        // Setup feet picker (3-8 feet)
        npFeet.setMinValue(3);
        npFeet.setMaxValue(8);
        npFeet.setValue(6);
        npFeet.setWrapSelectorWheel(false);

        // Setup inches picker (0-11 inches)
        npInches.setMinValue(0);
        npInches.setMaxValue(11);
        npInches.setValue(1);
        npInches.setWrapSelectorWheel(false);

        // Set value change listeners
        npFeet.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                heightFeet = newVal;
            }
        });

        npInches.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                heightInches = newVal;
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

        // Convert height from feet and inches to meters
        float totalInches = (heightFeet * 12) + heightInches;
        float heightInMeters = totalInches * 0.0254f; // Convert inches to meters
        float heightInCm = totalInches * 2.54f; // Convert inches to centimeters

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_more) {
            // Handle more options menu
            Toast.makeText(this, "More options", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
