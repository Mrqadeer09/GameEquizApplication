package com.teamx.gameequizapplication.games

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class ReverseRPS {
}
//Rock paper scissor

/*enum class Symbol(val text: String, val dominantSymbols: List<Symbol>, val deficientSymbols: List<Symbol>) {
    ROCK("Rock", listOf(Symbol.SCISSORS), listOf(Symbol.PAPER)),
    PAPER("Paper", listOf(ROCK), listOf(SCISSORS)),
    SCISSORS("Scissors", listOf(PAPER), listOf(ROCK))
}

@Composable
fun ReverseRockPaperScissorsGameScreen() {
    var score by remember { mutableStateOf(0) }
    var currentSymbol by remember { mutableStateOf(generateSymbol()) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Reverse Rock Paper Scissors",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Box(
            modifier = Modifier
                .size(200.dp)
                .background(color = currentSymbol.color)
                .clickable {
                    val nextSymbol = generateSymbol()
                    val isCorrect = if (currentSymbol.color == Color.Green) {
                        nextSymbol in currentSymbol.dominantSymbols
                    } else {
                        nextSymbol in currentSymbol.deficientSymbols
                    }

                    if (isCorrect) {
                        score++
                    } else {
                        score = 0
                    }

                    currentSymbol = nextSymbol
                }
        ) {
            Text(
                text = currentSymbol.text,
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

private fun generateSymbol(): Symbol {
    val symbols = Symbol.values()
    return symbols.random()
}

@Preview
@Composable
fun PreviewReverseRockPaperScissorsGameScreen() {
    ReverseRockPaperScissorsGameScreen()
}*/

enum class Symbol(
    val text: String,
    val dominantSymbols: List<Symbol>,
    val deficientSymbols: List<Symbol>
) {
    ROCK("Rock", emptyList(), emptyList()),
    PAPER("Paper", listOf(Symbol.ROCK), emptyList()),
    SCISSORS("Scissors", listOf(Symbol.PAPER), listOf(Symbol.ROCK));

    val color: Color
        get() = if (dominantSymbols.isNotEmpty()) Color.Green else Color.Red
}

@Composable
fun ReverseRockPaperScissorsGameScreen() {
    var score by remember { mutableStateOf(0) }
    var currentSymbol by remember { mutableStateOf(generateSymbol()) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Reverse Rock Paper Scissors",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = currentSymbol.text,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Column(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            currentSymbol.dominantSymbols.forEach { symbol ->
                Button(
                    onClick = {
                        checkAnswer(symbol)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text(text = symbol.text)
                }
            }

            currentSymbol.deficientSymbols.forEach { symbol ->
                Button(
                    onClick = {
                        checkAnswer(symbol)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text(text = symbol.text)
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

private fun generateSymbol(): Symbol {
    val symbols = Symbol.values()
    return symbols.random()
}

private fun checkAnswer(chosenSymbol: Symbol) {
    val nextSymbol = generateSymbol()
    val isCorrect = if (nextSymbol.color == Color.Green) {
        chosenSymbol in nextSymbol.dominantSymbols
    } else {
        chosenSymbol in nextSymbol.deficientSymbols
    }

    // Update score based on correctness
    if (isCorrect) {
        score++
    } else {
        score = 0
    }

    currentSymbol = nextSymbol
}

var currentSymbol: Any? = null

@Preview
@Composable
fun PreviewReverseRockPaperScissorsGameScreen() {
    ReverseRockPaperScissorsGameScreen()
}

//Rock paper scissor
