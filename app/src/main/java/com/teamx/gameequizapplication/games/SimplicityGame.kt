package com.teamx.gameequizapplication.games

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.random.Random

class SimplicityGame {}

//Simplicity
data class Question(val equation: String, val choices: List<Int>, val correctAnswer: Int)

@Composable
fun ImplicityGameScreen(content: @Composable () -> Unit) {
    var score by remember { mutableStateOf(0) }
    var questionIndex by remember { mutableStateOf(0) }
    var gameState by remember { mutableStateOf(true) }

    val questions = generateQuestions()
    Log.d("TAG", "ImplicityGameScreen:$questions ")
    if (gameState) {
        val currentQuestion = if (questions.isNotEmpty()) {
            questions[questionIndex]
        } else {
            listOf<Question>(
                Question("2 + 3 = ?", listOf(4, 5, 6, 7), 5)
            ).get(0)
        }


        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = currentQuestion.equation,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            LazyColumn(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {

                itemsIndexed(currentQuestion.choices.shuffled()) { _, choice ->
                    Button(
                        onClick = {
                            if (gameState) {

                                if (choice == currentQuestion.correctAnswer) {
                                    score++
                                }
//                        nextQuestion(questions.size)
                                questionIndex++
                                if (questionIndex == questions.size) {
                                    gameState = false
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        Text(text = choice.toString())
                    }
                }
            }

            Text(
                text = "Score: $score",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Compeleted")
            IconButton(
                onClick = {
                    questionIndex = 0
                    score = 0
                    gameState = true

                },Modifier.size(54.dp)

                ) {
                Icon(imageVector = Icons.Default.Replay, contentDescription = null)

            }
        }
    }
}

private fun generateQuestions(): ArrayList<Question> {
    val operators = listOf("+", "-", "*", "/")
    var list = ArrayList<Question>()
    for (i in 1..10) {
        val firstNumber = Random.nextInt(1, 10)
        val secondNumber = Random.nextInt(1, 10)
        val selectOperation = Random.nextInt(1, 4)
        val result = when (operators[selectOperation]) {
            "+" -> {
                firstNumber + secondNumber
            }
            "-" -> {
                firstNumber - secondNumber
            }
            "*" -> {
                firstNumber * secondNumber
            }
            "/" -> {
                firstNumber / secondNumber
            }
            else -> {
                firstNumber + secondNumber
            }
        }

        val o = Question(
            "$firstNumber ${operators[selectOperation]} $secondNumber = ?",
            listOf(
                result + Random.nextInt(2, 10),
                result,
                result - Random.nextInt(2, 10),
                result * Random.nextInt(2, 10)
            ),
            result
        )
        list.add(o)
    }


    return list
/* return listOf(
        Question("2 + 3 = ?", listOf(4, 5, 6, 7), 5),
        Question("8 - 5 = ?", listOf(2, 3, 4, 5), 3),
        Question("4 * 6 = ?", listOf(18, 20, 24, 28), 24),
        Question("15 / 3 = ?", listOf(3, 4, 5, 6), 5)
    )*/
}

//@Preview
/*@Composable
fun PreviewImplicityGameScreen() {
    ImplicityGameScreen()
}*/


//Simplicity