package com.teamx.gameequizapplication.games

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.teamx.gameequizapplication.Hexagon
import kotlin.math.sqrt

class HexaChainGame {
}

//hexa chain
/*enum class HexaColor(val color: Color) {
    RED(Color.Red),
    BLUE(Color.Blue),
    GREEN(Color.Green),
    YELLOW(Color.Yellow)
}

@Composable
fun HexaChainGameScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hexa-Chain",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        HexaChainCanvas()
    }
}

@Composable
fun HexaChainCanvas() {
    androidx.compose.foundation.Canvas(
        modifier = Modifier
            .size(300.dp)
            .padding(8.dp)
    ) {
        val hexagonRadius = size.minDimension / 4
        val hexagonOffset = size / 2F

        val hexaColors = listOf(
            HexaColor.RED,
            HexaColor.BLUE,
            HexaColor.GREEN,
            HexaColor.YELLOW
        )

        for (i in 0 until hexaColors.size) {
            val angle = Math.PI / 3 * i

            val centerX = hexagonOffset.x + hexagonRadius * cos(angle).toFloat()
            val centerY = hexagonOffset.y + hexagonRadius * sin(angle).toFloat()

            drawCircle(
                color = hexaColors[i].color,
                radius = hexagonRadius,
                center = Offset(centerX, centerY),
                style =  androidx.compose.ui.graphics.drawscope.DrawStyle(fill = hexaColors[i].color)
            )
        }
    }
}

@Preview
@Composable
fun PreviewHexaChainGameScreen() {
    HexaChainGameScreen()
}*/
/*
enum class HexaColor(val color: Color) {
    RED(Color.Red),
    BLUE(Color.Blue),
    GREEN(Color.Green),
    YELLOW(Color.Yellow)
}

@Composable
fun HexaChainGameScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hexa-Chain",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        HexaChainGrid()
    }
}

@Composable
fun HexaChainGrid() {
    val hexaColors = listOf(
        HexaColor.RED,
        HexaColor.BLUE,
        HexaColor.GREEN,
        HexaColor.YELLOW
    )

    val gridSize = 4
    val cellSize = 60.dp
    val cellSpacing = 8.dp
    val gridPadding = 16.dp

    Box(
        modifier = Modifier.padding(gridPadding)
    ) {
        for (row in 0 until gridSize) {
            for (column in 0 until gridSize) {
                val color = hexaColors.random()

                HexaChainCell(
                    color = color.color,
                    modifier = Modifier
                        .size(cellSize)
                        .align(Alignment.TopStart)
                        .offset(
                            x = column * (cellSize + cellSpacing),
                            y = row * (cellSize + cellSpacing) + (column * cellSize / 2)
                        )
                )
            }
        }
    }
}

@Composable
fun HexaChainCell(color: Color, modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val radius = size.minDimension / 2
        val center = Offset(size.width / 2, size.height / 2)
        val cornerRadius = CornerRadius(size.width / 2, size.height / 2)
        val hexagonPath = createHexagonPath(center, radius, cornerRadius)

        drawPath(
            path = hexagonPath,
            color = color,
            style = Fill
        )
    }
}

private fun createHexagonPath(center: Offset, radius: Float, cornerRadius: CornerRadius): Path {
    val hexagonPath = Path()

    val angleDeg = 60f
    val angleRad = Math.toRadians(angleDeg.toDouble())

    val startX = center.x + radius * Math.cos(0.0).toFloat()
    val startY = center.y + radius * Math.sin(0.0).toFloat()

    hexagonPath.moveTo(startX, startY)

    for (i in 1..6) {
        val x = center.x + radius * Math.cos(angleRad * i).toFloat()
        val y = center.y + radius * Math.sin(angleRad * i).toFloat()
        hexagonPath.lineTo(x, y)
    }

    hexagonPath.close()

    val pathWithCornerRadius = Path()
    val cornerSize = Size(cornerRadius.x, cornerRadius.y)
    pathWithCornerRadius.addRect(hexagonPath.getBounds()*/
/*, cornerSize, Path.Direction.CW*//*
)

    return pathWithCornerRadius
}

@Preview
@Composable
fun PreviewHexaChainGameScreen() {
    HexaChainGameScreen()
}
*/

enum class HexaColor(val color: Color) {
    RED(Color.Red),
    BLUE(Color.Blue),
    GREEN(Color.Green),
    YELLOW(Color.Yellow)
}

@Composable
fun HexaChainGameScreen(content: @Composable () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hexa-Chain",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        HexaChainGrid()

    }
}

@Composable
fun HexaChainGrid() {
    val hexaColors = listOf(
        HexaColor.RED,
        HexaColor.BLUE,
        HexaColor.GREEN,
        HexaColor.YELLOW
    )

    val gridSize = 4
    val cellSize = 60.dp
    val cellSpacing = 8.dp
    val gridPadding = 16.dp

    Box(
        modifier = Modifier.padding(gridPadding)
    ) {
        for (row in 0 until gridSize) {
            Row(
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.TopStart)
                    .offset(y = row * (cellSize + cellSpacing) + (row * cellSize / 2))
            ) {
                for (column in 0 until gridSize) {
                    val color = hexaColors.random()

                    /*HexaChainCell(
                        color = color.color,
                        size = cellSize
                    )*/
                    HexagonShape(size = cellSize, color = color.color)
                }
            }
        }
    }
}

@Composable
fun HexaChainCell(color: Color, size: Dp) {
    Canvas(
        modifier = Modifier.size(size),
        onDraw = {
            val hexagon = Hexagon(size = Size(size.toPx(), size.toPx()))
            drawHexagon(hexagon, color)
        }
    )
}

private fun DrawScope.drawHexagon(hexagon: Hexagon, color: Color) {
    drawPath(
        path = hexagon.toPath(),
        color = color,
        style = androidx.compose.ui.graphics.drawscope.Fill
    )
}

@Preview
@Composable
fun PreviewHexaChainGameScreen() {
    HexaChainGameScreen() {}
}
//hexa chain


@Composable
fun HexagonShape(
    size: Dp,
    color: Color
) {
    Box(
        modifier = Modifier.size(size)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val path = Path()
            val radius = size.toPx() / 2
            val halfRadius = radius / 2
            val height = (sqrt(3.0) * radius).toFloat()

            path.moveTo(radius, 0f)
            path.lineTo(radius + halfRadius, height / 2)
            path.lineTo(radius + halfRadius, height + height / 2)
            path.lineTo(radius, height * 2)
            path.lineTo(radius - halfRadius, height + height / 2)
            path.lineTo(radius - halfRadius, height / 2)
            path.close()

            drawPath(path, color)
        }
    }
}
