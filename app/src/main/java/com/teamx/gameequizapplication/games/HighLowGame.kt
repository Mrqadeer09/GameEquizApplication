package com.teamx.gameequizapplication.games

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.random.Random

class HighLowGame {
}

//High low
/*@Composable
fun HighOrLowGameScreen() {
    var score by remember { mutableStateOf(0) }
    var currentNumber by remember { mutableStateOf(generateNumber()) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "High or Low",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Box(
            modifier = Modifier
                .size(200.dp)
                .background(color = Color.LightGray)
                .pointerInput(Unit) {
                    detectDragGestures { _, dragAmount ->
                        if (dragAmount.y < 0) {
                            checkAnswer(true)
                        } else if (dragAmount.y > 0) {
                            checkAnswer(false)
                        }
                    }
                }
        ) {
            Text(
                text = currentNumber.toString(),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Text(
            text = "Score: $score",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

private fun generateNumber(): Int {
    return Random.nextInt(100)
}
var currentNumber = 0
private fun checkAnswer(higher: Boolean): Boolean {
    // Generate the next number
    val nextNumber = generateNumber()

    // Check if the player's choice is correct
    val correctAnswer = if (higher) nextNumber > currentNumber else nextNumber < currentNumber

    // Update the current number and score
    currentNumber = nextNumber
    return correctAnswer
}

@Preview
@Composable
fun PreviewHighOrLowGameScreen() {
    HighOrLowGameScreen()
}*/


@Composable
fun HighOrLowGameScreen(content: @Composable () -> Unit, onContinueClicked: () -> Unit) {
    var score by remember { mutableStateOf(0) }
    var currentNumber by remember { mutableStateOf(generateNumber()) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "High or Low",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = currentNumber.toString(),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            itemsIndexed(listOf("Higher", "Lower")) { _, option ->
                Button(
                    onClick = {
                        val nextNumber = generateNumber()
                        val correctAnswer =
                            if (option == "Higher") nextNumber > currentNumber else nextNumber < currentNumber

                        if (correctAnswer) {
                            score++
                        } else {
                            score = 0
                        }

                        currentNumber = nextNumber
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text(text = option)
                }
            }
        }

        Text(
            text = "Score: $score",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

private fun generateNumber(): Int {
    return Random.nextInt(100)
}

/*@Preview
@Composable
fun PreviewHighOrLowGameScreen() {
    HighOrLowGameScreen(){}
}*/
//High low