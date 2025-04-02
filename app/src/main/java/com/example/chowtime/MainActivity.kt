package com.example.chowtime

import android.icu.util.Calendar
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeSelectorButton(
    onConfirm: (String) -> Unit = {},
    onDismiss: () -> Unit = {}
) {
    val currentTime = Calendar.getInstance()
    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true,
    )

    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        TimePicker(
            state = timePickerState,
        )
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = onDismiss) {
                Text("Close")
            }
            Button(onClick = {
                val selectedHour = timePickerState.hour
                val meal = getMealType(selectedHour)
                onConfirm(meal)
            }) {
                Text("Select")
            }
        }
    }
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