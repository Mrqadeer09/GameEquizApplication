package com.teamx.gameequizapplication.games

import android.annotation.SuppressLint
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.math.roundToInt
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

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun MyCard() {
    var swipeState by remember { mutableStateOf(false) }
    var randomInt by remember { mutableStateOf(0) }
    var previousNumber by remember { mutableIntStateOf(randomInt) }
    var wapsiState by remember { mutableStateOf(false) }

    val transitionState = remember {
        MutableTransitionState(swipeState).apply { targetState = !swipeState }
    }
    val transition = updateTransition(transitionState, "cardTransition")

    val offsetTransition by transition.animateFloat(
        label = "cardOffsetTransition",
        transitionSpec = { tween(durationMillis = 300) },
        targetValueByState = { if (swipeState) 1275f else 0f },
    )
    if (swipeState) {

        LaunchedEffect(key1 = true) {
            delay(1000)

            swipeState = false
            previousNumber = randomInt

            wapsiState = !wapsiState
            randomInt = Random.nextInt(4, 100);
        }

    }
    var i = 0;

    if (previousNumber >= randomInt) {
        wapsiState = true
    } else {
        wapsiState = false

    }

    if (wapsiState) {
        i = -1
    } else {
        i = 1
    }

    Card(
        modifier = Modifier
            .testTag("DraggableCard")
            .width(65.dp)
            .height(65.dp)
            .padding(horizontal = 4.dp, vertical = 1.dp)
            .offset {
                if (wapsiState) {

                    IntOffset(y = offsetTransition.roundToInt(), x = 0)
                } else {
                    IntOffset(y = -offsetTransition.roundToInt(), x = 0)

                }/*   if (wapsiState) {

                    IntOffset(x = offsetTransition.roundToInt(), y = 0)
                } else {
                    IntOffset(x = -offsetTransition.roundToInt(), y = 0)

                }*/

            }
            .pointerInput(Unit) {
                detectVerticalDragGestures { _, dragAmount ->
                    if (!wapsiState) {
                        when {
                            dragAmount >= 6 -> {
                                swipeState = false
                            }

                            dragAmount < -6 -> {
                                swipeState = true
                            }
                        }
                    } else {
                        when {
                            dragAmount >= 6 -> {
                                swipeState = true
                            }

                            dragAmount < -6 -> {
                                swipeState = false
                            }
                        }
                    }

                }/*detectVerticalDragGestures { change, dragAmount ->

                    when {
                        dragAmount >= 6 -> {
                            swipeState = false
                        }

                        dragAmount < -6 -> {
                            swipeState = true
                        }
                    }


                }*/
            }
            .background(color = Color.Gray),


        ) { Text(text = "$randomInt") }
}

@Preview
@Composable
fun checkout() {
    MaterialTheme {
        Box(
            modifier = Modifier.fillMaxSize(), Alignment.Center
        ) {

            MyCard()
        }
    }
}
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