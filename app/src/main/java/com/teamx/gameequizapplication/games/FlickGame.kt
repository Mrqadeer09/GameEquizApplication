package com.teamx.gameequizapplication.games

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.teamx.gameequizapplication.R
import kotlin.math.roundToInt
import kotlin.random.Random

class FlickGame {
}

//flick
enum class ArrowDirection { LEFT, RIGHT, TOP, BOTTOM }

@Composable
fun FlickGameScreen() {
    var correctAnswers by remember { mutableIntStateOf(0) }
    var incorrectAnswers by remember { mutableIntStateOf(0) }
    val arrowDirection by remember { mutableStateOf(ArrowDirection.LEFT) }
    var isShuffle by remember { mutableStateOf(ArrowDirection.TOP) }

    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            /*when (isShuffle) {
                ArrowDirection.LEFT -> {

                }
                ArrowDirection.RIGHT -> {

                }
                ArrowDirection.TOP -> {

                }
                ArrowDirection.BOTTOM -> {

                }
                else -> {

                    Log.d("TAG", "FlickGameScreen: ")
                }
            }*/

            ArrowIndicator(
                direction = isShuffle/*ArrowDirection.LEFT*/,
                selectedDirection = arrowDirection,
                onClick = {
                    if (arrowDirection == isShuffle /*ArrowDirection.LEFT*/) {
                        correctAnswers++

                        isShuffle = updateArrowDirection()
                    } else {
                        incorrectAnswers++
                    }

                }
            )

//            ArrowIndicator(
//                direction = ArrowDirection.RIGHT,
//                selectedDirection = arrowDirection,
//                onClick = {
//                    if (arrowDirection == ArrowDirection.RIGHT) {
//                        correctAnswers++
//                    } else {
//                        incorrectAnswers++
//                    }
//                    updateArrowDirection()
//                }
//            )
        }

        Text(
            text = "Correct: $correctAnswers, Incorrect: $incorrectAnswers",
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        )
    }
}

@Composable
fun ArrowIndicator(
    direction: ArrowDirection,
    selectedDirection: ArrowDirection,
    onClick: () -> Unit
) {
    val arrowColor = if (direction == selectedDirection) Color.Green else Color.Red

    Canvas(
        modifier = Modifier
            .size(120.dp)
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    if (change.pressed) {
                        onClick()
                    }
                }
            }
    ) {
        val arrowSize = size.minDimension * 0.6f
        val arrowThickness = 10.dp.toPx()

        drawArrow(
            color = arrowColor,
            size = arrowSize,
            thickness = arrowThickness,
            direction = direction,
            center = center
        )
    }
}

