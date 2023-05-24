package com.teamx.gameequizapplication.games

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

class FlickGame {
}
//flick
enum class ArrowDirection { LEFT, RIGHT }

@Composable
fun FlickGameScreen() {
    var correctAnswers by remember { mutableStateOf(0) }
    var incorrectAnswers by remember { mutableStateOf(0) }
    var arrowDirection by remember { mutableStateOf(ArrowDirection.LEFT) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ArrowIndicator(
                direction = ArrowDirection.LEFT,
                selectedDirection = arrowDirection,
                onClick = {
                    if (arrowDirection == ArrowDirection.LEFT) {
                        correctAnswers++
                    } else {
                        incorrectAnswers++
                    }
                    updateArrowDirection()
                }
            )
            ArrowIndicator(
                direction = ArrowDirection.RIGHT,
                selectedDirection = arrowDirection,
                onClick = {
                    if (arrowDirection == ArrowDirection.RIGHT) {
                        correctAnswers++
                    } else {
                        incorrectAnswers++
                    }
                    updateArrowDirection()
                }
            )
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

    drawLine(
        color = color,
        start = Offset(x - arrowOffset, y),
        end = Offset(x + arrowOffset, y),
        strokeWidth = thickness
    )

    when (direction) {
        ArrowDirection.LEFT -> {
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
    }
}

private fun updateArrowDirection(): ArrowDirection {
    return if (Random.nextBoolean()) ArrowDirection.LEFT else ArrowDirection.RIGHT
}

@Preview
@Composable
fun PreviewFlickGameScreen() {
    FlickGameScreen()
}

//flick