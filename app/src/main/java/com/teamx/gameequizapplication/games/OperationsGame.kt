package com.teamx.gameequizapplication.games

import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.random.Random

class OperationsGame {}

//operations
val operators = listOf("+", "-", "*", "/")

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GameScreen() {
    var equation by remember { mutableStateOf(generateEquation()) }
    var selectedOperator by remember { mutableStateOf("") }
    var allCounter by remember { mutableStateOf(0) }
    var accurateCounter by remember { mutableStateOf(0) }

    var isShaking by remember { mutableStateOf(false) }
    val offset = remember { Animatable(0f) }

    var selectedButtonIndex by remember { mutableStateOf(-1) }
    val context = LocalContext.current
    val vibrator = context.getSystemService(Vibrator::class.java)

    LaunchedEffect(isShaking) {
        if (isShaking) {

            vibrator?.let { v ->
                v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))
                // Delay for the vibration duration
                kotlinx.coroutines.delay(500)
                // Stop the vibration
                isShaking = false
                v.cancel()
            }
        } else {
            offset.snapTo(0f)
            offset.stop()
        }
    }




    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = equation.replaceFirst("*", "[ ]").replaceFirst("+", "[ ]")
                .replaceFirst("-", "[ ]").replaceFirst("/", "[ ]"),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )


        operators.shuffled().forEachIndexed { index, operator ->
            Button(
                modifier = Modifier
                    .padding(4.dp)
                    .offset { IntOffset(offset.value.dp.roundToPx(), 0) }
                    .size(83.dp),
                onClick = {
                    selectedButtonIndex = index
                    allCounter++

                    selectedOperator = operator
                    if (selectedOperator == equation.split(" ")[1]) {

                        equation = generateEquation()
                        accurateCounter++
                    } else {
                        if (index == selectedButtonIndex) {
                            isShaking = true
                        }
                    }


                },
                shape = RoundedCornerShape(5.dp),
                elevation = ButtonDefaults.buttonElevation(12.dp)
            ) {
                Text(text = operator)
            }
        }


        Text(
            text = "$accurateCounter $allCounter",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

fun generateEquation(): String {
    val number1 = Random.nextInt(1, 10)
    val number2 = Random.nextInt(1, 10)
    val operator = operators.random()
    val equation = "$number1 $operator $number2"
//    val result = evaluateEquation(number1, number2, operator)
    val result = evaluateEquation(equation)
    return "$number1 $operator $number2 = $result"
}

fun evaluateEquation(equation: String): Int {
    val parts = equation.split(" ")
    val number1 = parts[0].toInt()
    val number2 = parts[2].toInt()
    val operator = parts[1]
    return when (operator) {
        "+" -> number1 + number2
        "-" -> number1 - number2
        "*" -> number1 * number2
        "/" -> number1 / number2
        else -> throw IllegalArgumentException("Invalid operator: $operator")
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun PreviewGameScreen() {
    GameScreen()
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun VibrateComposable() {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val vibrator = context.getSystemService(Vibrator::class.java)

    Box(modifier = Modifier.fillMaxSize()) {
        // Trigger the vibration effect when the Composable is recomposed
        LaunchedEffect(true) {
            vibrator?.let { v ->
                v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))
                // Delay for the vibration duration
                kotlinx.coroutines.delay(500)
                // Stop the vibration

                v.cancel()
            }

        }
    }
}
//operations