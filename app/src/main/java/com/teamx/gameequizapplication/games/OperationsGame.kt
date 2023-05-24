package com.teamx.gameequizapplication.games

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.random.Random

class OperationsGame {
}
//operations
val operators = listOf("+", "-", "*", "/")

@Composable
fun GameScreen() {
    var equation by remember { mutableStateOf(generateEquation()) }
    var selectedOperator by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = equation,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        operators.shuffled().forEach { operator ->
            Button(
                onClick = { selectedOperator = operator },
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Text(text = operator)
            }
        }

        Text(
            text = if (selectedOperator == equation.split(" ")[1]) "Correct!" else "Wrong! The answer is ${
                evaluateEquation(
                    equation
                )
            }",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(top = 16.dp)
        )

        Button(
            onClick = { equation = generateEquation() },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Next")
        }
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

@Preview
@Composable
fun PreviewGameScreen() {
    GameScreen()
}

//operations