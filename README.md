# 🍴ChowTime - Meal Suggestion App🍴

## Overview
ChowTime is an Android app that suggests meals based on the selected time of the day. It provides meal recommendations along with corresponding images, enhancing the user experience with an interactive and visually appealing UI.

## Youtube Link
here

## Features
- **Meal Suggestions**: Get meal recommendations based on the time selected.
- **Time Picker**: Users can select a time to receive an appropriate meal suggestion.
- **Dynamic UI**: Displays an image and name of the suggested meal.
- **Reset Option**: Easily reset to default settings.

## Technologies Used
- **Jetpack Compose**: For building the UI.
- **Material3 Components**: For styling and UI elements.
- **Kotlin**: Programming language.
- **Android SDK**: For app development.

## Installation
1. Clone this repository:
   ```sh
   git clone https://github.com/your-repo/chowtime.git
   ```
2. Open the project in **Android Studio**.
3. Build and run the project on an **emulator** or **physical device**.

## Usage
1. Open the app.
2. Click on **"Select Time"** to choose a time.
3. A meal suggestion appears based on the selected time.
4. Click **"Reset"** to reset the meal suggestion.

## Code Structure
- **MainActivity.kt**: Entry point of the application.
- **MealSuggestionScreen.kt**: UI for displaying meal suggestions.
- **TimeSelectorButton.kt**: Handles time selection.
- **getMealType()**: Determines a meal based on the selected time.
- **getImage()**: Retrieves an image associated with the suggested meal.

## Meal Selection Logic
| Time Range | Meal Suggestions |
|------------|-----------------|
| 5 AM - 9 AM | Pancakes, Omelette, Smoothie |
| 10 AM - 11 AM | Fruit Salad, Yogurt, Chocolate |
| 12 PM - 2 PM | Sandwich, Salad, Soup |
| 3 PM - 5 PM | Cookies, Muffin, Tea |
| 6 PM - 9 PM | Steak, Pasta, Grilled Chicken |
| Other Times | "No meal" |


## Screenshots

### Home Page
<img src="https://github.com/user-attachments/assets/97c16727-cdaa-43d1-ac68-bc84f8213fc1" width="300">

### TimePicker Screen
<img src="https://github.com/user-attachments/assets/9a3df44e-eaba-42ea-b4fc-8b927009c0d8" width="300">

### Home Screen after time picked
<img src="https://github.com/user-attachments/assets/95cfdda1-e020-4add-8684-7c2598bd3bab" width="300">






