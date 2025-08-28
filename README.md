# BMI Calculator Android App

A modern, Material Design 3 Android application for calculating Body Mass Index (BMI) with a beautiful, responsive UI.

## Features

### 🎨 **Modern UI Design**
- Clean, modern interface following Material Design 3 guidelines
- Light blue gradient background with subtle visual elements
- Rounded cards and buttons for a polished look
- Responsive design that works on different screen sizes

### 📱 **Three-Screen Flow**
1. **Start Screen**: Welcome screen with app title and description
2. **Input Screen**: Data entry with gender selection, height picker, and weight/age inputs
3. **Result Screen**: BMI calculation display with category and personalized advice

### 🎯 **Key Features**
- **Gender Selection**: Interactive male/female cards with visual feedback
- **Height Picker**: Dual NumberPicker for meters and centimeters
- **Weight & Age Input**: Clean input fields with validation
- **BMI Calculation**: Accurate BMI calculation with category classification
- **Dynamic Results**: Color-coded BMI categories and personalized advice
- **Navigation**: Smooth transitions between screens with proper back navigation

### 🎨 **Visual Elements**
- **Color Scheme**: Blue primary colors with category-specific colors
  - Green: Normal BMI
  - Yellow/Orange: Overweight
  - Red: Obese
  - Blue: Underweight
- **Icons**: Custom vector drawables for male, female, height, and weight
- **Typography**: Consistent text styles with proper hierarchy

## Technical Implementation

### 📁 **File Structure**
```
app/src/main/
├── java/com/king/bmicalculatorfinal/
│   ├── MainActivity.java
│   ├── StartActivity.java
│   ├── InputActivity.java
│   └── ResultActivity.java
├── res/
│   ├── layout/
│   │   ├── activity_start.xml
│   │   ├── activity_input.xml
│   │   └── activity_result.xml
│   ├── drawable/
│   │   ├── background_gradient.xml
│   │   ├── button_background.xml
│   │   ├── circle_background.xml
│   │   ├── ic_male.xml
│   │   ├── ic_female.xml
│   │   ├── ic_height.xml
│   │   └── ic_weight.xml
│   ├── values/
│   │   ├── colors.xml
│   │   ├── strings.xml
│   │   └── styles.xml
│   └── mipmap/
└── AndroidManifest.xml
```

### 🛠 **Technologies Used**
- **Java**: All activity logic implemented in Java
- **XML Layouts**: ConstraintLayout for responsive design
- **Material Design 3**: Modern UI components and styling
- **CardView**: Rounded cards for content organization
- **NumberPicker**: Custom height selection interface
- **Vector Drawables**: Scalable icons for all UI elements

### 📊 **BMI Categories**
- **Underweight**: < 18.5 (Blue)
- **Normal**: 18.5 - 24.9 (Green)
- **Overweight**: 25.0 - 29.9 (Yellow/Orange)
- **Obese**: ≥ 30.0 (Red)

## How to Use

1. **Launch the app** - Opens to the welcome screen
2. **Tap "START"** - Navigate to the input screen
3. **Select gender** - Tap male or female card
4. **Set height** - Use the number pickers for meters and centimeters
5. **Enter weight** - Type your weight in kilograms
6. **Enter age** - Type your age
7. **Tap "CALCULATE"** - View your BMI result
8. **Review results** - See your BMI value, category, and personalized advice
9. **Tap "RETRY"** - Start over with new calculations

## Installation

1. Clone or download the project
2. Open in Android Studio
3. Sync Gradle files
4. Build and run on an Android device or emulator

## Requirements

- Android API Level 24+ (Android 7.0+)
- Android Studio Arctic Fox or later
- Gradle 7.0+

## Screenshots

The app features three main screens:
- **Start Screen**: Large "BMI Calculator" title with welcome message and START button
- **Input Screen**: Gender selection cards, height picker, weight/age inputs, and CALCULATE button
- **Result Screen**: Circular BMI display with category, summary, advice, and RETRY button

All screens maintain the consistent light blue gradient background and modern Material Design aesthetic.