private fun DrawScope.drawArrow(
    color: Color,
    size: Float,
    thickness: Float,
    direction: ArrowDirection,
    center: Offset
) {
    val x = center.x
    val y = center.y

    val arrowOffset = size * 0.4f
    val arrowWidth = size * 0.3f
    val arrowHeight = size * 0.1f



    when (direction) {
        ArrowDirection.LEFT -> {
            drawLine(
                color = color,
                start = Offset(x - arrowOffset, y),
                end = Offset(x + arrowOffset, y),
                strokeWidth = thickness
            )
            drawLine(
                color = color,
                start = Offset(x + arrowOffset, y),
                end = Offset(x + arrowOffset - arrowHeight, y - arrowWidth),
                strokeWidth = thickness
            )
            drawLine(
                color = color,
                start = Offset(x + arrowOffset, y),
                end = Offset(x + arrowOffset - arrowHeight, y + arrowWidth),
                strokeWidth = thickness
            )
        }

        ArrowDirection.RIGHT -> {
            drawLine(
                color = color,
                start = Offset(x - arrowOffset, y),
                end = Offset(x + arrowOffset, y),
                strokeWidth = thickness
            )
            drawLine(
                color = color,
                start = Offset(x - arrowOffset, y),
                end = Offset(x - arrowOffset + arrowHeight, y - arrowWidth),
                strokeWidth = thickness
            )
            drawLine(
                color = color,
                start = Offset(x - arrowOffset, y),
                end = Offset(x - arrowOffset + arrowHeight, y + arrowWidth),
                strokeWidth = thickness
            )
        }

        ArrowDirection.BOTTOM -> {
            drawLine(
                color = color,
                start = Offset(y = x - arrowOffset, x = y),
                end = Offset(y = x + arrowOffset, x = y),
                strokeWidth = thickness
            )

            drawLine(
                color = color,
                start = Offset(y = y + arrowOffset, x = x),
                end = Offset(y = y + arrowOffset - arrowHeight, x = x - arrowWidth),
                strokeWidth = thickness
            )
            drawLine(
                color = color,
                start = Offset(y = y + arrowOffset, x = y),
                end = Offset(y = y + arrowOffset - arrowHeight, x = x + arrowWidth),
                strokeWidth = thickness
            )
        }

        ArrowDirection.TOP -> {
            drawLine(
                color = color,
                start = Offset(y = x - arrowOffset, x = y),
                end = Offset(y = x + arrowOffset, x = y),
                strokeWidth = thickness
            )

            drawLine(
                color = color,
                start = Offset(y = y - arrowOffset, x = x),
                end = Offset(y = y - arrowOffset + arrowHeight, x = x - arrowWidth),
                strokeWidth = thickness
            )
            drawLine(
                color = color,
                start = Offset(y = y - arrowOffset, x = x),
                end = Offset(y = y - arrowOffset + arrowHeight, x = x + arrowWidth),
                strokeWidth = thickness
            )
        }
    }
}

private fun updateArrowDirection(): ArrowDirection {
    return when (Random.nextInt(0, 4)) {
        1 -> ArrowDirection.LEFT
        2 -> ArrowDirection.RIGHT
        3 -> ArrowDirection.TOP
        4 -> ArrowDirection.BOTTOM
        else -> ArrowDirection.RIGHT
    }
}

/*@Preview
@Composable
fun PreviewFlickGameScreen() {
    SwipeableComponent()
}*/

//flick
@Composable
fun SwipeableComponent(content: @Composable () -> Unit) {
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    offsetX += dragAmount.x
                    offsetY += dragAmount.y
                }
            }
    ) {
        val swipeableModifier = Modifier.offset { IntOffset(offsetX.toInt(), offsetY.toInt()) }

        Text(
            text = "Swipe Me",
            modifier = swipeableModifier.align(Alignment.Center),
            color = Color.White,
            fontSize = 24.sp
        )
    }
}


