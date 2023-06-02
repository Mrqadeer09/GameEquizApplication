package com.teamx.gameequizapplication

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.teamx.gameequizapplication.games.TouchTheNumbersGameScreen
import com.teamx.gameequizapplication.ui.theme.GameEquizApplicationTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GameEquizApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
//                    GameScreen()
//                    FlickGameScreen()
//                    TouchTheNumbersGameScreen()
//                    ReverseRockPaperScissorsGameScreen()
//                    MissingPieceGameScreen()
//                    ColorSwitchGameScreen()
//                    HexaChainGameScreen()
//                    CardCalculationGameScreen()
//                    Make10GameScreen()
//                    HighOrLowGameScreen()
//                    ImplicityGameScreen()
//                    ResultScreenToolbar()
//                    FieldClassification.Match /ingStepGame(Modifier)
                    TouchTheNumbersGameScreen()
//                    SwipeableComponent()

//                    ColorSwitchGameScreen()
//                    GameScreen2()
//                    ColorChangeApp()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GameEquizApplicationTheme {
//        GameScreen2()

    }
}


class Hexagon(private val size: Size) {
    private val radius: Float = size.width / 2f

    private val angles = listOf(
        0f,
        60f,
        120f,
        180f,
        240f,
        300f
    )

    fun toPath(): Path {
        val path = Path()

        for (i in 0 until 6) {
            val angleRad = Math.toRadians(angles[i].toDouble())
            val x = size.width / 2f + radius * Math.cos(angleRad).toFloat()
            val y = size.height / 2f + radius * Math.sin(angleRad).toFloat()

            val point = if (i == 0) {
                Offset(x, y)
            } else {
                path.lineTo(x, y)
                null
            }

            if (i == 5) {
                point?.let { path.lineTo(it.x, it.y) }
            }
        }

        return path
    }
}


//lazycolum//
@Composable
fun LazyListExample() {
    val itemsList = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")

    LazyColumn {
        items(itemsList) { item ->
            ListItem(item = item)
        }
    }
}

@Composable
fun ListItem(item: String) {
    Surface(
        color = Color.White,
        shadowElevation = 2.dp,
        modifier = androidx.compose.ui.Modifier.padding(8.dp)
    ) {
        Text(
            text = item,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold,
                color = Color.Black
            ),
            modifier = androidx.compose.ui.Modifier.padding(16.dp)
        )
    }
}

@Preview
@Composable
fun PreviewLazyListExample() {
    LazyListExample()
}

/*@ExperimentalFoundationApi
@Composable
fun LazyGridExample() {
    val itemsList = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7", "Item 8", "Item 9")

    LazyVerticalGrid(
        cells = GridCells.Fixed(3)
    ) {
        items(itemsList) { item ->
            GridItem(item = item)
        }
    }
}

@Composable
fun GridItem(item: String) {
    Card(
        modifier = Modifier.padding(8.dp),
        backgroundColor = Color.White,
        elevation = 2.dp,
        shape = MaterialTheme.shapes.medium
    ) {
        Surface(
            modifier = Modifier.padding(16.dp),
            color = Color.White
        ) {
            Text(
                text = item,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                ),
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@ExperimentalFoundationApi
@Preview
@Composable
fun PreviewLazyGridExample() {
    LazyGridExample()
}*/
//