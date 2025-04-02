package com.example.chowtime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.chowtime.ui.theme.ChowTimeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChowTimeTheme {
                MealSuggestionScreen()
            }
        }
    }
}

@Preview
@Composable
fun MealSuggestionScreen() {

}



fun getMealType(hour: Int): String {
    val morningMeals = arrayOf("Pancakes", "Omelette", "Smoothie")
    val midMorningMeals = arrayOf("Fruit Salad", "Yogurt", "Granola Bar")
    val afternoonMeals = arrayOf("Sandwich", "Salad", "Soup")
    val midAfternoonMeals = arrayOf("Cookies", "Muffin", "Tea")
    val dinnerMeals = arrayOf("Steak", "Pasta", "Grilled Chicken")

    if(hour < 9 && hour > 5){
        return morningMeals.random()
    } else if(hour < 11 && hour > 9){
        return midMorningMeals.random()
    } else if(hour < 14 && hour > 12){
        return afternoonMeals.random()
    } else if(hour < 17 && hour > 15){
        return midAfternoonMeals.random()
    } else if(hour < 21 && hour > 18){
        return dinnerMeals.random()
    } else {
        return "No meal"

    }
}