package com.example.chowtime

import android.icu.util.Calendar
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    val showTimePicker = remember { mutableStateOf(false) }
    val mealType = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1E1E1E)), //changed background color because it was WAY too bright
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (showTimePicker.value) {
            TimeSelectorButton(
                onConfirm = { meal ->
                    mealType.value = meal
                    showTimePicker.value = false
                },
                onDismiss = { showTimePicker.value = false }
            )
        } else {

            Spacer(modifier = Modifier.height(24.dp))

            // meal name
            Text(
                text = mealType.value,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            // description
            Text(
                text = "prep ",
                fontSize = 14.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            // select time button
            Button(
                onClick = { showTimePicker.value = true },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF7A36FF)
                ),
                shape = RoundedCornerShape(30.dp)
            ) {
                Text(text = "Select Time", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeSelectorButton(
    onConfirm: (String) -> Unit = {},
    onDismiss: () -> Unit = {}
) {
    //time picker state must be set to initialise it so i set it to current time
    val currentTime = Calendar.getInstance()
    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true,
    )

    // column to hold timepicker and buttons
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        TimePicker(
            state = timePickerState,
        )
        // row to hold buttons and make them evenly spaced
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

    //returns meal based on time of day from timeselector button :)
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