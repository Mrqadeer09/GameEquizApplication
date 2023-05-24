package com.teamx.gameequizapplication.games

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class ColorSwitchGame {
}

//color switch

/*enum class GameColor(val color: Color) {
    RED(Color.Red),
    BLUE(Color.Blue),
    GREEN(Color.Green),
    YELLOW(Color.Yellow)
}

@Composable
fun ColorSwitchGameScreen() {
    var score by remember { mutableStateOf(0) }
    var currentColor by remember { mutableStateOf(generateRandomColor()) }
    var isColorMatched by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Color Switch",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Box(
            modifier = Modifier
                .size(200.dp)
                .background(currentColor.color)
                .clickable { onColorClicked() }
        )

        Text(
            text = "Score: $score",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

private fun generateRandomColor(): GameColor {
    val colors = GameColor.values()
    return colors.random()
}

private fun onColorClicked() {
    // Perform color matching logic
    // Update score and currentColor state accordingly
}

@Preview
@Composable
fun PreviewColorSwitchGameScreen() {
    ColorSwitchGameScreen()
}*/


enum class GameColor(val color: Color) {
    RED(Color.Red),
    BLUE(Color.Blue),
    GREEN(Color.Green),
    YELLOW(Color.Yellow)
}

@Composable
fun ColorSwitchGameScreen() {
    var score by remember { mutableStateOf(0) }
    var targetColor by remember { mutableStateOf(generateRandomColor()) }
    var isColorMatched by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Color Switch",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Box(
            modifier = Modifier
                .size(200.dp)
                .background(targetColor.color)
                .clickable {
                    if (isColorMatched) {
                        score++
                        targetColor = generateRandomColor()
                        isColorMatched = false
                    }
                }
        )

        Row(
            modifier = Modifier.padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            GameColor.values().forEach { gameColor ->
                ColorButton(
                    color = gameColor.color,
                    onColorSelected = { selectedColor ->
                        isColorMatched = (selectedColor == targetColor)
                    }
                )
            }
        }

        Text(
            text = "Score: $score",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

@Composable
fun ColorButton(color: Color, onColorSelected: (GameColor) -> Unit) {
    Box(
        modifier = Modifier
            .size(60.dp)
            .background(color)
            .clickable { onColorSelected(getGameColorByColor(color)) }
    )
}

private fun generateRandomColor(): GameColor {
    val colors = GameColor.values()
    return colors.random()
}

private fun getGameColorByColor(color: Color): GameColor {
    return GameColor.values().firstOrNull { it.color == color } ?: GameColor.RED
}

@Preview
@Composable
fun PreviewColorSwitchGameScreen() {
    ColorSwitchGameScreen()
}

//color switch