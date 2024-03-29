package com.teamx.gameequizapplication.games

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.teamx.gameequizapplication.ui.theme.BirdColor4
import kotlin.random.Random

class SimplicityGame {}

//Simplicity
data class Question(val equation: String, val choices: List<Int>, val correctAnswer: Int)

@Preview
@Composable
fun ImplicityGameScreen(content: @Composable () -> Unit = {}) {
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
            modifier = Modifier
                .fillMaxSize()
                .background(BirdColor4),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = currentQuestion.equation,
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White, fontSize = 33.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            LazyColumn(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {

                itemsIndexed(currentQuestion.choices.shuffled()) { _, choice ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(65.dp)
                            .padding(vertical = 8.dp, horizontal = 70.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.White)
                            .clickable {
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
                        contentAlignment = Alignment.Center

                    ) {
                        Text(
                            text = choice.toString(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 33.sp,
                            color = BirdColor4
                        )
                    }
                }
            }

            Text(
                text = "Score: $score",
                style = MaterialTheme.typography.bodyLarge, color = Color.White,
                fontSize = 33.sp,
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

                }, Modifier.size(54.dp)

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
            "$firstNumber ${operators[selectOperation]} $secondNumber ",
//            "$firstNumber ${operators[selectOperation]} $secondNumber = ?",
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