package com.teamx.gameequizapplication.games

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.random.Random

class TouchTheNumGame {
}
//Touch The Number


data class NumberBox(val number: Int, val color: Color)

@Composable
fun TouchTheNumbersGameScreen() {
    var score by remember { mutableStateOf(0) }
    var boxes by remember { mutableStateOf(generateBoxes()) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Touch the Numbers",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyRow(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            itemsIndexed(boxes) { index, box ->
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .background(color = box.color)
                        .clickable {
                            updateScore(box, index)
                            boxes = generateBoxes()
                        }
                ) {
                    Text(
                        text = box.number.toString(),
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.align(Alignment.Center)
                    )
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

private fun generateBoxes(): List<NumberBox> {
    val numbers = mutableListOf<Int>()
    for (i in 1..5) {
        numbers.add(i)
    }
    numbers.shuffle()

    val colors = mutableListOf<Color>()
    for (i in 1..5) {
        colors.add(if (Random.nextBoolean()) Color.Green else Color.Red)
    }

    return numbers.mapIndexed { index, number ->
        NumberBox(number = number, color = colors[index])
    }
}

var score = 0
private fun updateScore(box: NumberBox, index: Int) {
    val selectedNumbers = mutableListOf<Int>()
    for (i in index until index + 5) {
        if (i < box.number && box.color == Color.Green) {
            selectedNumbers.add(i)
        } else if (i > box.number && box.color == Color.Red) {
            selectedNumbers.add(i)
        }
    }

    val sortedNumbers = selectedNumbers.sorted()
    if (selectedNumbers == sortedNumbers) {
        // Correct order
        score++
    } else {
        // Incorrect order
        score = 0
    }
}


@Preview
@Composable
fun PreviewTouchTheNumbersGameScreen() {
    TouchTheNumbersGameScreen()
}

//Touch The Number