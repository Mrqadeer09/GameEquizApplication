package com.teamx.gameequizapplication.games

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import kotlinx.coroutines.delay
class TenSecond {
}
/*
@Composable
fun ShapeTransformationApp() {
    var shape by remember { mutableStateOf(Shape1.Circle) }

    LaunchedEffect(Unit) {
        delay(10000) // Wait for 10 seconds
        shape = Shape1.Triangle
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Canvas(modifier = Modifier.size(200.dp)) {
            val canvasSize = LocalDensity.current.run {
                Size(size.width.toDp().value, size.height.toDp().value)
            }

            drawShape(shape, canvasSize)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { shape = Shape1.Circle },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Reset")
        }
    }
}

@Composable
private fun CanvasScope.drawShape(shape: Shape1, canvasSize: Size) {
    val center = Offset(canvasSize.width / 2f, canvasSize.height / 2f)
    val radius = canvasSize.width / 2f

    when (shape) {
        Shape1.Circle -> drawCircle(Color.Red, radius, center)
        Shape1.Triangle -> drawTriangle(Color.Blue, radius, center)
    }
}

@Composable
private fun CanvasScope.drawTriangle(color: Color, size: Float, center: Offset) {
    val triangleHeight = size * 2f
    val triangleWidth = triangleHeight * (2f / 3f)
    val halfWidth = triangleWidth / 2f
    val halfHeight = triangleHeight / 2f

    val path = android.graphics.Path().apply {
        moveTo(center.x, center.y - halfHeight)
        lineTo(center.x - halfWidth, center.y + halfHeight)
        lineTo(center.x + halfWidth, center.y + halfHeight)
        close()
    }

    drawPath(android.graphics.Path(path), color)
}

enum class Shape1 {
    Circle,
    Triangle
}*/

@Composable
fun ColorChangeApp() {
    var color by remember { mutableStateOf(Color.Red) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(10000) // Wait for 10 seconds
            color = if (color == Color.Red) Color.Blue else Color.Red
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .size(150.dp)
                .background(color)
        )
    }
}