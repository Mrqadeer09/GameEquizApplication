package com.teamx.gameequizapplication.games

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class CardCalculationGame {
}

//calculations

/*enum class CardColor {
    GREEN, RED
}

@Composable
fun CardCalculationGameScreen() {
    val cards = listOf(
        Card(7, CardColor.GREEN),
        Card(2, CardColor.RED)
    )
    val selectedCards = remember { mutableStateListOf<Int>() }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Card Calculation",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            cards.forEach { card ->
                Button(
                    onClick = {
                        if (card.color == CardColor.GREEN) {
                            selectedCards.add(card.value)
                        } else if (card.color == CardColor.RED) {
                            selectedCards.add(-card.value)
                        }
                    },
                    modifier = Modifier.padding(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (card.color == CardColor.GREEN) Color.Green else Color.Red
                    ),
                    border = BorderStroke(1.dp, Color.Black)
                ) {
                    Text(text = card.value.toString())
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Selected Cards: ${selectedCards.joinToString(", ")}",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { selectedCards.clear() },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Clear")
        }
    }
}

data class Card(val value: Int, val color: CardColor)

@Preview
@Composable
fun PreviewCardCalculationGameScreen() {
    CardCalculationGameScreen()
}*/

enum class CardColor {
    GREEN, RED
}

@Composable
fun CardCalculationGameScreen() {
    val cards = listOf(
        Card(7, CardColor.GREEN),
        Card(2, CardColor.RED)
    )
    val selectedCards = remember { mutableStateListOf<Int>() }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Card Calculation",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            cards.forEach { card ->
                Button(
                    onClick = {
                        if (card.color == CardColor.GREEN) {
                            selectedCards.add(card.value)
                        } else if (card.color == CardColor.RED) {
                            selectedCards.add(-card.value)
                        }
                    },
                    modifier = Modifier.padding(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (card.color == CardColor.GREEN) Color.Green else Color.Red
                    ),
                    border = BorderStroke(1.dp, Color.Black)
                ) {
                    Text(text = card.value.toString())
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Selected Cards: ${selectedCards.joinToString(", ")}",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        val answer = selectedCards.sum()
        Text(
            text = "Choose the correct answer:",
            style = MaterialTheme.typography.bodyLarge
        )

        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { checkAnswer(answer, true) },
                modifier = Modifier.padding(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                border = BorderStroke(1.dp, Color.Black)
            ) {
                Text(text = (answer + 1).toString())
            }

            Button(
                onClick = { checkAnswer(answer, false) },
                modifier = Modifier.padding(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                border = BorderStroke(1.dp, Color.Black)
            ) {
                Text(text = answer.toString())
            }

            Button(
                onClick = { checkAnswer(answer, true) },
                modifier = Modifier.padding(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                border = BorderStroke(1.dp, Color.Black)
            ) {
                Text(text = (answer - 1).toString())
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { resetGame() },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Reset")
        }
    }
}

data class Card(val value: Int, val color: CardColor)

//@Composable
fun checkAnswer(actualAnswer: Int, isHigher: Boolean) {
    val selectedAnswer = if (isHigher) actualAnswer + 1 else actualAnswer - 1
    val isCorrect = selectedAnswer == actualAnswer
    val resultMessage = if (isCorrect) "Correct!" else "Wrong!"

    // Display the result message or perform other actions based on the answer check
    // For example, you can show a toast message, update the score, or navigate to the next level.
}

//@Composable
fun resetGame() {
    // Reset the game state
    // For example, clear the selected cards and generate new cards
}

@Preview
@Composable
fun PreviewCardCalculationGameScreen() {
    CardCalculationGameScreen()
}
//calculations