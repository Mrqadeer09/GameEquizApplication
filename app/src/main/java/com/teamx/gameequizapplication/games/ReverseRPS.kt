package com.teamx.gameequizapplication.games

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Color
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
enum class Symbols {


    ROCK,
    PAPER,
    SCISSORS, ;
}

enum class Symbol(
    val text: String,
    val dominantSymbols: List<Symbols>,
    val deficientSymbols: List<Symbols>
) {
    ROCK("Rock", listOf(Symbols.PAPER), listOf(Symbols.SCISSORS)),
    PAPER("Paper", listOf(Symbols.SCISSORS), listOf(Symbols.ROCK)),
    SCISSORS("Scissors", listOf(Symbols.ROCK), listOf(Symbols.PAPER));

    val color: Color
        get() = if (dominantSymbols.isNotEmpty()) Color.Green else Color.Red
}


@Composable
fun ReverseRockPaperScissorsGameScreen(content: @Composable () -> Unit) {
    var score2 by remember { mutableStateOf(0) }
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

        Row(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            currentSymbol.dominantSymbols.forEach { symbol ->
                Button(
                    onClick = {
                        checkAnswer(symbol, { score2++ }, { score2-- }, { symbol ->
                            currentSymbol = symbol
                        })
                    },
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .size(125.dp), shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = symbol.name)
                }
            }

            Button(
                onClick = {
                    checkAnswer(currentSymbol.text, { score2++ }, { score2-- }, { symbol ->
                        currentSymbol = symbol
                    })
                },
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(125.dp), shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = currentSymbol.name)
            }


            currentSymbol.deficientSymbols.forEach { symbol ->
                Button(
                    onClick = {
                        checkAnswer(symbol, { score2++ }, { score2-- }, { symbol ->
                            currentSymbol = symbol
                        })
                    },
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .size(125.dp), shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = symbol.name)
                }
            }
        }

        Text(
            text = "Score: $score2",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

private fun generateSymbol(): Symbol {
    val symbols = Symbol.values()
    return symbols.random()
}

private fun checkAnswer(
    chosenSymbol: Symbols,
    correctAction: () -> Unit,
    wrongAction: () -> Unit,
    changeAction: (symbol: Symbol) -> Unit
) {
    val nextSymbol = generateSymbol()
    val isCorrect = if (nextSymbol.color == Color.Green) {
        chosenSymbol in nextSymbol.dominantSymbols
    } else {
        chosenSymbol in nextSymbol.deficientSymbols
    }

    // Update score based on correctness
    if (isCorrect) {
//        score3++
        correctAction()
    } else {
        wrongAction()
//        score3--
//        score3 = 0
    }
    changeAction(nextSymbol)

//    currentSymbol = nextSymbol

}

private fun checkAnswer(
    chosenSymbol: String,
    correctAction: () -> Unit,
    wrongAction: () -> Unit,
    changeAction: (symbol: Symbol) -> Unit
) {
    val nextSymbol = generateSymbol()
    val isCorrect = nextSymbol.text == chosenSymbol

    // Update score based on correctness
    if (isCorrect) {
//        score3++
        correctAction()
    } else {
        wrongAction()
//        score3--
//        score3 = 0
    }
    changeAction(nextSymbol)

//    currentSymbol = nextSymbol

}

var currentSymbol: Any? = null

/*
@Preview
@Composable
fun PreviewReverseRockPaperScissorsGameScreen() {
    ReverseRockPaperScissorsGameScreen()
}
*/

//Rock paper scissor
