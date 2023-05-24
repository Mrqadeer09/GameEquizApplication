package com.teamx.gameequizapplication.games

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class SimplicityGame {}

//Simplicity
data class Question(val equation: String, val choices: List<Int>, val correctAnswer: Int)

@Composable
fun ImplicityGameScreen() {
    var score by remember { mutableStateOf(0) }
    var questionIndex by remember { mutableStateOf(0) }

    val questions = remember { generateQuestions() }
    val currentQuestion = questions[questionIndex]

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
            itemsIndexed(currentQuestion.choices) { _, choice ->
                Button(
                    onClick = {
                        if (choice == currentQuestion.correctAnswer) {
                            score++
                        }
                        nextQuestion(questions.size)
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
}

private fun generateQuestions(): List<Question> {
    return listOf(
        Question("2 + 3 = ?", listOf(4, 5, 6, 7), 5),
        Question("8 - 5 = ?", listOf(2, 3, 4, 5), 3),
        Question("4 * 6 = ?", listOf(18, 20, 24, 28), 24),
        Question("15 / 3 = ?", listOf(3, 4, 5, 6), 5)
    )
}

var questionIndex = 0
private fun nextQuestion(totalQuestions: Int) {
    questionIndex++
    if (questionIndex >= totalQuestions) {
        questionIndex = 0
    }
}

@Preview
@Composable
fun PreviewImplicityGameScreen() {
    ImplicityGameScreen()
}


//Simplicity