@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun MyCard2() {
    var swipeState by remember { mutableStateOf(false) }
    var randomInt by remember { mutableIntStateOf(0) }
//    var randomInt =0
    var previousNumber by remember { mutableIntStateOf(randomInt) }
    var wapsiState by remember { mutableStateOf(false) }
    var intOffset by remember { mutableStateOf<IntOffset>(IntOffset(y = 0, x = 0)) }
    var valuesTranslation by remember { mutableStateOf<Float>(100f) }


    var bimap by remember { mutableIntStateOf(R.drawable.left) }

    val transitionState = remember {
        MutableTransitionState(swipeState).apply { targetState = !swipeState }
    }
    val transition = updateTransition(transitionState, "cardTransition")

    val offsetTransition by transition.animateFloat(
        label = "cardOffsetTransition",
        transitionSpec = { tween(durationMillis = 300) },
        targetValueByState = { if (swipeState) /*0f*/ valuesTranslation else 0f },


        )

    wapsiState = transition.isRunning




    LaunchedEffect(key1 = !wapsiState) {


        randomInt = Random.nextInt(0, 4)
    }
    if (!wapsiState) {
        swipeState = false

    } else {

    }


    when (randomInt) {

        0 -> {

            intOffset = IntOffset(x = offsetTransition.roundToInt(), y = 0)
            bimap = R.drawable.right
        }

        1 -> {

            intOffset = IntOffset(x = -offsetTransition.roundToInt(), y = 0)
            bimap = R.drawable.left
        }

        2 -> {

            intOffset = IntOffset(y = -offsetTransition.roundToInt(), x = 0)
            bimap = R.drawable.up
        }

        3 -> {
            intOffset = IntOffset(y = offsetTransition.roundToInt(), x = 0)
            bimap = R.drawable.down
        }

        else -> {

            intOffset = IntOffset(y = -offsetTransition.roundToInt(), x = 0)
            bimap = R.drawable.left
        }


    }

    Card(
        modifier = Modifier
            .testTag("DraggableCard")
            .width(165.dp)
            .alpha(100f * 0.1f - valuesTranslation * 0.1f + 0.1f)
            .height(165.dp)
            .padding(horizontal = 4.dp, vertical = 1.dp)
            .offset {
                intOffset/* if (wapsiState) {

                        IntOffset(y = offsetTransition.roundToInt(), x = 0)
                    } else {
                        IntOffset(y = -offsetTransition.roundToInt(), x = 0)

                    }*//*   if (wapsiState) {

                    IntOffset(x = offsetTransition.roundToInt(), y = 0)
                } else {
                    IntOffset(x = -offsetTransition.roundToInt(), y = 0)

                }*/

            }
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->

                    when {
                        dragAmount.x >= 16 && randomInt == 0 -> {
//                            randomInt = 0
                            Log.d("123123", "MyCardRight: ")
                            swipeState = true
                        }

                        dragAmount.x < -16 && randomInt == 1 -> {
//                            randomInt = 1
                            Log.d("123123", "MyCardLeft: ")
                            swipeState = true
                        }

                        dragAmount.y >= 16 && randomInt == 3 -> {
//                            randomInt = 3
                            Log.d("123123", "MyCardDOWN: ")
                            swipeState = true
                        }

                        dragAmount.y < -16 && randomInt == 2 -> {
//                            randomInt = 2
                            Log.d("123123", "MyCardUP: ")
                            swipeState = true
                        }
                    }
                }

//                if (randomInt <= 1) {
//
//
//                    detectHorizontalDragGestures { _, dragAmount ->
//                        if (!wapsiState) {
//                            when {
//                                dragAmount >= 6 -> {
//                                    Log.d("123123", "MyCardRight: ")
//                                    swipeState = false
//                                }
//
//                                dragAmount < -6 -> {
//                                    Log.d("123123", "MyCardLeft: ")
//                                    swipeState = true
//                                }
//                            }
//                        } else {
//                            when {
//
//                                dragAmount >= -6 -> {
//                                    Log.d("123123", "MyCardRight: ")
//                                    swipeState = true
//                                }
//
//                                dragAmount < 6 -> {
//                                    Log.d("123123", "MyCardLeft: ")
//                                    swipeState = false
//                                }
//                            }
//                        }
//                    }
//                } else {
//                    detectVerticalDragGestures { _, dragAmount ->
//                        if (!wapsiState) {
//                            when {
//                                dragAmount >= 6 -> {
//                                    Log.d("123123", "MyCardUP: ")
//                                    swipeState = false
//                                }
//
//                                dragAmount < -6 -> {
//                                    Log.d("123123", "MyCardDOWN: ")
//                                    swipeState = true
//                                }
//                            }
//                        } else {
//                            when {
//
//                                dragAmount >= -6 -> {
//                                    Log.d("123123", "MyCardUP: ")
//                                    swipeState = true
//                                }
//
//                                dragAmount < 6 -> {
//                                    Log.d("123123", "MyCardDOWN: ")
//                                    swipeState = false
//                                }
//                            }
//                        }
//                    }
//                }

            }
            .background(color = Color.Gray),
    ) {
        Image(
            painter = painterResource(id = bimap),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
//                .clickable { isFlipped = !isFlipped }
//                .graphicsLayer(rotationZ = rotationZ)
        )

//        Text(
//            modifier = Modifier.fillMaxSize(), textAlign = TextAlign.Center, text = "$randomInt"
//        )
    }
}


@Preview
@Composable
fun checkout2() {
    MaterialTheme {
        Box(
            modifier = Modifier.fillMaxSize(), Alignment.Center
        ) {
            MyCard2()
        }
    }
}

