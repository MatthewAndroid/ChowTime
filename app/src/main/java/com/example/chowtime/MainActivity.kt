package com.example.chowtime

import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
    val mealType = remember { mutableStateOf("Hello!") }
    val mealImage = remember { mutableStateOf(R.drawable.defaultpic) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1E1E1E)), //changed background color because it was WAY too bright
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (showTimePicker.value) {
            TimeSelectorButton(
                onConfirm = { meal, imageResult ->
                    mealType.value = meal
                    mealImage.value = imageResult
                    showTimePicker.value = false
                },
                onDismiss = { showTimePicker.value = false }
            )
        } else {

            // image based on meal type for given time
            Image(
                painter = painterResource(id = mealImage.value),
                contentDescription = "Meal Image",
                modifier = Modifier
                    .size(200.dp)
                    .padding(16.dp),
                contentScale = ContentScale.Fit
            )

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
                text = "Choose a time to get a meal suggestion",
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
                    .height(50.dp).padding(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF7A36FF)
                ),
                shape = RoundedCornerShape(30.dp)
            ) {
                Text(text = "Select Time", color = Color.White, fontSize = 16.sp)
            }

            // reset button
            Button(
                onClick = { mealType.value = "Hello!"
                    mealImage.value = R.drawable.defaultpic },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(50.dp)
                    .padding(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF7A36FF)
                ),
                shape = RoundedCornerShape(30.dp)
            ) {
                Text(text = "Reset", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeSelectorButton(
    //takes string and int as parameters had to add in weird stuff to make it work in the curly brackets
    onConfirm: (String, Int) -> Unit = { _, _ -> },
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
            Button(onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF7A36FF)
                    )) {
                Text("Close", color = Color.White)
            }
            Button(onClick = {
                val selectedHour = timePickerState.hour
                val meal = getMealType(selectedHour)
                val imageRes = getImage(meal)
                onConfirm(meal, imageRes)
            },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF7A36FF)
                )
            ) {
                Text("Select", color = Color.White)
            }
        }
    }
}

//function to get image based on meal type probably a better way to do this but it works lol
fun getImage(meal: String): Int {
    val imageRes = if (meal == "Pancakes") {
        R.drawable.pancakes
    } else if (meal == "Omelette") {
        R.drawable.omelette
    } else if (meal == "Smoothie") {
        R.drawable.smoothie
    } else if (meal == "Fruit Salad") {
        R.drawable.fruitsalad
    } else if (meal == "Yogurt") {
        R.drawable.yogurt
    } else if (meal == "Chocolate") {
        R.drawable.chocolate
    } else if (meal == "Sandwich") {
        R.drawable.sandwich
    } else if (meal == "Salad") {
        R.drawable.salad
    } else if (meal == "Soup") {
        R.drawable.soup
    } else if (meal == "Cookies") {
        R.drawable.cookie
    } else if (meal == "Muffin") {
        R.drawable.muffin
    } else if (meal == "Tea") {
        R.drawable.tea
    } else if (meal == "Steak") {
        R.drawable.steak
    } else if (meal == "Pasta") {
        R.drawable.pasta
    } else if (meal == "Grilled Chicken") {
        R.drawable.chicken
    } else {
        R.drawable.cancel
    }

    return imageRes
}

fun getMealType(hour: Int): String {
    val morningMeals = arrayOf("Pancakes", "Omelette", "Smoothie")
    val midMorningMeals = arrayOf("Fruit Salad", "Yogurt", "Chocolate")
    val afternoonMeals = arrayOf("Sandwich", "Salad", "Soup")
    val midAfternoonMeals = arrayOf("Cookies", "Muffin", "Tea")
    val dinnerMeals = arrayOf("Steak", "Pasta", "Grilled Chicken")
    //arrays of foods to choose from based on time of day

    // Log the selected hour
    Log.d("MealSuggestion", "Selected hour: $hour")

    //returns meal based on time of day from timeselector button :)
    //also using range to check if hour is between certain times because it's easier to read and
    //less verbose than using && operator e.g instead of if(hour >= 5 && hour <= 9)

    if (hour in 5..9) {
        return morningMeals.random()
    } else if (hour in 10..11) {
        return midMorningMeals.random()
    } else if (hour in 12..14) {
        return afternoonMeals.random()
    } else if (hour in 15..17) {
        return midAfternoonMeals.random()
    } else if (hour in 18..21) {
        return dinnerMeals.random()
    } else {
        return "No meal"
    }

}